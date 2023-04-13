package com.knoldus

import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.math.pow
import scala.math.sqrt

// Calculator object that performs various operations based on the input operator and operands
object Calculator {
  // method to select and execute the selected operator with the provided operands
  def calculate(operator: String, operands: Seq[Double]): Future[Seq[Double]] = {
    val selectedOperator = operator match {
      case "+" => Addition
      case "-" => Subtraction
      case "*" => Multiplication
      case "/" => Division
      case "^" => Exponentiation
      case "sqrt" => SquareRoot
      case "!" => Factorial
      case "sum" => Sum
      case "gcd" => GreatestCommonDivisor
      case "odd" => Odd
      case "even" => Even
      case "fibonacci" => Fibonacci
      case _ => throw CalculatorException("Invalid operator")
    }
    // execute the selected operator with the provided operands
    execute(selectedOperator, operands)
  }

  // method that executes the provided operator with the given operands
  private def execute(operator: Operator, operands: Seq[Double]): Future[Seq[Double]] = {
    Future {
      operator.validateAndExecute(operands)
    }
  }


  //method to execute squareOfExpression and find if equal or not
  def squareOfExpression(firstOperand: Double, secondOperand: Double): String = {
    val leftHandSide = pow(firstOperand + secondOperand, 2)
    val rightHandSide = pow(firstOperand, 2) + pow(secondOperand, 2) + 2 * (firstOperand + secondOperand)
    if (leftHandSide == rightHandSide)
      "Equal"
    else
      "Not Equal"
  }

  //method to find the number whose factorial is greater than 6^number
  def find(numbers: Seq[Double]): Future[Seq[Double]] = Future {
    val result = numbers.filter(number => factorial(number.toInt) > pow(6, number))
    result
  }

  //helper function to compute factorial
  @tailrec
  private def factorial(number: Int, accumulator: Int = 1): Int = {
    if (number < 0)
      throw CalculatorException("cannot compute factorial of negative number")
    else if (number == 0)
      accumulator
    else
      factorial(number - 1, accumulator * number)
  }

  //method to find the Average of numbers
  //first we find the fibonacci of each number of input sequence then find the odd numbers from
  // that result then add those numbers find the average of them
  def findAverageAfterChainingOperations(numbers: Seq[Double]): Future[Double] = {
    Future {
      @tailrec
      def NthFibonacci(number: Double, previousNumber: Double, currentNumber: Double): Double = {
        if (number <= 2)
          currentNumber
        else
          NthFibonacci(number - 1, currentNumber, currentNumber + previousNumber)
      }

      val getOddFibonacciNumbers = numbers.filter { number =>
        val result = NthFibonacci(number.toInt, 0, 1)
        result % 2 != 0
      }
      getOddFibonacciNumbers.foldLeft(0.0)((firstValue: Double, secondValue: Double) => firstValue + secondValue) / getOddFibonacciNumbers.length
    }
  }

}

//object to Add two operands, implements Operator methods
object Addition extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.length == 2
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = Seq(operands.foldLeft(0.0)(_ + _))
}

//object to execute subtract operation
object Subtraction extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 2

  override def execute(operands: Seq[Double]): Seq[Double] = Seq(operands.reduceLeft(_ - _))
}

//object to execute multiplication operation
object Multiplication extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 2

  override def execute(operands: Seq[Double]): Seq[Double] = Seq(operands.foldLeft(1.0)(_ * _))
}

//object to execute division operation
object Division extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 2

  override def execute(operands: Seq[Double]): Seq[Double] = {
    operands match {
      case Seq(firstValue, secondValue) => if (secondValue != 0) {
        Seq(firstValue / secondValue)
      }
      else {
        throw new ArithmeticException("Cannot divide By zero ")
      }
      case _ => throw  CalculatorException("Cannot divide due to some error")
    }
  }
}

//object to execute exponent of number
object Exponentiation extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 2

  override def execute(operands: Seq[Double]): Seq[Double] = {
    def power(base: Double, exponent: Double): Double = {
      if (exponent == 0) 1
      else base * power(base, exponent - 1)
    }

    Seq(power(operands.head, operands.last))
  }
}

//object to execute square root operation
object SquareRoot extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 1

  override def execute(operands: Seq[Double]): Seq[Double] = {
    operands.map(operand => sqrt(operand))
  }
}

//object to execute Factorial operation
object Factorial extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 1 && operands.last > 0

  override def execute(operands: Seq[Double]): Seq[Double] = {
    operands.map { operand =>
      lazy val factorial: (Int, Int) => Int = {
        case (number, accumulator) => if (number == 0)
          accumulator
        else
          factorial(number - 1, accumulator * number)
      }
      factorial(operand.toInt, 1)
    }
  }
}

//object to execute Sum operation
object Sum extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.nonEmpty

  override def execute(operands: Seq[Double]): Seq[Double] = {
    Seq(operands.foldLeft(0.0)(_ + _))
  }
}


//object to execute GCD operation
object GreatestCommonDivisor extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 2

  override def execute(operands: Seq[Double]): Seq[Double] = {
    val firstValue = operands.head.toLong
    val secondValue = operands(1).toLong
    Seq(gcd(firstValue, secondValue).toDouble)
  }

  @tailrec
  private def gcd(firstNumber: Long, secondNumber: Long): Long = {
    if (secondNumber == 0)
      firstNumber
    else
      gcd(secondNumber, firstNumber % secondNumber)
  }
}

//object to execute odd operation on a sequence
object Odd extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.nonEmpty

  override def execute(operands: Seq[Double]): Seq[Double] = {
    operands.filter(value => value % 2 != 0)
  }
}

//object to execute even operation on sequence
object Even extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.nonEmpty

  override def execute(operands: Seq[Double]): Seq[Double] = {
    operands.filter(value => value % 2 == 0)
  }
}

//object to execute fibonacci operation to print the series
object Fibonacci extends Operator {
  override def validate(operands: Seq[Double]): Boolean = operands.length == 1

  override def execute(operands: Seq[Double]): Seq[Double] = {
    operands.headOption match {
      case Some(head) if head > 0 =>
        val number = head.toInt

        def fibonacci(number: Int): Seq[Int] = number match {
          case 1 => Seq(0)
          case 2 => Seq(0, 1)
          case _ =>
            val fibonacciSeq = fibonacci(number - 1)
            fibonacciSeq :+ fibonacciSeq.takeRight(2).sum
        }

        fibonacci(number).map(_.toDouble)
      case _ => throw CalculatorException("Fibonacci sequence requires a non-negative integer input.")
    }
  }
}




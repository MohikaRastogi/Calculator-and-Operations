package com.knoldus

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, SECONDS}
import scala.util.{Failure, Success}

object DriverMain extends App {
  // Perform various calculations using the Calculator object
  private val additionResult = Calculator.calculate("+", Seq(2.0, 3.0))
  private val subtractionResult = Calculator.calculate("-", Seq(5.0, 2.0))
  private val multiplyResult = Calculator.calculate("*", Seq(3.0, 4.0))
  private val divisionResult = Calculator.calculate("/", Seq(10.0, 5.0))
  private val exponentiationResult = Calculator.calculate("^", Seq(2, 4))
  private val squareRootResult = Calculator.calculate("sqrt", Seq(16.0))
  private val factorialResult = Calculator.calculate("!", Seq(5.0))
  private val sumResult = Calculator.calculate("sum", Seq(1.0, 2.0, 3.0, 4.0))
  private val gcdResult = Calculator.calculate("gcd", Seq(15.0, 30.0))
  private val oddResult = Calculator.calculate("odd", Seq(1.0, 2.0, 3.0, 4.0, 5.0))
  private val evenResult = Calculator.calculate("even", Seq(1.0, 2.0, 3.0, 4.0, 5.0))
  private val fibonacciSeriesResult = Calculator.calculate("fibonacci", Seq(10.0))


  // Collect all the results in a sequence
  private val results = Seq(
    additionResult,
    subtractionResult,
    multiplyResult,
    divisionResult,
    exponentiationResult,
    squareRootResult,
    factorialResult,
    sumResult,
    gcdResult,
    oddResult,
    evenResult,
    fibonacciSeriesResult,
  )
  // Printing the results when all futures are complete
  Future.sequence(results).onComplete {
    case Success(values) =>
      println("Results:")
      println(s"Addition: ${values(0)}")
      println(s"Subtraction: ${values(1)}")
      println(s"Multiplication: ${values(2)}")
      println(s"Division: ${values(3)}")
      println(s"Exponentiation: ${values(4)}")
      println(s"Square Root: ${values(5)}")
      println(s"Factorial: ${values(6)}")
      println(s"Sum: ${values(7)}")
      println(s"GCD: ${values(8)}")
      println(s"Odd: ${values(9)}")
      println(s"Even: ${values(10)}")
      println(s"Fibonacci Series: ${values(11)}")
    case Failure(exception) =>
      println(s"Failed to calculate: \n ${exception.getMessage}")
  }

  // Perform some additional calculations
  private val squareOfExpressionResult = Calculator.squareOfExpression(2, 2)
  private val findResult = Calculator.find(Seq(1.0, 2.0, 3.0, 4.0))
  private val resultChainingOperations = Calculator.findAverageAfterChainingOperations(Seq(1, 2, 3))

  // Wait for findResult and resultChainingOperations to complete
  Await.result(findResult, Duration(50, SECONDS))
  Await.result(resultChainingOperations, Duration(50, SECONDS))

  // Print results
  println("Square of Expression Result: " + squareOfExpressionResult)
  println("Find Result: " + findResult)
  println("Average After Chaining Operations Result: " + resultChainingOperations.value.get.get)
}

package com.knoldus

//A trait that has validate method,which is used to validate operands for every operator
trait Validator {
  //validate the operands for the specific operator
  def validate(operands: Seq[Double]): Boolean
}

//custom exception case class to throw exceptions
case class CalculatorException(message: String) extends Exception(message)

// a trait inherited by every operator to execute the selected operation
trait Operator extends Validator {
  def validateAndExecute(operands: Seq[Double]): Seq[Double] = {
    if (validate(operands))
      execute(operands)
    else
      throw CalculatorException(s"Validation Failed at ${this.getClass.getName} : operands $operands are not valid ")
  }

  protected def execute(operands: Seq[Double]): Seq[Double]
}




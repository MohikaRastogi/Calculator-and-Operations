### Calculator

This is a Scala project that implements a calculator with several operations like addition, subtraction, multiplication, division, exponentiation, square root, factorial, sum, greatest common divisor, odd, even, and Fibonacci. 

The program defines an Operator trait that has two methods: validate and execute. Each operation is implemented as an object that extends the Operator trait and overrides these two methods.

The calculate method takes an operator and operands as input and returns a Future that will eventually contain the result of executing the operator with the given operands.

The execute method is a private helper method that takes an operator and operands and returns a Future that will eventually contain the result of executing the operator with the given operands.

The program also defines several helper methods like squareOfExpression, factorial, find, and findAverageAfterChainingOperations that perform additional calculations.

Overall, the program provides a simple and extensible calculator implementation.

##### Usage

To use this project, you will first need to clone the repository to your local machine. You can do this by following these steps:

   1. Open up your terminal and navigate to the directory where you want to clone the project.
   2.  Type git clone https://github.com/username/repo.git (replace "username" and "repo" with the appropriate information for the repository you want to clone) and press enter.
   3. Once the repository has been cloned, navigate to the project directory by typing cd repo (replace "repo" with the appropriate name for the repository you cloned) and pressing enter.

####### Once you have cloned the repository and navigated to the project directory, you can run the project using the following steps:

    1. Install any required dependencies by typing npm install or yarn install (depending on which package manager you are using) and pressing enter.
   2. Run the project by typing npm start or yarn start and pressing enter.
   3. The project should now be running and you can access it by navigating to http://localhost:3000 in your web browser.

##### Running the project

To run the project, viewers can follow the steps provided above under the "Usage" section. Alternatively, you can provide a link to a deployed version of the project (if available) that viewers can access directly from their web browser. If you are hosting the project on a platform like Heroku, you can include a link to the hosted version in the README file or in the project description on the platform.

###### [Learn more about scala and setting up sbt here](https://blog.knoldus.com/simple-build-tool-getting-with-sbt-setting-up-running/)






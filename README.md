# kotlinplayground-calculator
This Kotlin project contains tests for a calculator, but no implementation.
It is used to learn Kotlin by implementing the calculator logic and making the tests pass.

I originally made this for my friends and colleagues, but anyone can use this. Just fork the repo and code away :)

## Getting started
To start implementing the calculator, you need to:
1. Fork this repo.
1. Optional: On your fork, create a branch named `solution/<your_name>`.
1. Run the tests the first time to see all the requirements. (see section below for details)
1. Implement a part of the calculator. (see section below for details)
1. Run the tests again and pat yourself on the back for the progress you made.
1. If there still are failing tests, go to step 4.

## Running the tests

##### From the command line
The simplest way to run the tests is to run the command `./gradlew test` in your project directory.
This will display all test results in the console.

##### From IntelliJ
If you are using IntelliJ, I strongly recommend the `Spek` Plugin. (https://github.com/raniejade/spek-idea-plugin)
You can install it directly from within IntelliJ.
With it, you can run the tests directly from the IDE and get a nice graphical representation of the results.

Simply open the 
[test source file](src/test/kotlin/de/lostmekka/kotlinplayground/calculator/CalculatorTest.kt)
in your IntelliJ project and you should see some run icons to the left off the code. 
Click the topmost of those to start a whole test run.

<image src="readme_resources/run_tests_intellij.png">
<image src="readme_resources/test_results_intellij.png">

## Implementing the calculator
You need to implement the method marked with `TODO` in the 
[calculator source file](src/main/kotlin/de/lostmekka/kotlinplayground/calculator/Calculator.kt).
It is highly recommended that your implementation spreads over many classes and files, 
so you can divide and conquer this training exercise.

# Contributing to this repo
If you have any change that would be nice to have in this repo, you can implement it in your fork and start a pull request.

Please note that no solution implementation is allowed in this repo, so if you also implement the calculator,
do it in your `solution/<your_name>` branch and only start pull requests for the `master` branch.

Also note, that I try to have a bit of backwards compatibility. 
Pull requests that are reasonable, but change the calculator specification, need to be handled with extra care.

##### Branching model
Currently, I am the only one who develops on the `master` branch.
Therefore I can just commit anything directly to `master`.
If there is a need for collaboration in the future, I will change the branching model accordingly. 
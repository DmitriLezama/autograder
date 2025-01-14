# Autograder

An automated grading system that evaluates Comp2603 assignment 1 and generates detailed PDF reports with marks and feedback based on assignment specification.

## Installation

<details>

<summary>
    <code>There are several ways to save this repository to your device. These options include:</code>
</summary>

-   [Downloading repository as ZIP](https://github.com/carrot2803/autograder/archive/refs/heads/master.zip)

-   Running the following command in a terminal, provided the [GitHub CLI](https://cli.github.com/) has been previously installed:

```sh
git clone https://github.com/carrot2803/autograder.git
```

#### Package java and dependencies:

Java and Maven are required to run this application. Refer to the [Document](https://github.com/carrot2803/autograder/wiki/Installation-Guide) for prerequisite installations.

-   To install the required dependencies and package the application, run the following command:

```sh
mvn clean package
```

-   To run the application, execute the following command:

```sh
java -jar target/autograder-1.0-SNAPSHOT-jar-with-dependencies.jar
```

</details>

## Features

-   Full coverage unit & performance test for all interfaces
-   ZIP file extraction for student submissions
-   Submission evaluation & grading based on assignment specification
-   PDF feedback generation with detailed test results and scores
-   Implements over 5 formal design patterns and many informal ones
-   High performance processing times of under 30 seconds for 100 submissions

## Architecture

This project follows a layered architecture, with structures as the domain layer, services as the application layer, and interfaces as the contract layer. [Link to Documentation](https://github.com/carrot2803/autograder/wiki)

[Class Diagram](https://github.com/carrot2803/autograder/wiki/Design#class-diagram)

## Package Structure

```sh
com.gophers         # Root package
.
├── interfaces      # contains all core system interfaces
|
├── services        # contains core business logic for system
|   ├── handlers    # operations for handling submissions and generating results
|   ├── helpers     # helpers for grade processing services
|   └── testCases   # testcase suite for evaluating submission quality
|
├── structures      # contains all structural entities within the project
|   ├── domain      # domain data models representing real world enitties
|   ├── factory     # contains grade factory classes for assigning grades
|   └── grades      # storage model to encapsulate scores per sub-section
|
└── utilities       # contains utility classes and functions
```

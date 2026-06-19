# rank-system

[![Maven](https://img.shields.io/badge/Build-Maven-007396)](pom.xml)
[![Java](https://img.shields.io/badge/Java-OpenJDK-orange)](pom.xml)
[![Status](https://img.shields.io/badge/Tests-Available-2ea043)](src/test/java/com/project/AppTest.java)

A small Java project demonstrating basic integer data structures and a small application entry point. The code is intended as an educational example and includes a binary search tree, a linked list, a queue interface and related utilities, plus a simple `App` driver and unit tests.

## Table of Contents

- [What This Project Does](#what-this-project-does)
- [Why This Project Is Useful](#why-this-project-is-useful)
- [Architecture Overview](#architecture-overview)
- [Instruction Set](#instruction-set)
- [Getting Started](#getting-started)
- [Usage Examples](#usage-examples)
- [Project Structure](#project-structure)
- [Where To Get Help](#where-to-get-help)
- [Maintainers](#maintainers)

## What This Project Does

`rank-system` implements several core integer data structures and a tiny application demonstrating them:

- `BinarySearchTreeOfInteger`: a binary search tree for `int` values with basic insert/search/remove semantics.
- `LinkedListOfInteger`: a simple singly-linked list implementation for integers.
- `Queue` and `EmptyQueueTreeOfInteger`: queue interface and an example queue implementation used by tests.
- `EmptyTreeException`: runtime exception used by tree operations.
- `App`: a small command-line driver that shows basic usage of the data structures.

There is a JUnit test suite in `src/test/java/com/project/AppTest.java` that verifies basic behaviour.

## Why This Project Is Useful

This repository is useful if you want to:

- Learn idiomatic implementations of common data structures in Java.
- See small, focused examples suitable for classroom exercises or interview practice.
- Reuse simple integer-based containers for algorithm experiments.

## Architecture Overview

The code is organized into straightforward single-responsibility classes under the package `com.project`:

- Data structures: `BinarySearchTreeOfInteger`, `LinkedListOfInteger`, queue implementations.
- Exceptions: `EmptyTreeException` for invalid tree operations.
- Application entry point: `App` demonstrates interactions and sample outputs.
- Tests: JUnit-based tests in `src/test/java/com/project`.

Execution model:

1. Create and populate data structure instances.
2. Exercise insert/search/remove or queue operations via the `App` driver or tests.
3. Observe behaviour through CLI output or test assertions.

## Instruction Set

This section documents the main public operations provided by each core class (high-level overview):

- `BinarySearchTreeOfInteger`: `insert(int)`, `remove(int)`, `contains(int)`, traversal helpers.
- `LinkedListOfInteger`: `add(int)`, `remove(int)`, `get(int)`, `size()`.
- `Queue` (interface): `enqueue(int)`, `dequeue()`, `peek()`, `isEmpty()`.

Refer to the individual source files for full API details and method signatures.

## Getting Started

### Prerequisites

- Java 11+ (OpenJDK or Oracle JDK)
- Apache Maven

### Build and run tests

From the repository root, run:

```bash
mvn -q test
```

### Compile and run the `App` driver

```bash
mvn -q -DskipTests package
java -cp target/classes com.project.App
```

The `App` class prints simple examples of using the bundled data structures.

## Usage Examples

1) Run the unit tests to validate behaviour:

```bash
mvn -q test
```

2) Run the `App` to see example output:

```bash
mvn -q -DskipTests package
java -cp target/classes com.project.App
```

## Project Structure

```text
.
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── project
    │               ├── App.java
    │               ├── BinarySearchTreeOfInteger.java
    │               ├── EmptyQueueTreeOfInteger.java
    │               ├── EmptyTreeException.java
    │               ├── LinkedListOfInteger.java
    │               └── Queue.java
    └── test
        └── java
            └── com
                └── project
                    └── AppTest.java
```

## Where To Get Help

- Start reading the application entry at [src/main/java/com/project/App.java](src/main/java/com/project/App.java).
- Inspect tests at [src/test/java/com/project/AppTest.java](src/test/java/com/project/AppTest.java) for usage examples.

## Maintainers

Maintainer:

- [@kydoa](https://github.com/kydoa)
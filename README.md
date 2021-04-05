# Guice-Demo

[![pipeline status](https://gitlab.com/christianpflugradt/guice-demo/badges/main/pipeline.svg)](https://gitlab.com/christianpflugradt/guice-demo/-/commits/main) [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-blue.svg)](https://creativecommons.org/share-your-work/licensing-considerations/compatible-licenses)

This project demonstrates the Dependency Injection capabilities of [Guice](https://github.com/google/guice).

## A brief introduction to Dependency Injection (DI)

DI is a design pattern to provide objects that another object depends on. That object does not instantiate these objects by itself, instead it only declares its dependencies.

Some advantages that come with DI are:
* loose coupling (dependencies can be declared as interfaces, implementations are passed by the DI framework)
* separation of concerns (constructing and using objects are separated)
* easier unit testing - dependencies can be passed as mocks
* reusability and flexibility, dependencies can be more easily exchanged or replaced

Of course DI also has some disadvantages:
* higher complexity, especially if used improperly 
* achieved by means such as reflexion, which can negatively impact IDE features like refactoring, call hierarchy etc.
* resolving dependencies at run time means that related errors will also occur at run time instead of compile time

Typical tasks of a DI framework:
* resolve dependencies (implementations of interfaces)
* find a specific implementation of an interface by name
* find a specific instance declared as dependency
* create an instance using a parametrized constructor
* create an instance using a factory
* apply a scope to a dependency (singleton, request scope...)
* inject a dependency as a field, method or constructor parameter

### How Guice does it

Guice is a lightweight alternative to DI in more heavyweight frameworks like Spring and the Java EE specification.

Dependencies are declared using annotations. Dependencies are applied to an object structure by retrieving an instance of that object from an **injector**. 

**Bindings** declare implementations of an interface. A **module** is a collection of bindings that is passed to the **injector** when resolving the dependencies of an object structure.

I use the term **object structure** to describe a structure of nested objects, each of them possibly declaring multiple dependencies. Guice will resolve all these dependencies simply by requesting the object root from the **injector**.

Each of the test cases linked below demonstrates a very concise use case of DI with Guice.

## How to use this demo project

1. Clone this repository
2. Run the test suite with gradle: `./gradlew clean test`

## Tests

* [field injection, method injection and constructor injection](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/simple/SimpleInterfaceTest.java)
* [inject an instance as singleton](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/simple/SimpleInterfaceTest.java) (same test class as above)
* [inject all implementations of an interface as a set](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/multiple/MultipleInterfaceTest.java)
* [inject an implementation by name](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/named/NamedInterfaceTest.java)
* [inject a specific instance of a class](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/instance/InstanceInterfaceTest.java)
* [inject an implementation without default constructor](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/constructor/ConstructorInterfaceTest.java)
* [inject an instance from a factory](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/factory/FactoryInterfaceTest.java)

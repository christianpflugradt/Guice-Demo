# Guice-Demo

[![pipeline status](https://gitlab.com/christianpflugradt/guice-demo/badges/main/pipeline.svg)](https://gitlab.com/christianpflugradt/guice-demo/-/commits/main) [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-blue.svg)](https://creativecommons.org/share-your-work/licensing-considerations/compatible-licenses)

This project demonstrates the Dependency Injection capabilities of [Guice](https://github.com/google/guice).

## How to use

1. Clone this repository
2. Run the test suite with gradle: `./gradlew clean test`

## Tests

* [field injection, method injection and constructor injection](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/simple/InterfaceSimpleTest.java)
* [inject an instance as singleton](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/simple/InterfaceSimpleTest.java) (same test class as above)
* [inject all implementations of an interface as a set](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/multiple/InterfaceMultipleTest.java)
* [inject an implementation by name](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/named/InterfaceNamedTest.java)
* [inject a specific instance of a class](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/instance/InterfaceInstanceTest.java)
* [inject an implementation without default constructor](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/constructor/InterfaceConstructorTest.java)
* [inject an instance from a factory](https://gitlab.com/christianpflugradt/guice-demo/-/blob/main/src/test/java/de/pflugradts/guicedemo/factory/InterfaceFactoryTest.java)

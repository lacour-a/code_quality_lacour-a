# Weather

An application which retrieves weather information from a given city for up to the next 5 days.

Usage: `java -jar weather.jar <city> [-j <number-of-days>]` 

## Build status
 - master: [![master](https://travis-ci.org/lacour-a/code_quality_lacour-a.svg?branch=master)](https://travis-ci.org/lacour-a/code_quality_lacour-a)
 - develop: [![develop](https://travis-ci.org/lacour-a/code_quality_lacour-a.svg?branch=develop)](https://travis-ci.org/lacour-a/code_quality_lacour-a)

## Contributing, getting started
### Building
This project uses [`gradle`](https://gradle.org/docs/) as its build system. To build `weather`, simply run `gradle build`.

### Directory structure
Source files are located in `src/main/java/weather` and tests files in `src/test/java/weather`.

### Testing
Code must be tested using [`JUnit`](https://junit.org/junit4/javadoc/latest/) and [`Jacoco`](http://www.jacoco.org/jacoco/trunk/doc/).

Example tests can be found in the `src/test/java/weather` folders.

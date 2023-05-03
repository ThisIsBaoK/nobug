# Introduction

**nobug** is a cross-platform and customizable bug-tracking system written in Java.

# Development

## Eclipse

The project uses the Eclipse project template, so you can run the project in debug mode in Eclipse.

## Terminal

Install google-java-format, java, and make.

Install JavaFX SDK.

Set environment variable `JAVAFX\_LIB\_PATH` to the JavaFX bin folder (containing JavaFX .jar files) path.
Make sure to use forward slashes for path separators.
Here is the example: `JAVAFX\_LIB\_PATH = C:/Users/username/javafx-sdk-20/lib`

Set environment variable `JAVA_FMT` to the google-java-format invocation command.
Here is the example: `JAVA_FMT = java -jar C:/Users/username/java-tools/google-java-format-1.17.0-all-deps.jar`

Run the application:

    make run

Format the codebase:

    make fmt

Clean artifacts:

    make clean

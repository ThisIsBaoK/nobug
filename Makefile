# Make sure you have added these environemnt variables:
#     JAVAFX_LIB_PATH: path to JavaFX .jar files
#     JDBC_LIB_PATH: path to MySQL connector .jar file.
#     JAVA_FMT: google-java-format command
#
# Make sure you have these commands: make, rm, cp 

ifeq ($(OS),Windows_NT)
    OS_DETECTED = Windows
else
    OS_DETECTED = $(shell uname -s)
endif

ifeq ($(OS_DETECTED),Windows)
DELIM = ;
else ifeq ($(OS_DETECTED),Darwin)
DELIM = :
endif

ADDED_MODULES = javafx.controls,javafx.fxml
JAVAC_FLAGS = --module-path $(JAVAFX_LIB_PATH) --add-modules $(ADDED_MODULES)
JAVA_FLAGS = -Dfile.encoding=UTF-8 -p "./bin$(DELIM)$(JAVAFX_LIB_PATH)/javafx.base.jar$(DELIM)$(JAVAFX_LIB_PATH)/javafx.controls.jar$(DELIM)$(JAVAFX_LIB_PATH)/javafx.fxml.jar$(DELIM)$(JAVAFX_LIB_PATH)/javafx.graphics.jar$(DELIM)$(JAVAFX_LIB_PATH)/javafx.media.jar$(DELIM)$(JAVAFX_LIB_PATH)/javafx.swing.jar$(DELIM)$(JAVAFX_LIB_PATH)/javafx.web.jar$(DELIM)$(JDBC_LIB_PATH)"


.PHONY: run compile fmt clean

run: compile
	java $(JAVA_FLAGS) -m nobug/application.Main

compile:
	javac $(JAVAC_FLAGS) -d bin/ -cp src src/application/Main.java
	javac $(JAVAC_FLAGS) -d bin/ -cp src src/module-info.java
	cp -r src/views bin/

fmt:
	$(JAVA_FMT) -i src/**/*.java

clean:
	rm -rf bin

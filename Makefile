# Make sure you have added these environemnt variables:
#     JAVAFX_LIB_PATH: path to JavaFX .jar files
#     JAVA_FMT: google-java-format command

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

FX_PATH = $(JAVAFX_LIB_PATH)
ADDED_MODULES = javafx.controls,javafx.fxml
JAVAC_FLAGS = --module-path $(FX_PATH) --add-modules $(ADDED_MODULES)

JAVA_FLAGS = --module-path $(FX_PATH) --add-modules $(ADDED_MODULES) -Dfile.encoding=UTF-8 -classpath "./bin$(DELIM)$(FX_PATH)/javafx.base.jar$(DELIM)$(JAVA_FX)/javafx.controls.jar$(DELIM)$(JAVA_FX)/javafx.fxml.jar$(DELIM)$(JAVA_FX)/javafx.graphics.jar$(DELIM)$(JAVA_FX)/javafx.media.jar$(DELIM)$(JAVA_FX)/javafx.swing.jar$(DELIM)$(JAVA_FX)/javafx.web.jar"

.PHONY: run compile fmt clean

run: compile
	java $(JAVA_FLAGS) application.Main

compile:
	javac $(JAVAC_FLAGS) -d bin/ -cp src src/application/Main.java

fmt:
	$(JAVA_FMT) -i src/**/*.java

clean:
	rm -rf bin

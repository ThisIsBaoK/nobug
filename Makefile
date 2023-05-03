# JAVAFX_LIB_PATH is the path to the javafx .jar files. The path should use forward slash.
FX_PATH = $(JAVAFX_LIB_PATH)
ADDED_MODULES = javafx.controls,javafx.fxml
JAVAC_FLAGS = --module-path $(FX_PATH) --add-modules $(ADDED_MODULES)
JAVA_FLAGS = --module-path $(FX_PATH) --add-modules $(ADDED_MODULES) -Dfile.encoding=UTF-8 -classpath "../nobug/bin:$(FX_PATH)/javafx.base.jar:$(JAVA_FX)/javafx.controls.jar:$(JAVA_FX)/javafx.fxml.jar:$(JAVA_FX)/javafx.graphics.jar:$(JAVA_FX)/javafx.media.jar:$(JAVA_FX)/javafx.swing.jar:$(JAVA_FX)/javafx.web.jar" -XX:+ShowCodeDetailsInExceptionMessages

.PHONY: run fmt clean

run: compile
	java $(JAVA_FLAGS) application.Main

compile:
	javac $(JAVAC_FLAGS) -d bin/ -cp src src/application/Main.java

fmt:
	$(JAVA_FMT) -i src/**/*.java

clean:
	rm -rf bin

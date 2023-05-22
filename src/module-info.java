module nobug {
  requires transitive javafx.controls;
  requires transitive java.sql;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.base;

  opens application to javafx.graphics, javafx.fxml;
  exports application;

  opens controllers to javafx.graphics, javafx.fxml;
  exports controllers;

  opens models to javafx.graphics, javafx.fxml;
  exports models;

  opens common to javafx.graphics, javafx.fxml;
  exports common;
}

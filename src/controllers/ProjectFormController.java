package controllers;

import application.ProjectStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProjectFormController {
  @FXML private VBox container;
  @FXML private Label errorMessage;
  @FXML private TextField title;
  @FXML private TextArea description;
  @FXML private ChoiceBox<ProjectStatus> status;
  @FXML private Button submit;

  public void initialize() {
    status.getItems().setAll(ProjectStatus.values());
    status.setValue(ProjectStatus.ARCHIVED);
  }

  public VBox getContainer() {
    return container;
  }

  public void setErrorMessage(String message) {
    errorMessage.setText(message);
  }

  public String getTitleText() {
    return title.getText();
  }

  public String getDescriptionText() {
    return description.getText();
  }

  public void setTitleText(String text) {
    title.setText(text);
  }

  public void setDescriptionText(String text) {
    description.setText(text);
  }

  public void clear() {
    title.clear();
    description.clear();
    errorMessage.setText("");
  }

  public Button getSubmit() {
    return submit;
  }

  public ProjectStatus getStatus() {
    return status.getValue();
  }
}

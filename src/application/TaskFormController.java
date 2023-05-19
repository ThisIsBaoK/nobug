package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TaskFormController {
  @FXML private VBox container;
  @FXML private TextField author;
  @FXML private TextField assigned;
  @FXML private TextField title;
  @FXML private TextArea description;
  @FXML private TextField project;
  @FXML private ChoiceBox<TaskStatus> status;
  @FXML private Label errorMessage;
  @FXML private Button submit;

  public void initialize() {
    status.getItems().setAll(TaskStatus.values());
  }

  public void clear() {
    author.clear();
    assigned.clear();
    title.clear();
    description.clear();
    project.clear();
    status.setValue(TaskStatus.TODO);
    errorMessage.setText("");
  }

  public VBox getContainer() {
    return container;
  }

  public String getAuthorText() {
    return author.getText();
  }

  public String getAssignedText() {
    return assigned.getText();
  }

  public String getTitleText() {
    return title.getText();
  }

  public String getDescriptionText() {
    return description.getText();
  }

  public TaskStatus getTaskStatus() {
    return status.getValue();
  }

  public String getProjectText() {
    return project.getText();
  }

  public void setTaskStatus(TaskStatus status) {
    this.status.setValue(status);
  }

  public void setErrorMessage(String message) {
    errorMessage.setText(message);
  }

  public Button getSubmit() {
    return submit;
  }
}

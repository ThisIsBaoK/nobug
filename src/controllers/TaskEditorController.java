package controllers;

import application.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TaskEditorController {
  @FXML private VBox container;
  @FXML private Label id;
  @FXML private TextField author;
  @FXML private TextField assigned;
  @FXML private TextField title;
  @FXML private TextArea description;
  @FXML private TextField project;
  @FXML private Label errorMessage;
  @FXML private Button delete;
  @FXML private Button submit;
  private Task task;

  public void setTask(Task task) {
    this.task = task;
    id.setText(String.valueOf(task.getID()));
    author.setText(task.getAuthor());
    assigned.setText(task.getAssigned());
    title.setText(task.getTitle());
    description.setText(task.getDescription());
    project.setText(String.valueOf(task.getProject()));
    errorMessage.setText("");
  }

  public int getTaskID() {
    return task.getID();
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

  public String getProjectText() {
    return project.getText();
  }

  public void setErrorMessage(String message) {
    errorMessage.setText(message);
  }

  public Button getDelete() {
    return delete;
  }

  public Button getSubmit() {
    return submit;
  }
}

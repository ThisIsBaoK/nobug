package application;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
  private TaskFlowController parentController;

  public void initialize() {
    status.getItems().setAll(TaskStatus.values());
  }

  public void submit() {
    parentController.generateTaskFromTaskForm();
    parentController.closeTaskFormStage();
  }

  public void clear() {
    title.clear();
    description.clear();
    status.setValue(TaskStatus.TODO);
  }

  public VBox getContainer() {
    return container;
  }

  public String getAuthor() {
    return author.getText();
  }

  public String getAssigned() {
    return assigned.getText();
  }

  public String getTitle() {
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

  public void setParentController(TaskFlowController parentController) {
    this.parentController = parentController;
  }
}

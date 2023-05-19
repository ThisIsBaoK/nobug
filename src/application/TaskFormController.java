package application;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TaskFormController {
  @FXML private VBox container;
  @FXML private TextField title;
  @FXML private TextArea description;
  @FXML private ChoiceBox<TaskStatus> status;
  private TaskFlowController parentController;

  public void init() {
    status.getItems().setAll(TaskStatus.values());
  }

  public void submit() {
    parentController.generateTaskFromTaskForm();
  }

  public void clear() {
    title.clear();
    description.clear();
    status.setValue(TaskStatus.TODO);
  }

  public VBox getContainer() {
    return container;
  }

  public String getTitle() {
    return title.getText();
  }

  public String getDescription() {
    return description.getText();
  }

  public TaskStatus getTaskStatus() {
    return status.getValue();
  }

  public void setTaskStatus(TaskStatus status) {
    this.status.setValue(status);
  }

  public void setParentController(TaskFlowController parentController) {
    this.parentController = parentController;
  }
}

package application;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskFlowController {
  @FXML private HBox container;
  @FXML private VBox todoContainer;
  @FXML private VBox inprogressContainer;
  @FXML private VBox doneContainer;

  private TaskFormController taskFormController;

  private Stage stage;

  public void init() {
    System.out.println("TaskFlowController.init");
    container.addEventHandler(
        UserEvent.TASK_FORM_SUBMITTED,
        event -> {
          System.out.println("Form submitted"); // TODO: delete me.
        });
  }

  public HBox getContainer() {
    return container;
  }

  public VBox getTodoContainer() {
    return todoContainer;
  }

  public VBox getInprogressContainer() {
    return inprogressContainer;
  }

  public VBox getDoneContainer() {
    return doneContainer;
  }

  public void displayTodoTaskForm() {
    displayTaskForm(TaskStatus.TODO);
  }

  public void displayInprogressTaskForm() {
    displayTaskForm(TaskStatus.INPROGRESS);
  }

  public void displayDoneTaskForm() {
    displayTaskForm(TaskStatus.DONE);
  }

  public void displayTaskForm(TaskStatus status) {
    stage.show();
    stage.toFront();
  }

  public void setTaskFormController(TaskFormController taskFormController) {
    this.taskFormController = taskFormController;
    stage = new Stage();
    stage.setTitle("Task Form");
    Scene scene = new Scene(this.taskFormController.getContainer());
    stage.setScene(scene);
  }
}

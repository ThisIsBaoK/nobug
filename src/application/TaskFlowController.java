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

  private Stage taskFormStage;

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
    taskFormStage.show();
    taskFormStage.toFront();
    taskFormController.clear();
    taskFormController.setTaskStatus(status);
  }

  public void setTaskFormController(TaskFormController taskFormController) {
    this.taskFormController = taskFormController;
    taskFormStage = new Stage();
    taskFormStage.setTitle("Task Form");
    Scene scene = new Scene(this.taskFormController.getContainer());
    taskFormStage.setScene(scene);
  }

  public void generateTaskFromTaskForm() {
    User user = new User("first", "last", "email@mail.com", "123");
    Project project =
        new Project("nobug", "A bug tracking system in Java and JavaFX", ProjectStatus.ACTIVE);
    Task task =
        new Task(
            user,
            user,
            taskFormController.getTitle(),
            taskFormController.getDescription(),
            project,
            taskFormController.getTaskStatus());
    new TaskController(todoContainer, inprogressContainer, doneContainer, task);
  }

  public void closeTaskFormStage() {
    taskFormStage.close();
  }
}

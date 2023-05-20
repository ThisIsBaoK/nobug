package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
  private Backend backend;
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
    taskFormStage.setTitle("Project Form");
    Scene scene = new Scene(this.taskFormController.getContainer());
    taskFormStage.setScene(scene);
    taskFormController
        .getSubmit()
        .setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                submitTaskForm();
              }
            });
  }

  public void setBackend(Backend backend) {
    this.backend = backend;
  }

  public void submitTaskForm() {
    String author = taskFormController.getAuthorText();
    String assigned = taskFormController.getAssignedText();
    String title = taskFormController.getTitleText();
    String description = taskFormController.getDescriptionText();
    int project;
    // Author.
    if (author.length() == 0) {
      taskFormController.setErrorMessage("Author must be at least one character");
      return;
    }
    if (author.length() >= 45) {
      taskFormController.setErrorMessage("Author must be less than 45 characters");
      return;
    }
    // Assigned.
    if (assigned.length() >= 45) {
      taskFormController.setErrorMessage("Assigned must be less than 45 characters");
      return;
    }
    // Title.
    if (title.length() == 0) {
      taskFormController.setErrorMessage("Title must be at least one character");
      return;
    }
    if (title.length() >= 255) {
      taskFormController.setErrorMessage("Title must be less than 255 characters");
      return;
    }
    // Description.
    if (description.length() == 0) {
      taskFormController.setErrorMessage("Description must be at least one character");
      return;
    }
    if (description.length() >= 2000) {
      taskFormController.setErrorMessage("Description must be less than 2000 characters");
      return;
    }
    // Project.
    try {
      project = Integer.parseInt(taskFormController.getProjectText());
    } catch (NumberFormatException e) {
      taskFormController.setErrorMessage("Project must be a positive integer");
      return;
    }
    // Check if author matches with any user.
    try {
      if (!backend.userExists(author)) {
        taskFormController.setErrorMessage("Author does not match with any user");
        return;
      }
      if (assigned.length() > 0) {
        if (!backend.userExists(assigned)) {
          taskFormController.setErrorMessage("Assigned does not match with any user");
          return;
        }
      }
      if (!backend.projectExists(project)) {
        taskFormController.setErrorMessage("Project does not match with any project");
        return;
      }
    } catch (MyException e) {
      taskFormController.setErrorMessage("Failed to query database");
      return;
    }
    TaskStatus status = taskFormController.getTaskStatus();
    // Append a new task.
    Task task = new Task(author, assigned, title, description, project, status);
    try {
      new TaskController(todoContainer, inprogressContainer, doneContainer, backend, task);
    } catch (MyException e) {
      System.out.println("update issue tables: " + e);
      taskFormController.setErrorMessage("Failed to update database");
      return;
    }
    // Close the form.
    taskFormStage.close();
  }
}

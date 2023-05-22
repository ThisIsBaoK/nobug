package controllers;

import common.Backend;
import common.MyException;
import common.Utility;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Task;
import models.TaskStatus;

public class TaskFlowController {
  @FXML private VBox container;
  @FXML private VBox todoContainer;
  @FXML private VBox inprogressContainer;
  @FXML private VBox doneContainer;
  @FXML private Label errorMessage;
  private Backend backend;
  private TaskFormController taskFormController;
  private Stage taskFormStage;
  private TaskEditorController taskEditorController;
  private Stage taskEditorStage;
  private TaskController taskInEdit;

  public VBox getContainer() {
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

  public void displayEditorForm(Task task) {
    taskEditorController.setTask(task);
    taskEditorStage.show();
    taskEditorStage.toFront();
  }

  public void setTaskFormController(TaskFormController taskFormController) {
    this.taskFormController = taskFormController;
    taskFormStage = new Stage();
    taskFormStage.setTitle("Create New Issue");
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

  public void setTaskEditorController(TaskEditorController taskEditorController) {
    this.taskEditorController = taskEditorController;
    taskEditorStage = new Stage();
    taskEditorStage.setTitle("Edit Issue");
    Scene scene = new Scene(this.taskEditorController.getContainer());
    taskEditorStage.setScene(scene);
    taskEditorController
        .getDelete()
        .setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                deleteTask();
              }
            });
    taskEditorController
        .getSubmit()
        .setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                saveTaskChange();
              }
            });
  }

  public void deleteTask() {
    try {
      taskInEdit.deleteTask();
    } catch (MyException e) {
      System.out.println("update issue table: " + e);
      taskEditorController.setErrorMessage("Failed to update database");
    }
    taskEditorStage.close();
  }

  public void saveTaskChange() {
    String author = taskEditorController.getAuthorText();
    String assigned = taskEditorController.getAssignedText();
    String title = taskEditorController.getTitleText();
    String description = taskEditorController.getDescriptionText();
    int project;
    // Author.
    if (author.length() == 0) {
      taskEditorController.setErrorMessage("Author must be at least one character");
      return;
    }
    if (author.length() >= 45) {
      taskEditorController.setErrorMessage("Author must be less than 45 characters");
      return;
    }
    // Assigned.
    if (assigned.length() >= 45) {
      taskEditorController.setErrorMessage("Assigned must be less than 45 characters");
      return;
    }
    // Title.
    if (title.length() == 0) {
      taskEditorController.setErrorMessage("Title must be at least one character");
      return;
    }
    if (title.length() >= 255) {
      taskEditorController.setErrorMessage("Title must be less than 255 characters");
      return;
    }
    // Description.
    if (description.length() == 0) {
      taskEditorController.setErrorMessage("Description must be at least one character");
      return;
    }
    if (description.length() >= 2000) {
      taskEditorController.setErrorMessage("Description must be less than 2000 characters");
      return;
    }
    // Project.
    try {
      project = Integer.parseInt(taskEditorController.getProjectText());
    } catch (NumberFormatException e) {
      taskEditorController.setErrorMessage("Project must be a positive integer");
      return;
    }
    // Check if author matches with any user.
    try {
      if (!backend.userExists(author)) {
        taskEditorController.setErrorMessage("Author does not match with any user");
        return;
      }
      if (assigned.length() > 0) {
        if (!backend.userExists(assigned)) {
          taskEditorController.setErrorMessage("Assigned does not match with any user");
          return;
        }
      }
      if (!backend.projectExists(project)) {
        taskEditorController.setErrorMessage("Project does not match with any project");
        return;
      }
    } catch (MyException e) {
      taskEditorController.setErrorMessage("Failed to query database");
      return;
    }
    TaskStatus status = taskInEdit.getTask().getStatus();
    // Update task.
    try {
      backend.updateTask(
          taskEditorController.getTaskID(),
          author,
          assigned,
          title,
          description,
          project,
          status.toString());
    } catch (MyException e) {
      System.out.println("update issue tables: " + e);
      taskEditorController.setErrorMessage("Failed to update database");
      return;
    }
    taskInEdit.updateTask(author, assigned, title, description, project, status);
    taskEditorStage.close();
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
    try {
      int taskID =
          backend.addTask(author, assigned, title, description, project, status.toString());
      Task task = new Task(taskID, author, assigned, title, description, project, status);
      createNewTaskController(task);
    } catch (MyException e) {
      System.out.println("update issue tables: " + e);
      taskFormController.setErrorMessage("Failed to update database");
      return;
    }
    // Close the form.
    taskFormStage.close();
  }

  public TaskController createNewTaskController(Task task) throws MyException {
    TaskController taskController =
        new TaskController(
            todoContainer, inprogressContainer, doneContainer, errorMessage, backend, task);
    bindTaskController(taskController);
    return taskController;
  }

  public void loadTasksFromDatabase() {
    try {
      ResultSet rs = backend.readAllTasks();
      // Populate tasks.
      while (rs.next()) {
        Task task =
            new Task(
                rs.getInt(1),
                rs.getString(2),
                Utility.toNonNullString(rs.getString(3)),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                TaskStatus.valueOf(rs.getString(7)));
        createNewTaskController(task);
      }
    } catch (Exception e) {
      System.out.println(e);
      errorMessage.setText("Failed to query database");
    }
  }

  public void bindTaskController(TaskController taskController) {
    Hyperlink heading = taskController.getHeading();
    heading.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            taskInEdit = taskController;
            displayEditorForm(taskController.getTask());
          }
        });
  }
}

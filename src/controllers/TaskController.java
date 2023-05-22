package controllers;

import common.Backend;
import common.MyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.Task;
import models.TaskStatus;

public class TaskController {
  private VBox container;
  private VBox todoParentContainer;
  private VBox inprogressParentContainer;
  private VBox doneParentContainer;
  private VBox parentContainer;
  private Hyperlink heading;
  private Label assignment;
  private Label errorMessage;
  private Task task;
  private Backend backend;

  public TaskController(
      VBox todoParentContainer,
      VBox inprogressParentContainer,
      VBox doneParentContainer,
      Label errorMessage,
      Backend backend,
      Task task)
      throws MyException {

    this.todoParentContainer = todoParentContainer;
    this.inprogressParentContainer = inprogressParentContainer;
    this.doneParentContainer = doneParentContainer;
    this.errorMessage = errorMessage;
    this.backend = backend;
    this.task = task;
    switch (task.getStatus()) {
      case TODO:
        parentContainer = todoParentContainer;
        break;
      case INPROGRESS:
        parentContainer = inprogressParentContainer;
        break;
      case DONE:
        parentContainer = doneParentContainer;
        break;
    }
    heading = new Hyperlink("#" + task.getID() + " " + task.getTitle());
    heading.setWrapText(true);
    assignment = new Label("Assigned: ");
    if (task.getAssigned() != null) {
      assignment.setText("Assigned: " + task.getAssigned());
    }
    Button button = new Button(">");
    button.setStyle("-fx-font-size: 8;");
    button.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            switchPane();
          }
        });
    container = new VBox(heading, assignment, button);
    container.setSpacing(2);
    container.setPadding(new Insets(5));
    container.setStyle("-fx-background-color: #FFFFFF;");
    parentContainer.getChildren().add(container);
  }

  public void updateTask(
      String author,
      String assigned,
      String title,
      String description,
      int project,
      TaskStatus status) {
    task.setAuthor(author);
    task.setAssigned(assigned);
    task.setTitle(title);
    task.setDescription(description);
    task.setProject(project);
    task.setStatus(status);
    this.heading.setText("#" + task.getID() + " " + task.getTitle());
    assignment.setText("Assigned: ");
    if (task.getAssigned() != null) {
      assignment.setText("Assigned: " + task.getAssigned());
    }
  }

  public void switchPane() {
    parentContainer.getChildren().remove(container);
    TaskStatus nextStatus;
    if (parentContainer == todoParentContainer) {
      nextStatus = TaskStatus.INPROGRESS;
    } else if (parentContainer == inprogressParentContainer) {
      nextStatus = TaskStatus.DONE;
    } else {
      nextStatus = TaskStatus.TODO;
    }
    try {
      backend.updateTaskStatus(task.getID(), nextStatus.toString());
    } catch (MyException e) {
      System.out.println("update task table: " + e);
      errorMessage.setText("Failed to update database");
      return;
    }
    if (parentContainer == todoParentContainer) {
      parentContainer = inprogressParentContainer;
    } else if (parentContainer == inprogressParentContainer) {
      parentContainer = doneParentContainer;
    } else {
      parentContainer = todoParentContainer;
    }
    parentContainer.getChildren().add(container);
    task.setStatus(nextStatus);
    errorMessage.setText("");
  }

  public Hyperlink getHeading() {
    return heading;
  }

  public Task getTask() {
    return task;
  }

  public void deleteTask() throws MyException {
    backend.deleteTask(task.getID());
    parentContainer.getChildren().remove(container);
  }
}

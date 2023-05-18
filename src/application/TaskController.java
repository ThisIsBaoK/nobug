package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TaskController {
  private VBox container;
  private VBox todoParentContainer;
  private VBox inprogressParentContainer;
  private VBox doneParentContainer;
  private VBox parentContainer;

  public TaskController(
      VBox todoParentContainer,
      VBox inprogressParentContainer,
      VBox doneParentContainer,
      Task task) {
    this.todoParentContainer = todoParentContainer;
    this.inprogressParentContainer = inprogressParentContainer;
    this.doneParentContainer = doneParentContainer;
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
    Label label = new Label(task.getTitle());
    label.setWrapText(true);
    Button button = new Button(">");
    button.setStyle("-fx-font-size: 8;");
    button.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            switchPane();
          }
        });
    container = new VBox(label, button);
    container.setSpacing(5);
    container.setPadding(new Insets(5));
    container.setStyle("-fx-background-color: #FFFFFF;");
    parentContainer.getChildren().add(container);
  }

  public void switchPane() {
    parentContainer.getChildren().remove(container);
    if (parentContainer == todoParentContainer) {
      parentContainer = inprogressParentContainer;
    } else if (parentContainer == inprogressParentContainer) {
      parentContainer = doneParentContainer;
    } else {
      parentContainer = todoParentContainer;
    }
    parentContainer.getChildren().add(container);
  }
}

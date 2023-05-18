package application;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TaskFlowController {
  @FXML private HBox container;
  @FXML private VBox todoContainer;
  @FXML private VBox inprogressContainer;
  @FXML private VBox doneContainer;

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
}

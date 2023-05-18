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

  public void init() {
    status.getItems().setAll(TaskStatus.values());
  }

  public void submit() {
    UserEvent event = new UserEvent(UserEvent.TASK_FORM_SUBMITTED);
    container.fireEvent(event);
    System.out.println("TaskFormController.submit: run"); // TODO: delete me.
  }

  public VBox getContainer() {
    return container;
  }
}

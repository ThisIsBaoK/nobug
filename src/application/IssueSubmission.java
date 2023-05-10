package application;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class IssueSubmission {
  public String sectionTitle;
  public VBox pane;
  public ChoiceBox<String> priorities;
  public TextField title;
  public TextArea description;
  public Button button;

  public IssueSubmission() {
    this.sectionTitle = "Issue Submission";
    this.priorities = new ChoiceBox<>();
    this.priorities.getItems().add("Severe");
    this.priorities.getItems().add("High");
    this.priorities.getItems().add("Medium");
    this.priorities.getItems().add("Low");
    this.priorities.setValue("High");
    this.title = new TextField();
    this.description = new TextArea();
    this.button = new Button("Submit");
    this.button.setOnAction((evt) -> {
      System.out
          .println("Priority: " + this.priorities.getValue() + ", Title: " + this.title.getText());
    });

    HBox titleBox = new HBox(new Label("Title: "), title);
    titleBox.setSpacing(UIConfig.pad2);
    HBox prioritiesBox = new HBox(new Label("Severity: "), priorities);
    prioritiesBox.setSpacing(UIConfig.pad2);
    VBox descriptionBox = new VBox(new Label("Description: "), this.description);
    descriptionBox.setSpacing(UIConfig.pad2);

    this.pane = new VBox(titleBox, prioritiesBox, descriptionBox, button);
    this.pane.setSpacing(UIConfig.pad2);
  }
  
  
}

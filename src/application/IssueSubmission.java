package application;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class IssueSubmission {
  public String sectionTitle;
  public VBox pane;
  public ChoiceBox<String> packages;
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
    this.packages = new ChoiceBox<>();
    this.packages.getItems().add("nobug");
    this.packages.getItems().add("netcalc");
    this.title = new TextField();
    this.title.setPrefColumnCount(24);
    this.description = new TextArea();
    this.button = new Button("Submit");
    this.button.setOnAction(
        (evt) -> {
          System.out.println(
              "Priority: " + this.priorities.getValue() + ", Title: " + this.title.getText());
        });

    Text heading = new Text("Submit New Issue");
    heading.setFont(UIConfig.heading1Font);
    HBox titleBox = new HBox(new Label("Title: "), title);
    titleBox.setSpacing(UIConfig.pad2);
    HBox packagesBox = new HBox(new Label("Package: "), this.packages);
    packagesBox.setSpacing(UIConfig.pad2);
    HBox prioritiesBox = new HBox(new Label("Severity: "), priorities);
    prioritiesBox.setSpacing(UIConfig.pad2);
    VBox descriptionBox = new VBox(new Label("Description: "), this.description);
    descriptionBox.setSpacing(UIConfig.pad2);

    this.pane = new VBox(heading, titleBox, packagesBox, prioritiesBox, descriptionBox, button);
    this.pane.setSpacing(UIConfig.pad2);
  }
}

package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavigationController {
  @FXML private VBox tabContainer;
  @FXML private MenuItem helpLogout;
  @FXML private Button issueTracker;
  @FXML private Button projects;
  @FXML private Button people;
  private Button selected;
  private Stage stage;
  private Parent taskFlowPage;
  private Parent selectedPage;
  private Parent projectPage;
  private Parent peoplePage;

  public void initialize() {
    selected = issueTracker;
    issueTracker.setStyle("-fx-background-color: #000000;");
  }

  public void switchToIssueTracker() {
    if (selected != null) {
      selected.setStyle("-fx-background-color: transparent;");
    }
    if (selectedPage != null) {
      tabContainer.getChildren().remove(selectedPage);
    }
    issueTracker.setStyle("-fx-background-color: #000000;");
    selected = issueTracker;
    selectedPage = taskFlowPage;
    tabContainer.getChildren().add(selectedPage);
  }

  public void switchToPeople() {
    if (selected != null) {
      selected.setStyle("-fx-background-color: transparent;");
    }
    if (selectedPage != null) {
      tabContainer.getChildren().remove(selectedPage);
    }
    people.setStyle("-fx-background-color: #000000;");
    selected = people;
    selectedPage = peoplePage;
    tabContainer.getChildren().add(selectedPage);
  }

  public void switchToProjects() {
    if (selected != null) {
      selected.setStyle("-fx-background-color: transparent;");
    }
    if (selectedPage != null) {
      tabContainer.getChildren().remove(selectedPage);
    }
    projects.setStyle("-fx-background-color: #000000;");
    selected = projects;
    selectedPage = projectPage;
    tabContainer.getChildren().add(selectedPage);
  }

  public VBox getTabContainer() {
    return this.tabContainer;
  }

  public MenuItem getHelpLogout() {
    return helpLogout;
  }

  public void openAboutWindow() {
    Stage newWindow = new Stage();
    newWindow.setTitle("About");
    Label content = new Label(SoftwareInfo.ABOUT);
    content.setWrapText(true);
    VBox container = new VBox(content);
    container.setPadding(new Insets(5));
    container.setAlignment(Pos.CENTER);
    newWindow.setScene(new Scene(container));
    newWindow.show();
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void resetWindowSize() {
    this.stage.setWidth(800);
    this.stage.setHeight(600);
  }

  public void setTaskFlow(Parent page) {
    this.taskFlowPage = page;
    selectedPage = page;
  }

  public void setProject(Parent page) {
    this.projectPage = page;
  }

  public void setPeople(Parent page) {
    this.peoplePage = page;
  }
}

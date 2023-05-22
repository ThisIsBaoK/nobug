package controllers;

import java.sql.ResultSet;
import application.Backend;
import application.MyException;
import application.ProjectStatus;
import application.ProjectsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectController {
  @FXML private VBox container;
  @FXML private TableView<ProjectsModel> tableview;
  @FXML private TableColumn<ProjectsModel, Integer> projectID;
  @FXML private TableColumn<ProjectsModel, String> title;
  @FXML private TableColumn<ProjectsModel, String> description;
  @FXML private TableColumn<ProjectsModel, String> status;
  @FXML private Label errorMessage;
  private Backend backend;
  private ProjectFormController projectFormController;
  private Stage projectFormStage;
  private ObservableList<ProjectsModel> projectsModels = FXCollections.observableArrayList();

  public void initialize() {
    projectID.setCellValueFactory(new PropertyValueFactory<>("projectID"));
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    status.setCellValueFactory(new PropertyValueFactory<>("status"));
    tableview.setItems(projectsModels);
  }

  public VBox getContainer() {
    return container;
  }

  public void setBackend(Backend backend) {
    this.backend = backend;
  }

  public void updateTableFromDatabase() {
    projectsModels.clear();
    ResultSet rs;
    try {
      rs = backend.readAllProjects();
      while (rs.next()) {
        ProjectsModel projectsModel =
            new ProjectsModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        projectsModels.add(projectsModel);
      }
    } catch (Exception e) {
      System.out.println("update project table view: " + e);
      errorMessage.setText("failed to update table view");
    }
  }

  public void setProjectFormController(ProjectFormController projectFormController) {
    this.projectFormController = projectFormController;
    projectFormStage = new Stage();
    projectFormStage.setTitle("Task Form");
    Scene scene = new Scene(this.projectFormController.getContainer());
    projectFormStage.setScene(scene);
    projectFormController
        .getSubmit()
        .setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                submitTaskForm();
              }
            });
  }

  public void displayProjectForm() {
    projectFormStage.show();
    projectFormStage.toFront();
    projectFormController.clear();
  }

  public void submitTaskForm() {
    String title = projectFormController.getTitleText();
    String description = projectFormController.getDescriptionText();
    ProjectStatus status = projectFormController.getStatus();
    // Title.
    if (title.length() == 0) {
      projectFormController.setErrorMessage("Title must be at least one character");
      return;
    }
    if (title.length() >= 255) {
      projectFormController.setErrorMessage("Title must be less than 255 characters");
      return;
    }
    // Description.
    if (description.length() == 0) {
      projectFormController.setErrorMessage("Description must be at least one character");
      return;
    }
    if (description.length() >= 2000) {
      projectFormController.setErrorMessage("Description must be less than 2000 characters");
      return;
    }
    try {
      backend.addProject(title, description, status.toString());
    } catch (MyException e) {
      System.out.println("update project table: " + e);
      projectFormController.setErrorMessage("Failed to update database");
      return;
    }
    // Update the table.
    updateTableFromDatabase();
    // Close the form.
    projectFormStage.close();
  }
}

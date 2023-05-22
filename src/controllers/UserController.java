package controllers;

import common.Backend;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.UsersModel;

public class UserController {
  @FXML private VBox container;
  @FXML private TableView<UsersModel> tableview;
  @FXML private TableColumn<UsersModel, String> email;
  @FXML private TableColumn<UsersModel, String> firstName;
  @FXML private TableColumn<UsersModel, String> lastName;
  @FXML private Label errorMessage;
  private Backend backend;
  private ObservableList<UsersModel> usersModels = FXCollections.observableArrayList();

  public void initialize() {
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    tableview.setItems(usersModels);
  }

  public VBox getContainer() {
    return container;
  }

  public void setBackend(Backend backend) {
    this.backend = backend;
  }

  public void updateTableFromDatabase() {
    usersModels.clear();
    ResultSet rs;
    try {
      rs = backend.readAllUsers();
      while (rs.next()) {
        UsersModel usersModel = new UsersModel(rs.getString(1), rs.getString(3), rs.getString(4));
        usersModels.add(usersModel);
      }
    } catch (Exception e) {
      System.out.println("update project table view: " + e);
      errorMessage.setText("failed to update table view");
    }
  }
}

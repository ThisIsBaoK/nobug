package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage stage) {
    try {
      // Create application menu.
      FXMLLoader navigationLoader =
          new FXMLLoader(getClass().getResource("/application/Navigation.fxml"));
      Scene menuScene = new Scene(navigationLoader.load());
      NavigationController navigationController = navigationLoader.getController();
      navigationController.setStage(stage);

      // Create pages.
      VBox tabContainer = navigationController.getTabContainer();

      // Task Flow page.
      FXMLLoader taskFlowLoader =
          new FXMLLoader(getClass().getResource("/application/TaskFlow.fxml"));
      taskFlowLoader.load();
      TaskFlowController taskFlowController = taskFlowLoader.getController();
      System.out.println(taskFlowController);
      tabContainer.getChildren().add(taskFlowController.getContainer());
      VBox.setVgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      HBox.setHgrow(taskFlowController.getContainer(), Priority.ALWAYS);

      // Generate fake data.
      new TaskController(
          "Implement the Login page",
          taskFlowController.getTodoContainer(),
          taskFlowController.getInprogressContainer(),
          taskFlowController.getDoneContainer(),
          0);
      new TaskController(
          "Implement the Task Flow page",
          taskFlowController.getTodoContainer(),
          taskFlowController.getInprogressContainer(),
          taskFlowController.getDoneContainer(),
          0);
      new TaskController(
          "Merge the Task Flow and the Navigation system",
          taskFlowController.getTodoContainer(),
          taskFlowController.getInprogressContainer(),
          taskFlowController.getDoneContainer(),
          0);
      new TaskController(
          "Implement the Navigation system",
          taskFlowController.getTodoContainer(),
          taskFlowController.getInprogressContainer(),
          taskFlowController.getDoneContainer(),
          0);
      new TaskController(
          "Implement task creation",
          taskFlowController.getTodoContainer(),
          taskFlowController.getInprogressContainer(),
          taskFlowController.getDoneContainer(),
          0);

      // Configure primary stage.
      stage.setScene(menuScene);
      stage.show();
      stage.setTitle(SoftwareInfo.softwareName);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}

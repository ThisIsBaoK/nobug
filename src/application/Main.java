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
  public void start(Stage primaryStage) {
    try {
      // Load all FXMLs.
      FXMLLoader navigationLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.NAVIGATION_FXML));
      FXMLLoader taskFlowLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FLOW_FXML));
      FXMLLoader taskFormLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FORM_FXML));

      // Controllers.
      Scene menuScene = new Scene(navigationLoader.load());
      taskFlowLoader.load();
      taskFormLoader.load();
      NavigationController navigationController = navigationLoader.getController();
      TaskFormController taskFormController = taskFormLoader.getController();
      TaskFlowController taskFlowController = taskFlowLoader.getController();

      // Create pages.
      VBox tabContainer = navigationController.getTabContainer();

      // Task Flow page.
      tabContainer.getChildren().add(taskFlowController.getContainer());
      VBox.setVgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      HBox.setHgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      System.out.print(taskFormController.getContainer());
      taskFlowController.setTaskFormController(taskFormController);

      // Required initialization.
      taskFlowController.init();
      taskFormController.init();

      // Configure primary stage.
      navigationController.setStage(primaryStage);
      primaryStage.setScene(menuScene);
      primaryStage.show();
      primaryStage.setTitle(SoftwareInfo.SOFTWARE_NAME);
    } catch (Exception e) {
      System.out.println("Here" + e);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}

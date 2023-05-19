package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
  private FXMLLoader navigationLoader;
  private FXMLLoader taskFlowLoader;
  private FXMLLoader taskFormLoader;
  private FXMLLoader loginLoader;
  private Scene loginScene;
  private Scene navigationScene;
  private Stage primaryStage;

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;

    try {
      // Load all FXMLs.
      navigationLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.NAVIGATION_FXML));
      taskFlowLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FLOW_FXML));
      taskFormLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FORM_FXML));
      loginLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.LOGIN_FXML));

      // Controllers.
      Parent loginLoaded = loginLoader.load();
      Parent nagivationLoaded = navigationLoader.load();
      taskFlowLoader.load();
      taskFormLoader.load();
      loginScene = new Scene(loginLoaded);
      navigationScene = new Scene(nagivationLoaded);
      NavigationController navigationController = navigationLoader.getController();
      TaskFormController taskFormController = taskFormLoader.getController();
      TaskFlowController taskFlowController = taskFlowLoader.getController();
      LoginController loginController = loginLoader.getController();

      // Create pages.
      VBox tabContainer = navigationController.getTabContainer();

      // Task Flow page.
      tabContainer.getChildren().add(taskFlowController.getContainer());
      VBox.setVgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      HBox.setHgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      System.out.print(taskFormController.getContainer());
      taskFlowController.setTaskFormController(taskFormController);
      taskFormController.setParentController(taskFlowController);

      // Configure primary stage.
      navigationController.setStage(primaryStage);
      primaryStage.setTitle(SoftwareInfo.SOFTWARE_NAME);
      primaryStage.setScene(loginScene);

      loginController
          .getLogin()
          .setOnAction(
              new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                  login();
                }
              });

      // Show primary stage.
      primaryStage.show();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void login() {
    primaryStage.setScene(navigationScene);
  }
}

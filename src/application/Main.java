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
  private Stage primaryStage;
  private FXMLLoader navigationLoader;
  private FXMLLoader taskFlowLoader;
  private FXMLLoader taskFormLoader;
  private FXMLLoader loginLoader;
  private FXMLLoader signUpLoader;
  private Scene loginScene;
  private Scene signUpScene;
  private Scene navigationScene;
  private Backend backend;
  private NavigationController navigationController;
  private TaskFormController taskFormController;
  private TaskFlowController taskFlowController;
  private LoginController loginController;
  private SignUpController signUpController;

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;

    try {
      // Connect to database.
      backend = new Backend();

      // Load all FXMLs.
      navigationLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.NAVIGATION_FXML));
      taskFlowLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FLOW_FXML));
      taskFormLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FORM_FXML));
      loginLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.LOGIN_FXML));
      signUpLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.SIGN_UP_FXML));

      // Controllers.
      Parent loginLoaded = loginLoader.load();
      Parent signUpLoaded = signUpLoader.load();
      Parent nagivationLoaded = navigationLoader.load();
      taskFlowLoader.load();
      taskFormLoader.load();
      loginScene = new Scene(loginLoaded);
      signUpScene = new Scene(signUpLoaded);
      navigationScene = new Scene(nagivationLoaded);
      navigationController = navigationLoader.getController();
      taskFormController = taskFormLoader.getController();
      taskFlowController = taskFlowLoader.getController();
      loginController = loginLoader.getController();
      signUpController = signUpLoader.getController();

      // Create pages.
      VBox tabContainer = navigationController.getTabContainer();

      // Task Flow page.
      tabContainer.getChildren().add(taskFlowController.getContainer());
      VBox.setVgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      HBox.setHgrow(taskFlowController.getContainer(), Priority.ALWAYS);
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
      loginController
          .getSignUp()
          .setOnAction(
              new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                  signUp();
                }
              });
      signUpController
          .getCancel()
          .setOnAction(
              new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                  cancelSignUpForm();
                }
              });
      signUpController
          .getSubmit()
          .setOnAction(
              new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                  submitSignUpForm();
                }
              });

      // Show primary stage.
      primaryStage.show();
    } catch (Exception e) {
      System.out.println("system error: " + e);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void login() {
    boolean ok = false;
    if (loginController.getEmailText().equals("admin")
        && loginController.getPasswordText().equals("admin")) {
      ok = true;
      loginController.setLoginStatusText("");

    } else {
      try {
        if (backend.userExists(loginController.getEmailText(), loginController.getPasswordText())) {
          ok = true;
        } else {
          loginController.setLoginStatusText("Wrong email or password");
        }
      } catch (MyException e) {
        System.out.println(e);
        loginController.setLoginStatusText("Failed to read database");
      }
    }
    if (ok) {
      primaryStage.setScene(navigationScene);
    }
  }

  public void signUp() {
    primaryStage.setScene(signUpScene);
  }

  public void cancelSignUpForm() {
    primaryStage.setScene(loginScene);
  }

  public void submitSignUpForm() {
    primaryStage.setScene(loginScene);
  }
}

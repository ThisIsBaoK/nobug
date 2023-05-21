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
  private Backend backend;
  private Stage primaryStage;
  private Scene loginScene;
  private Scene signUpScene;
  private Scene navigationScene;
  private NavigationController navigationController;
  private TaskFormController taskFormController;
  private TaskFlowController taskFlowController;
  private TaskEditorController taskEditorController;
  private LoginController loginController;
  private SignUpController signUpController;
  private ProjectController projectController;
  private ProjectFormController projectFormController;
  private UserController userController;

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;

    try {
      // Connect to database.
      backend = new Backend();

      // Load all FXMLs.
      FXMLLoader navigationLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.NAVIGATION_FXML));
      FXMLLoader taskFlowLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FLOW_FXML));
      FXMLLoader taskFormLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_FORM_FXML));
      FXMLLoader taskEditorLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.TASK_EDITOR_FXML));
      FXMLLoader loginLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.LOGIN_FXML));
      FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.SIGN_UP_FXML));
      FXMLLoader projectLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.PROJECT_FXML));
      FXMLLoader projectFormLoader =
          new FXMLLoader(getClass().getResource(SoftwareInfo.PROJECT_FORM_FXML));
      FXMLLoader userLoader = new FXMLLoader(getClass().getResource(SoftwareInfo.USER_FXML));

      // Controllers.
      Parent loginLoaded = loginLoader.load();
      Parent signUpLoaded = signUpLoader.load();
      Parent nagivationLoaded = navigationLoader.load();
      taskFlowLoader.load();
      taskFormLoader.load();
      taskEditorLoader.load();
      projectLoader.load();
      projectFormLoader.load();
      userLoader.load();
      loginScene = new Scene(loginLoaded);
      signUpScene = new Scene(signUpLoaded);
      navigationScene = new Scene(nagivationLoaded);
      navigationController = navigationLoader.getController();
      taskFormController = taskFormLoader.getController();
      taskFlowController = taskFlowLoader.getController();
      taskEditorController = taskEditorLoader.getController();
      loginController = loginLoader.getController();
      signUpController = signUpLoader.getController();
      projectController = projectLoader.getController();
      projectFormController = projectFormLoader.getController();
      userController = userLoader.getController();

      // Create pages.
      VBox tabContainer = navigationController.getTabContainer();

      // Navigation.
      navigationController.setTaskFlow(taskFlowController.getContainer());
      navigationController.setProject(projectController.getContainer());
      navigationController.setPeople(userController.getContainer());

      // Task Flow page.
      tabContainer.getChildren().add(taskFlowController.getContainer());
      VBox.setVgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      HBox.setHgrow(taskFlowController.getContainer(), Priority.ALWAYS);
      taskFlowController.setTaskFormController(taskFormController);
      taskFlowController.setTaskEditorController(taskEditorController);
      taskFlowController.setBackend(backend);
      taskFlowController.loadTasksFromDatabase();

      // Project page.
      projectController.setBackend(backend);
      projectController.updateTableFromDatabase();
      projectController.setProjectFormController(projectFormController);

      // People page.
      userController.setBackend(backend);
      userController.updateTableFromDatabase();

      // Configure primary stage.
      navigationController.setStage(primaryStage);
      primaryStage.setTitle(SoftwareInfo.SOFTWARE_NAME);
      primaryStage.setScene(loginScene);

      navigationController
          .getHelpLogout()
          .setOnAction(
              new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                  helpLogout();
                }
              });
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
        if (backend.emailAndPasswordExists(
            loginController.getEmailText(), loginController.getPasswordText())) {
          ok = true;
        } else {
          loginController.setLoginStatusText("Wrong email or password");
        }
      } catch (MyException e) {
        System.out.println(e);
        loginController.setLoginStatusText("Failed to query database");
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
    signUpController.clear();
    primaryStage.setScene(loginScene);
  }

  public void submitSignUpForm() {
    // Validate user name.
    String email = signUpController.getEmailText();
    String password = signUpController.getPasswordText();
    String firstName = signUpController.getFirstNameText();
    String lastName = signUpController.getLastNameText();
    if (email.length() == 0) {
      signUpController.setErrorMessage("Email must be at least one character");
      return;
    }
    if (email.length() >= 45) {
      signUpController.setErrorMessage("Email must be less than 45 characters");
      return;
    }
    // Validate password.
    if (password.length() == 0) {
      signUpController.setErrorMessage("Password must be at least one character");
      return;
    }
    if (password.length() >= 45) {
      signUpController.setErrorMessage("Password must be less than 45 characters");
      return;
    }
    // Validate first name.
    if (firstName.length() == 0) {
      signUpController.setErrorMessage("First Name must be at least one character");
      return;
    }
    if (firstName.length() >= 45) {
      signUpController.setErrorMessage("First Name must be less than 45 characters");
      return;
    }
    // Validate last name.
    if (lastName.length() == 0) {
      signUpController.setErrorMessage("Last Name must be at least one character");
      return;
    }
    if (lastName.length() >= 45) {
      signUpController.setErrorMessage("Last Name must be less than 45 characters");
      return;
    }
    // Check if user exists.
    try {
      if (backend.userExists(email)) {
        signUpController.setErrorMessage("Email is already used");
        return;
      }
    } catch (MyException e) {
      signUpController.setErrorMessage("Failed to query database");
      return;
    }
    // Add new user to the database.
    try {
      backend.addUser(email, password, firstName, lastName);
    } catch (MyException e) {
      signUpController.setErrorMessage("Failed to update database");
      System.out.println(e);
      return;
    }
    signUpController.clear();
    loginController.setEmailText(email);
    loginController.setPasswordText(password);
    primaryStage.setScene(loginScene);
    userController.updateTableFromDatabase();
  }

  public void helpLogout() {
    loginController.clear();
    primaryStage.setScene(loginScene);
  }
}

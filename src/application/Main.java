package application;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  // Stage is top-level JavaFX container.
  @Override
  public void start(Stage primaryStage) {
    // Set window title.
    primaryStage.setTitle(UIConfig.windowTitle);
    LoginPage loginPage = new LoginPage();

    IssueTrackerPage issueTrackerPage = new IssueTrackerPage();

    primaryStage.setScene(loginPage.scene);
    primaryStage.show();

    loginPage.signInButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (loginPage.userField.getText().equals("admin")
            && loginPage.passwordField.getText().equals("admin")) {
          loginPage.loginStatus.setFill(Color.GREEN);
          loginPage.loginStatus.setText("Hello " + loginPage.userField.getText());
          primaryStage.setScene(issueTrackerPage.scene);
        } else if (loginPage.userField.getText().isEmpty()
            && loginPage.passwordField.getText().isEmpty()) {
          loginPage.loginStatus.setFill(Color.FIREBRICK);
          loginPage.loginStatus.setText("Username and Password are required");
        } else {
          loginPage.loginStatus.setFill(Color.FIREBRICK);
          loginPage.loginStatus.setText("Wrong username or password");
        }
      }
    });
  }

}

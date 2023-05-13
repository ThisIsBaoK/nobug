package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LoginPage {
  public Scene scene;
  public Button signInButton;
  public Button signUpButton;
  public PasswordField passwordField;
  public TextField userField;
  public Text loginStatus;

  public LoginPage() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(UIConfig.pad2);
    grid.setVgap(UIConfig.pad2);
    grid.setPadding(new Insets(UIConfig.pad2));

    Text scenetitle = new Text("Login");
    scenetitle.setFont(UIConfig.heading1Font);
    grid.add(scenetitle, 0, 0, 2, 1);

    grid.add(new Label("Username:"), 0, 1);
    this.userField = new TextField("admin");
    grid.add(this.userField, 1, 1);

    grid.add(new Label("Password:"), 0, 2);
    this.passwordField = new PasswordField();
    grid.add(this.passwordField, 1, 2);

    HBox buttonHBox = new HBox(10);

    this.signInButton = new Button("Sign in");
    buttonHBox.getChildren().add(this.signInButton);
    buttonHBox.setAlignment(Pos.BOTTOM_RIGHT);
    grid.add(buttonHBox, 1, 4);

    this.loginStatus = new Text();
    grid.add(this.loginStatus, 0, 6);
    GridPane.setColumnSpan(this.loginStatus, 2);
    GridPane.setHalignment(this.loginStatus, HPos.CENTER);
    this.scene = new Scene(grid, UIConfig.windowWidth, UIConfig.windowHeight);
  }
}

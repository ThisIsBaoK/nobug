package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private Button login;
  @FXML private Label loginStatus;
  @FXML private Hyperlink signUp;

  public String getEmailText() {
    return email.getText();
  }

  public String getPasswordText() {
    return password.getText();
  }

  public void setLoginStatusText(String message) {
    loginStatus.setText(message);
  }

  public Button getLogin() {
    return login;
  }

  public Hyperlink getSignUp() {
    return signUp;
  }
}

package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private Button login;

  public TextField getEmail() {
    return email;
  }

  public String getEmailText() {
    return email.getText();
  }

  public PasswordField getPassword() {
    return password;
  }

  public String getPasswordText() {
    return password.getText();
  }

  public Button getLogin() {
    return login;
  }
}

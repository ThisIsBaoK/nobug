package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController {
  @FXML private TextField email;
  @FXML private TextField password;
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private Label errorMessage;
  @FXML private Button cancel;
  @FXML private Button submit;

  public String getEmailText() {
    return email.getText();
  }

  public String getPasswordText() {
    return password.getText();
  }

  public String getFirstNameText() {
    return firstName.getText();
  }

  public String getLastNameText() {
    return lastName.getText();
  }

  public void setErrorMessage(String message) {
    errorMessage.setText(message);
  }

  public Button getCancel() {
    return cancel;
  }

  public Button getSubmit() {
    return submit;
  }

  public void clear() {
    email.clear();
    password.clear();
    firstName.clear();
    lastName.clear();
  }
}

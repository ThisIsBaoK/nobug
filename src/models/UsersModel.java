package models;

import javafx.beans.property.SimpleStringProperty;

public class UsersModel {
  private SimpleStringProperty email;
  private SimpleStringProperty firstName;
  private SimpleStringProperty lastName;

  public UsersModel(String email, String lastName, String firstName) {
    this.email = new SimpleStringProperty(email);
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
  }

  public String getEmail() {
    return email.get();
  }

  public void setEmail(String email) {
    this.email.set(email);
  }

  public String getFirstName() {
    return firstName.get();
  }

  public void setFirstName(String firstName) {
    this.firstName.set(firstName);
  }

  public String getLastName() {
    return lastName.get();
  }

  public void setLastName(String lastName) {
    this.lastName.set(lastName);
  }
}

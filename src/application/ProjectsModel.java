package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProjectsModel {
  private SimpleIntegerProperty projectID;
  private SimpleStringProperty title;
  private SimpleStringProperty description;

  public ProjectsModel(int projectID, String title, String description) {
    this.projectID = new SimpleIntegerProperty(projectID);
    this.title = new SimpleStringProperty(title);
    this.description = new SimpleStringProperty(description);
  }

  public int getProjectID() {
    return projectID.get();
  }

  public void setProjectID(int projectID) {
    this.projectID.set(projectID);
  }

  public String getTitle() {
    return title.get();
  }

  public void setTitle(String title) {
    this.title.set(title);
  }

  public String getDescription() {
    return description.get();
  }

  public void setDescription(String description) {
    this.description.set(description);
  }
}

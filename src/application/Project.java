package application;

public class Project {
  private String title;
  private String description;
  private ProjectStatus status;

  public Project(String title, String description, ProjectStatus status) {
    this.title = title;
    this.description = description;
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProjectStatus getStatus() {
    return status;
  }

  public void setStatus(ProjectStatus status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

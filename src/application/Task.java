package application;

public class Task {
  private User author;
  private User assigned;
  private String title;
  private String description;
  private Project project;
  private TaskStatus status;

  public Task(
      User author,
      User assigned,
      String title,
      String description,
      Project project,
      TaskStatus status) {
    this.author = author;
    this.assigned = assigned;
    this.title = title;
    this.description = description;
    this.project = project;
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public User getAssigned() {
    return assigned;
  }

  public void setAssigned(User assigned) {
    this.assigned = assigned;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }
}

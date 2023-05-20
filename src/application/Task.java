package application;

public class Task {
  private int id;
  private String author;
  private String assigned;
  private String title;
  private String description;
  private int project;
  private TaskStatus status;

  public Task(
      String author,
      String assigned,
      String title,
      String description,
      int project,
      TaskStatus status) {
    this.author = author;
    this.assigned = assigned;
    this.title = title;
    this.description = description;
    this.project = project;
    this.status = status;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getProject() {
    return project;
  }

  public void setProject(int project) {
    this.project = project;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getAssigned() {
    return assigned;
  }

  public void setAssigned(String assigned) {
    this.assigned = assigned;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }
}

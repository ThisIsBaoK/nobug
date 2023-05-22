package models;

public enum TaskStatus {
  TODO("TODO"),
  INPROGRESS("INPROGRESS"),
  DONE("DONE");

  private String label;

  TaskStatus(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}

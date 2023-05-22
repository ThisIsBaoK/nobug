package controllers;

public enum ProjectStatus {
  ARCHIVED("ARCHIVED"),
  ACTIVE("ACTIVE"),
  MAINTENANCE("MAINTENANCE");

  private String label;

  ProjectStatus(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}

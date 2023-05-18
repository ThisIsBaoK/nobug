package application;

public class SoftwareInfo {
  public static final String SOFTWARE_BASE_NAME = "NoBug";
  public static final String RELEASE_DATE = "May 23, 2023";
  public static final int VERSION_MAJOR = 0;
  public static final int VERSION_MINOR = 1;
  public static final int VERSION_FIX = 0;
  public static final String VERSION = VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_FIX;
  public static final String SOFTWARE_NAME = "NoBug " + VERSION;
  public static final String ABOUT =
      SOFTWARE_BASE_NAME
          + " - a fast and minimal bug tracking system in Java.\n\n"
          + "Software Version: "
          + VERSION
          + "\n\n"
          + "Release Date: "
          + RELEASE_DATE;
  public static final String TASK_FLOW_FXML = "/application/TaskFlow.fxml";
  public static final String NAVIGATION_FXML = "/application/Navigation.fxml";
  public static final String TASK_FORM_FXML = "/application/TaskForm.fxml";
}

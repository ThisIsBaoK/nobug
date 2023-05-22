package common;

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
  public static final String TASK_FLOW_FXML = "/views/TaskFlow.fxml";
  public static final String NAVIGATION_FXML = "/views/Navigation.fxml";
  public static final String TASK_FORM_FXML = "/views/TaskForm.fxml";
  public static final String TASK_EDITOR_FXML = "/views/TaskEditor.fxml";
  public static final String LOGIN_FXML = "/views/Login.fxml";
  public static final String SIGN_UP_FXML = "/views/SignUp.fxml";
  public static final String PROJECT_FXML = "/views/Project.fxml";
  public static final String PROJECT_FORM_FXML = "/views/ProjectForm.fxml";
  public static final String USER_FXML = "/views/User.fxml";
  public static final String DATABASE_USERNAME = "root";
  public static final String DATABASE_PASSWORD = "cs380";
  public static final String DATABASE_NAME = "nobug";
  public static final String ADMIN_EMAIL = "admin";
  public static final String ADMIN_PASSWORD = "admin";
}

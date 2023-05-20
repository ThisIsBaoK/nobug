package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Backend {
  private static final String SQL_INSERT_USER =
      "INSERT INTO Users(email, password, firstName, lastName) VALUES(?, ?, ?, ?)";
  private static final String SQL_INSERT_TASK =
      "INSERT INTO Tasks(author, assigned, title, description, project, status) VALUES(?, ?, ?, ?,"
          + " ?, ?)";
  private static final String SQL_QUERY_LAST_INSERTED_TASK_INDEX =
      "SELECT LAST_INSERT_ID() AS lastID FROM Tasks";
  private static final String SQL_UPDATE_TASK_STATUS = "UPDATE Tasks SET status=? WHERE id=?";
  private static final String SQL_QUERY_USER_EXISTENCE = "SELECT 1 FROM Users WHERE email=?";
  private static final String SQL_QUERY_EMAIL_PASSWORd_EXISTENCE =
      "SELECT 1 FROM Users WHERE email=? AND password=?";
  private static final String SQL_QUERY_PROJECT_EXISTENCE = "SELECT 1 FROM Projects WHERE id=?";
  private static final String SQL_READ_ALL_PROJECTS = "SELECT * FROM Projects";
  private static final String SQL_READ_ALL_USERS = "SELECT * FROM Users";
  private static final String SQL_INSERT_PROJECT =
      "INSERT INTO Projects(title, description, status) VALUES(?, ?, ?)";
  private static final String SQL_READ_ALL_TASKS = "SELECT * FROM Tasks";
  private Connection connection;

  public Backend() throws MyException {
    try {
      connection =
          DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/" + SoftwareInfo.DATABASE_NAME,
              SoftwareInfo.DATABASE_USERNAME,
              SoftwareInfo.DATABASE_PASSWORD);
    } catch (SQLException e) {
      throw new MyException("connect to database: " + e);
    }
  }

  public boolean userExists(String email) throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_QUERY_USER_EXISTENCE);
      preparedStatement.setString(1, email);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.isBeforeFirst()) {
        return true;
      }
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
    return false;
  }

  public boolean emailAndPasswordExists(String email, String password) throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_QUERY_EMAIL_PASSWORd_EXISTENCE);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.isBeforeFirst()) {
        return true;
      }
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
    return false;
  }

  public int addTask(
      String author, String assigned, String title, String description, int project, String status)
      throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_INSERT_TASK);
      preparedStatement.setString(1, author);
      if (assigned.length() == 0) {
        assigned = null;
      }
      preparedStatement.setString(2, assigned);
      preparedStatement.setString(3, title);
      preparedStatement.setString(4, description);
      preparedStatement.setInt(5, project);
      preparedStatement.setString(6, status);
      preparedStatement.executeUpdate();

      // Get task id.
      preparedStatement = connection.prepareStatement(SQL_QUERY_LAST_INSERTED_TASK_INDEX);
      ResultSet rs = preparedStatement.executeQuery();
      rs.next();
      String lastID = rs.getString("lastID");
      try {
        return Integer.parseInt(lastID);
      } catch (NumberFormatException e) {
        throw new MyException("cannot read query result");
      }
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
  }

  public void updateTaskStatus(int id, String status) throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TASK_STATUS);
      preparedStatement.setString(1, status);
      preparedStatement.setInt(2, id);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
  }

  public int addUser(String email, String password, String firstName, String lastName)
      throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      preparedStatement.setString(3, firstName);
      preparedStatement.setString(4, lastName);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
  }

  public boolean projectExists(int projectID) throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_QUERY_PROJECT_EXISTENCE);
      preparedStatement.setInt(1, projectID);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.isBeforeFirst()) {
        return true;
      }
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
    return false;
  }

  public ResultSet readAllProjects() throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_READ_ALL_PROJECTS);
      return preparedStatement.executeQuery();
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
  }

  public ResultSet readAllUsers() throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_READ_ALL_USERS);
      return preparedStatement.executeQuery();
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
  }

  public ResultSet readAllTasks() throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_READ_ALL_TASKS);
      return preparedStatement.executeQuery();
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
  }

  public int addProject(String title, String description, String status) throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_INSERT_PROJECT);
      preparedStatement.setString(1, title);
      preparedStatement.setString(2, description);
      preparedStatement.setString(3, status);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new MyException("execute query: " + e);
    }
  }
}

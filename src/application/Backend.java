package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Backend {
  private static final String SQL_INSERT_USER =
      "INSERT INTO Users(email, password, firstName, lastName) VALUES(?, ?, ?, ?)";
  private static final String SQL_QUERY_USER_EXISTENCE = "SELECT 1 FROM Users WHERE email=?";
  private static final String SQL_QUERY_EMAIL_PASSWORd_EXISTENCE =
      "SELECT 1 FROM Users WHERE email=? AND password=?";
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
}

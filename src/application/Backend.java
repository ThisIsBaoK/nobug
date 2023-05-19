package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Backend {
  private static final String SQL_INSERT_USER =
      "INSERT INTO Users(email, password, firstName, lastName) VALUES(?, ?, ?, ?)";
  private static final String SQL_QUERY_USER_EXISTENCE =
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

  public boolean userExists(String email, String password) throws MyException {
    PreparedStatement preparedStatement;
    try {
      preparedStatement = connection.prepareStatement(SQL_QUERY_USER_EXISTENCE);

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
}

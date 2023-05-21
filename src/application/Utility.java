package application;

public class Utility {
  public static String toNonNullString(String str) {
    if (str == null) {
      return "";
    }
    return str;
  }
}

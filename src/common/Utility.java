package common;

public class Utility {
  /**
   * Returns the empty string if the String object is null. Otherwise, the method returns the passed
   * String object.
   */
  public static String toNonNullString(String str) {
    if (str == null) {
      return "";
    }
    return str;
  }
}

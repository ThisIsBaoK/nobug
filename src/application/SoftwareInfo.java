package application;

public class SoftwareInfo {
  public static final String softwareBaseName = "NoBug";
  public static final String releaseDate = "May 23, 2023";
  public static final int versionMajor = 0;
  public static final int versionMinor = 1;
  public static final int versionFix = 0;
  public static final String version = "" + versionMajor + "." + versionMinor + "." + versionFix;
  public static final String softwareName = "NoBug " + version;
  public static final String about =
      softwareBaseName
          + " - a fast and minimal bug tracking system in Java.\n\n"
          + "Software Version: "
          + version
          + "\n\n"
          + "Release Date: "
          + releaseDate;
}

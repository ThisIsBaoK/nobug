package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavigationController {
  @FXML private VBox tabContainer;
  @FXML private MenuItem helpLogout;

  private Stage stage;

  public VBox getTabContainer() {
    return this.tabContainer;
  }

  public MenuItem getHelpLogout() {
    return helpLogout;
  }

  public void openAboutWindow() {
    Stage newWindow = new Stage();
    newWindow.setTitle("About");
    Label content = new Label(SoftwareInfo.ABOUT);
    content.setWrapText(true);
    VBox container = new VBox(content);
    container.setPadding(new Insets(5));
    container.setAlignment(Pos.CENTER);
    newWindow.setScene(new Scene(container));
    newWindow.show();
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void resetWindowSize() {
    this.stage.setWidth(800);
    this.stage.setHeight(600);
  }
}

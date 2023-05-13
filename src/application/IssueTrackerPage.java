package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IssueTrackerPage extends PageBase {
  public IssueTrackerPage() {
    super("Issue Tracker");
    IssueSubmission issueItem = new IssueSubmission();

    VBox subContainer = new VBox(issueItem.pane);
    subContainer.setPadding(new Insets(UIConfig.pad3));
    VBox container = new VBox(createTopMenu(), subContainer);
    container.setSpacing(UIConfig.pad2);
    this.scene = new Scene(container, UIConfig.windowWidth, UIConfig.windowHeight);
  }

  public static MenuBar createTopMenu() {
    Menu fileMenu = new Menu("Info");
    MenuItem aboutItem = new MenuItem("About");
    fileMenu.getItems().addAll(aboutItem);
    MenuBar menuBar = new MenuBar(fileMenu);

    EventHandler<ActionEvent> event =
        new EventHandler<ActionEvent>() {
          public void handle(ActionEvent e) {
            System.out.println(SoftwareInfo.about);
            Stage newWindow = new Stage();
            newWindow.setTitle("About");
            Label content = new Label(SoftwareInfo.about);
            content.setWrapText(true);
            VBox container = new VBox(content);
            container.setPadding(new Insets(UIConfig.pad3));
            container.setAlignment(Pos.CENTER);
            newWindow.setScene(new Scene(container));
            newWindow.show();
          }
        };

    // add event
    aboutItem.setOnAction(event);

    return menuBar;
  }
}

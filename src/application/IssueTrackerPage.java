package application;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class IssueTrackerPage extends PageBase {
  public IssueTrackerPage() {
    super("Issue Tracker");
    IssueSubmission issueItem = new IssueSubmission();

    Text scenetitle = new Text("Submit New Issue");
    scenetitle.setFont(UIConfig.heading1Font);
    VBox mainBox = new VBox(createTopMenu(), issueItem.pane);
    mainBox.setSpacing(UIConfig.pad2);

    this.scene = new Scene(mainBox, UIConfig.windowWidth, UIConfig.windowHeight);

  }

  public static MenuBar createTopMenu() {
    Menu fileMenu = new Menu("Info");
    MenuItem aboutItem = new MenuItem("About");
    fileMenu.getItems().addAll(aboutItem);
    MenuBar menuBar = new MenuBar(fileMenu);

    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        System.out.println(SoftwareInfo.about);
      }
    };

    // add event
    aboutItem.setOnAction(event);

    return menuBar;
  }
}

package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ReportPage {
  public Scene scene;

  public ReportPage() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(UIConfig.pad2);
    grid.setVgap(UIConfig.pad2);
    grid.setPadding(new Insets(UIConfig.pad2, UIConfig.pad2, UIConfig.pad2, UIConfig.pad2));

    Text scenetitle = new Text("Issue Report");
    scenetitle.setFont(UIConfig.heading1Font);
    grid.add(scenetitle, 0, 0, 2, 1);

    this.scene = new Scene(grid, UIConfig.windowWidth, UIConfig.windowHeight);
  }
}

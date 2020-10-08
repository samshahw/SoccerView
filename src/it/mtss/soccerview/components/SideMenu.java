package it.mtss.soccerview.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;
import java.util.List;

public class SideMenu {

  List<Button> buttons;
  List<SVGPath> icons;
  VBox container;


  public SideMenu() {

    String iconsPath[] = {
        "M21 3a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1h-2.001L19 20a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1l-.001-8.001L3 12a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h6a3 3 0 0 0 6 0h6z",
        "M12 11a5 5 0 0 1 5 5v6H7v-6a5 5 0 0 1 5-5zm-6.712 3.006a6.983 6.983 0 0 0-.28 1.65L5 16v6H2v-4.5a3.5 3.5 0 0 1 3.119-3.48l.17-.014zm13.424 0A3.501 3.501 0 0 1 22 17.5V22h-3v-6c0-.693-.1-1.362-.288-1.994zM5.5 8a2.5 2.5 0 1 1 0 5a2.5 2.5 0 0 1 0-5zm13 0a2.5 2.5 0 1 1 0 5a2.5 2.5 0 0 1 0-5zM12 2a4 4 0 1 1 0 8a4 4 0 0 1 0-8z",
        "M17 10.43V2H7v8.43c0 .35.18.68.49.86l4.18 2.51l-.99 2.34l-3.41.29l2.59 2.24L9.07 22L12 20.23L14.93 22l-.78-3.33l2.59-2.24l-3.41-.29l-.99-2.34l4.18-2.51c.3-.18.48-.5.48-.86zm-4 1.8l-1 .6l-1-.6V3h2v9.23z"
    };

    String iconsText[] = {"Players", "Teams", "Competitions"};

    buttons = new ArrayList<>();
    icons = new ArrayList<>();

    container = new VBox();

    container.setPadding(new Insets(20));

    container.setSpacing(10);

    SVGPath playersIcon = new SVGPath();
    playersIcon.setId("players-icon");
    SVGPath teamsIcon = new SVGPath();
    teamsIcon.setId("teams-icon");
    SVGPath competitionsIcon = new SVGPath();
    competitionsIcon.setId("competitions-icon");

    icons.add(playersIcon);
    icons.add(teamsIcon);
    icons.add(competitionsIcon);

    for (int i = 0; i < icons.size(); i++) {
      SVGPath icon = icons.get(i);
      icon.setContent(iconsPath[i]);
      icon.setFill(Color.BLACK);
      Button btn = new Button(iconsText[i], icon);
      buttons.add(btn);
      btn.setPrefWidth(200);
      container.getChildren().add(btn);
    }

  }


  public Pane getPane() {

    return container;
  }

}

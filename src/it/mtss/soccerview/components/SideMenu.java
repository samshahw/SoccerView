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

    // Buttons icon
    String[] iconsPath = {
        "M21 3a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1h-2.001L19 20a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1l-.001-8." +
            "001L3 12a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h6a3 3 0 0 0 6 0h6z",
        "M12 11a5 5 0 0 1 5 5v6H7v-6a5 5 0 0 1 5-5zm-6.712 3.006a6.983 6.983 0 0 0-.28 1.65L5 16" +
            "v6H2v-4.5a3.5 3.5 0 0 1 3.119-3.48l.17-.014zm13.424 0A3.501 3.501 0 0 1 22 17.5V22h" +
            "-3v-6c0-.693-.1-1.362-.288-1.994zM5.5 8a2.5 2.5 0 1 1 0 5a2.5 2.5 0 0 1 0-5zm13 0a2" +
            ".5 2.5 0 1 1 0 5a2.5 2.5 0 0 1 0-5zM12 2a4 4 0 1 1 0 8a4 4 0 0 1 0-8z",
        "M552 64H448V24c0-13.3-10.7-24-24-24H152c-13.3 0-24 10.7-24 24v40H24C10.7 64 0 74.7 0 88" +
            "v56c0 35.7 22.5 72.4 61.9 100.7c31.5 22.7 69.8 37.1 110 41.7C203.3 338.5 240 360 24" +
            "0 360v72h-48c-35.3 0-64 20.7-64 56v12c0 6.6 5.4 12 12 12h296c6.6 0 12-5.4 12-12v-12" +
            "c0-35.3-28.7-56-64-56h-48v-72s36.7-21.5 68.1-73.6c40.3-4.6 78.6-19 110-41.7c39.3-28" +
            ".3 61.9-65 61.9-100.7V88c0-13.3-10.7-24-24-24zM99.3 192.8C74.9 175.2 64 155.6 64 14" +
            "4v-16h64.2c1 32.6 5.8 61.2 12.8 86.2c-15.1-5.2-29.2-12.4-41.7-21.4zM512 144c0 16.1-" +
            "17.7 36.1-35.3 48.8c-12.5 9-26.7 16.2-41.8 21.4c7-25 11.8-53.6 12.8-86.2H512v16z"
    };

    // Buttons text
    String[] iconsText = {"Players", "Teams", "Competitions"};

    buttons = new ArrayList<>();
    icons = new ArrayList<>();

    // Create buttons container and define its style
    container = new VBox();
    container.setId("sidemenu");
    container.setPadding(new Insets(20));
    container.setSpacing(15);

    // Fill the icons list with their SVGs
    SVGPath playersIcon = new SVGPath();
    SVGPath teamsIcon = new SVGPath();
    SVGPath competitionsIcon = new SVGPath();
    icons.add(playersIcon);
    icons.add(teamsIcon);
    icons.add(competitionsIcon);

    // Create the buttons and add icons and texts
    for (int i = 0; i < icons.size(); i++) {

      SVGPath icon = icons.get(i);
      icon.setContent(iconsPath[i]);
      icon.setFill(Color.BLACK);

      Button btn = new Button(iconsText[i], icon);
      btn.setGraphicTextGap(15);
      btn.setPrefWidth(200);
      buttons.add(btn);

      container.getChildren().add(btn);
    }

  }


  public Pane getPane() {

    return container;
  }

}

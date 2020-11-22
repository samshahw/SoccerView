package it.mtss.soccerview.main;

import it.mtss.soccerview.components.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {

    Application.launch(args);
  }


  @Override
  public void start(Stage primaryStage) {

    // Define both the main view and stage
    BorderPane mainView = new BorderPane();
    Scene scene = new Scene(mainView, 1680, 960);
    primaryStage.setScene(scene);
    primaryStage.setTitle("SoccerView");

    // Top banner
    mainView.setTop(new TopBanner().getTitleBar());

    // Side menu
    mainView.setLeft(new SideMenu().getPane());

    // Player form
    mainView.setRight(new PlayerForm().getPlayerForm());

    // Players list
    mainView.setCenter(new PlayersList().getPane());

    // Footer
    mainView.setBottom(new Footer().getFooter());

    // Style the scene
    scene.getStylesheets().add("it/mtss/soccerview/css/players.css");

    // Show the scene
    primaryStage.show();
  }

}

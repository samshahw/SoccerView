package it.mtss.soccerview.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.*;

public class TopBanner {

  private final FlowPane topBanner;


  public TopBanner() {

    this.topBanner = new FlowPane();
    topBanner.setId("top-banner");

    // Define top banner style
    topBanner.setPadding(new Insets(40, 50, 30, 50));
    topBanner.setAlignment(Pos.TOP_LEFT);
    topBanner.setPrefHeight(100);
    topBanner.setStyle("-fx-background-color: #04ab6e");

    // Soccer ball banner icon
    SVGPath topBannerIcon = new SVGPath();
    topBannerIcon.setId("top-banner-icon");
    topBannerIcon.setContent("M61.9 32c0-.7.2-10.9-5.8-17.5c-.3-.6-1.5-3-5.6-5.9C47.8 6.5 45 5 4" +
                                 "4.7 4.8C44.4 4.6 39.4 2 33.4 2c-.5 0-.9 0-1.4.1c-4.6-.1-8.8 1." +
                                 "1-11.9 2.5c-3.2 1.4-5.3 2.8-5.5 3c-3.4 1.9-9.9 9.5-10.4 13.6c-" +
                                 "2.1 2.6-3.8 14.5 0 21.7c2.7 10 12.7 15 13.5 15.4c.5.3 5.9 3.7 " +
                                 "12.6 3.7h.9c.6.1 1.1.1 1.7.1c7.2 0 18-5.1 20.2-9.1c6.2-4.6 9.4" +
                                 "-16.2 8.8-21M17.8 47.1c-2.9-4.6-4.5-10.7-4.9-12.1c.9-1.4 5.4-8" +
                                 " 7.9-10c1.4.3 7.5 1.4 13.2 2.4c.7 1.9 3.9 10 4.8 13.2c-1 1.2-4" +
                                 ".9 5.7-8.7 9.2c-4.1.1-11-2.3-12.3-2.7m36-32.5c0 .4-.1 2-.9 3.9" +
                                 "c-1.5-.8-5.3-2.4-10.6-2.7c-.8-1.2-3.8-5.3-8.5-8.1c.6-1.3 1.5-2" +
                                 ".8 2.1-3.3c.2 0 .4-.1.8-.1c2.5 0 6.9 1.7 7.3 1.8c.4.2 8.3 4.4 " +
                                 "9.8 8.5M11.8 34c-3.4-.6-5.5-1.6-6.1-2c-1.3-4.6-.2-9.6-.1-10.3c" +
                                 "1.3-2.2 4.8-8 7.2-9.1c2.4-.5 5.5.1 6.7.4c-.1 1.6-.3 6.1.3 10.9" +
                                 "c-2.6 2.2-6.9 8.5-8 10.1M31.7 3.5c.8.1 1.9.2 2.7.5c-.8 1-1.6 2" +
                                 ".5-1.9 3.3c-1.6.3-7.5 1.4-12.2 4.4c-.9-.2-3.8-.9-6.5-.7c.7-1.3" +
                                 " 1.7-2.2 1.8-2.3c.3-.3 7.4-5.3 16.1-5.2m19.1 38.1c-1.2 0-5.7-." +
                                 "3-10.6-1.5c-.9-3.3-4.1-11.4-4.8-13.3c3.1-4.4 6.1-8.5 6.9-9.7c5" +
                                 ".7.4 9.7 2.5 10.5 2.9c3.3 5.3 4 10.7 4.1 11.6c-1.8 5.5-5.2 9.2" +
                                 "-6.1 10M3.7 28.5c.1 1.3.3 2.6.7 3.9c-.3.9-.6 1.8-.7 2.7c-.3-2." +
                                 "3-.3-4.6 0-6.6M18.5 57l-.4.6l.4-.6c-2.5-1.2-4.4-4-5.2-5.1c1.5-" +
                                 "1.5 3.4-2.9 4.1-3.4c1.6.6 8.3 2.8 12.6 2.8c.7 1 3.1 4 6 6.4c-1" +
                                 ".8 1.8-4.4 2.6-4.9 2.8c-6.8.2-12.6-3.5-12.6-3.5m16.3 3.4c.9-.5" +
                                 " 1.9-1.2 2.7-2.1c1.3-.2 6.9-1.1 11.9-4.8c.3 0 .9.1 1.5.1c-3.1 " +
                                 "2.9-10.5 6.2-16.1 6.8M50.2 52c1.8-4.7 1.7-8.3 1.6-9.4c1-1 4.4-" +
                                 "4.6 6.3-10.1c1 .2 1.7.4 2 .6c.1.4.3 1.3.2 2.7c-.8 5-3.4 12.6-8" +
                                 ".1 15.9c-.5.3-1.3.4-2 .3");
    topBannerIcon.setFill(Color.WHITE);
    topBannerIcon.setScaleX(1.6);
    topBannerIcon.setScaleY(1.6);

    // Create banner title and subtitle, and add them to a vbox
    VBox topBannerTitlesBox = new VBox();

    Text topBannerTitle = new Text("SoccerView");
    topBannerTitle.setId("top-banner-title");

    Text topBannerSubtitle = new Text("Visualize your players");
    topBannerTitle.setId("top-banner-subtitle");

    topBannerTitlesBox.getChildren().addAll(topBannerTitle, topBannerSubtitle);

    // Main title style
    topBannerTitle.setFont(Font.font("SanSerif", FontWeight.BOLD, 35));
    topBannerTitle.setFill(Color.WHITE);

    // Subtitle style
    topBannerSubtitle.setFont(Font.font("SanSerif", 25));
    topBannerSubtitle.setFill(Color.WHITE);

    // Add everything to the top banner region
    HBox topBannerHBox = new HBox(50);
    topBannerHBox.getChildren().addAll(topBannerIcon, topBannerTitlesBox);

    topBanner.getChildren().add(topBannerHBox);
  }


  public Pane getTitleBar() {

    return this.topBanner;
  }

}

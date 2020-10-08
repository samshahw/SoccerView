package it.mtss.soccerview.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class Footer {

  private final FlowPane footer;


  public Footer() {

    this.footer = new FlowPane();
    footer.setId("footer");

    // Define the footer style
    footer.setPadding(new Insets(10));
    footer.setAlignment(Pos.CENTER);
    footer.setPrefHeight(25);
    footer.setStyle("-fx-background-color: #04ab6e");

    // Create footer text
    Text footerText = new Text("SoccerView - TMSS 2020");
    footerText.setId("footer-text");

    // Footer text style
    footerText.setFont(Font.font("Serif", FontWeight.LIGHT, 15));
    footerText.setFill(Color.WHITE);

    // Add text to the footer
    footer.getChildren().add(footerText);
  }


  public Pane getFooter() {

    return this.footer;
  }

}

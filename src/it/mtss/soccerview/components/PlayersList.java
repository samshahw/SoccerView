package it.mtss.soccerview.components;

import it.mtss.soccerview.dao.PlayersDAO;
import it.mtss.soccerview.model.Player;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayersList {

  private static VBox container;
  private List<Player> players;


  public PlayersList() {

    TableView tableView = new TableView();
    PlayersDAO dao = new PlayersDAO();

    try {

      // Retrieve players data from DB
      players = dao.fetchPlayers(0);

    } catch (SQLException e) {

      System.out.println("Unable to retrieve data");
      e.printStackTrace();
    }

    // Set single selection mode on table
    TableView.TableViewSelectionModel<Player> selectionModel = tableView.getSelectionModel();
    selectionModel.setSelectionMode(SelectionMode.SINGLE);

    // Set table columns
    setColumns(tableView);

    // Add players fetched data to the list
    for (Player player : players) {

      tableView.getItems().add(player);
    }

    // Set constraints on columns resize
    tableView.setPrefHeight(860);
    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    // Assemble the list
    container = new VBox();
    container.getChildren().add(tableView);
  }


  private void bindColumn(String field, TableColumn<String, Player> col) {

    col.setCellValueFactory(new PropertyValueFactory<>(field));
  }


  // Get the last column from the list
  private TableColumn lastColumn(List<TableColumn<String, Player>> list) {

    return list.get(list.size() - 1);
  }


  // Add new columns to the list
  private void setColumns(TableView tableView) {

    // Add labels and fields to the list
    List<TableColumn<String, Player>> columns = new ArrayList<>();

    String labels[] = {
        "First Name",
        "Last Name",
        "Alias",
        "Squad Name",
        "Birth Date",
        "Height",
        "Weight"
    };

    String fields[] = {
        "firstName",
        "lastName",
        "alias",
        "squadNumber",
        "birthdate",
        "height",
        "weight"
    };

    for (int i = 0; i < labels.length; i += 1) {

      columns.add(new TableColumn<>(labels[i]));
      bindColumn(fields[i], lastColumn(columns));
    }

    for (TableColumn<String, Player> column : columns) {

      column.prefWidthProperty().bind(tableView.widthProperty().multiply(1.0 / labels.length));
      tableView.getColumns().add(column);
    }

  }


  public Pane getPane() {

    container.setPadding(new Insets(20, 0, 20, 0));
    return this.container;
  }

}

package it.mtss.soccerview.dao;

import it.mtss.soccerview.model.Player;
import it.mtss.soccerview.utils.Connector;

import java.sql.*;
import java.util.*;

/**
 * Multi-purpose DAO class for integration with a multitude of frameworks and libraries
 * (ExtJS, SmartClient, JavaFX, ...).
 * You may notice the connection is returned from a class "Connector", that class has
 * not been uploaded to the repository since it contains infos about the DB.
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection, unused")
public class PlayersDAO {

  // Add the new fetched player to the list of players
  private void setPlayer(ResultSet resultSet, List<Player> players) throws SQLException {

    Player player = new Player();

    player.setId(resultSet.getInt("ID_REC"));
    player.setFirstName(resultSet.getString("NOME"));
    player.setLastName(resultSet.getString("COGNOME"));
    player.setAlias(resultSet.getString("ALIAS"));

    // We don't want to display 0 if there is no number
    if (resultSet.getInt("NUMERO_MAGLIA_ABITUALE") != 0) {

      player.setSquadNumber(resultSet.getInt("NUMERO_MAGLIA_ABITUALE"));

    } else {

      player.setSquadNumber(null);
    }

    player.setBirthdate(resultSet.getDate("DATA_DI_NASCITA"));

    if (resultSet.getInt("ALTEZZA") != 0) {

      player.setHeight(resultSet.getInt("ALTEZZA"));

    } else {

      player.setHeight(null);
    }

    if (resultSet.getInt("PESO") != 0) {

      player.setWeight(resultSet.getInt("PESO"));

    } else {

      player.setWeight(null);
    }

    player.setOperationType("R");

    players.add(player);
  }


  // Fetch either all the players if the given id is 0, or
  // just the wanted player if the id is anything else
  public List<Player> fetchPlayers(int id) throws SQLException {

    Connection connection = Connector.getConnection();

    List<Player> players = new ArrayList<>();

    // Query with logic for fetching all or just one
    String query =
        "SELECT " +
            "id_rec, " +
            "nome, " +
            "cognome, " +
            "alias, " +
            "numero_maglia_abituale," +
            "data_di_nascita, " +
            "altezza, " +
            "peso " +
            "FROM giocatori" +
            (id > 0 ? " WHERE id_rec = ?" : "");

    PreparedStatement statement = connection.prepareStatement(query);

    // Set the id only if we are not requesting everyone
    if (id > 0) {

      statement.setInt(1, id);
    }

    ResultSet resultSet = statement.executeQuery();

    // Add the players to the list
    while (resultSet.next()) {

      setPlayer(resultSet, players);
    }

    connection.close();
    System.out.println("Fetched succesfully from the DB!");

    return players;
  }


  // Directly get all players fom the fetchPlayers() method
  public List<Player> getAllPlayers(int start, int limit, Map<String, String> sorter, List<Map<String, String>> filters) {

    try {

      return fetchPlayers(0); // (start, limit, sorter, filters);

    } catch (SQLException e) {

      System.out.println("Error while getting the connection!");
      e.printStackTrace();
    }
    return null;
  }


  // Delete a player by index
  public List<Player> getPlayer(int id) {

    try {

      return fetchPlayers(id);

    } catch (SQLException e) {

      System.err.println("Error while getting the connection!");
      e.printStackTrace();
    }
    return null;
  }


  // Add a new player to the DB
  public boolean commitPlayer(Player player) {

    try {

      Connection connection = Connector.getConnection();

      try {

        String query;

        // "C" means create a new one, either update it, asking for id
        if (player.getOperationType().equals("C")) {

          query = "INSERT INTO giocatori (" +
              "nome, " +
              "cognome, " +
              "alias, " +
              "numero_maglia_abituale, " +
              "data_di_nascita, " +
              "altezza, " +
              "peso, " +
              "id_rec"
              + ") VALUES (?, ?, ?, ?, ?, ?, ?, sq_id_giocatori.nextval)";

        } else {

          query = "UPDATE giocatori " +
              "SET " +
              "nome = ?, " +
              "cognome = ?, " +
              "alias = ?, " +
              "numero_maglia_abituale = ?, " +
              "data_di_nascita = ?, " +
              "altezza = ?, " +
              "peso = ? " +
              "WHERE id_rec = ?";
        }

        PreparedStatement statement = connection.prepareStatement(query);

        // Set the players values to the statement
        statement.setString(1, player.getFirstName().toUpperCase());
        statement.setString(2, player.getLastName().toUpperCase());
        statement.setString(3, player.getAlias().toUpperCase());

        if (player.getSquadNumber() != null) {

          statement.setInt(4, player.getSquadNumber());

        } else {

          statement.setNull(4, 0);
        }

        if (player.getBirthdate() != null) {

          statement.setDate(5, new java.sql.Date(player.getBirthdate().getTime()));

        } else {

          statement.setNull(5, 0);
        }

        if (player.getHeight() != null) {

          statement.setInt(6, player.getHeight());

        } else {
          statement.setNull(6, 0);
        }

        if (player.getWeight() != null) {

          statement.setInt(7, player.getWeight());

        } else {

          statement.setNull(7, 0);
        }

        if (!player.getOperationType().equals("C")) {

          statement.setInt(8, player.getId());
        }

        statement.execute();
        connection.close();

        System.out.println("Written successfully on the DB!");
        return true;

      } catch (SQLException ex) {

        System.err.println("Error while executing the query to the DB!");
        ex.printStackTrace();
      }
    } catch (SQLException e) {

      System.err.println("Error while getting the connection!");
      e.printStackTrace();
    }

    return false;
  }


  // Delete a player by object
  public void deletePlayer(Player player) {

    int id = player.getId();

    try {

      Connection connection = Connector.getConnection();
      String query = "DELETE FROM giocatori WHERE id_rec = ?";

      PreparedStatement statement = connection.prepareStatement(query);

      statement.setInt(1, id);

      statement.execute();
      connection.close();

      System.out.println("Deleted successfully from the DB!");

    } catch (SQLException e) {

      System.err.println("Error while getting the connection!");
      e.printStackTrace();
    }
  }


  // Return the players sorted accordingly
  private String getSortDirection(Map<String, String> sorter) {

    return "DESC".equalsIgnoreCase(sorter.get("direction")) ? "DESC" : "ASC";
  }


  // Get the filter values, check if they are classic or modern ones
  private String valueString(Map<String, String> filter) {

    String filterValueString = filter.get("value");
    String property = getProperty(filter.get("property"));

    if ("DATA_DI_NASCITA".equalsIgnoreCase(property)) {
      if (filterValueString.contains("T")) {

        System.out.println("Received modern date filters!");

        String[] date = filterValueString.split("T")[0].split("-");
        filterValueString = date[1] + "-" + (Integer.parseInt(date[2]) + 1) + "-" + date[0];

        System.out.println("Filter Value String: " + filterValueString);

      } else {

        System.out.println("Received classic date filters!");
      }
    }
    return filterValueString;
  }


  // Get the type of operation requested from ExtJS
  private String getOperation(Map<String, String> filter) {

    String operation = getOperator(filter.get("operator"));
    String operationString;
    String property = getProperty(filter.get("property"));
    System.out.println("Current operation: " + operation);
    if ("LIKE".equals(operation)) {
      operationString = " REGEXP_LIKE(" + property + ", ?)";
    } else {
      operationString = " " + property + " " + operation
          + ("DATA_DI_NASCITA".equals(property) ? " TO_DATE(?, 'mm/dd/yyyy')" : " ?");
    }
    return operationString;
  }


  // Translate the SQL operators
  private String getOperator(String operator) {

    String operatorString;
    System.out.println("Testing operator: " + operator);
    switch (operator) {
      case ">":
      case "gt":
        operatorString = ">";
        break;
      case "<":
      case "lt":
        operatorString = "<=";
        break;
      case "=":
      case "==":
      case "eq":
        operatorString = "=";
        break;
      default:
        operatorString = "LIKE";
    }
    System.out.println("Resulting operation: " + operatorString);
    return operatorString;
  }


  // Translate the SQL property
  private String getProperty(String property) {

    String propertyString;
    switch (property) {
      case "NOME":
        propertyString = "NOME";
        break;
      case "COGNOME":
        propertyString = "COGNOME";
        break;
      case "ALIAS":
        propertyString = "ALIAS";
        break;
      case "NUMERO_MAGLIA_ABITUALE":
        propertyString = "NUMERO_MAGLIA_ABITUALE";
        break;
      case "DATA_DI_NASCITA":
        propertyString = "DATA_DI_NASCITA";
        break;
      case "ALTEZZA":
        propertyString = "ALTEZZA";
        break;
      case "PESO":
        propertyString = "PESO";
        break;
      default:
        propertyString = "ID_REC";
    }
    return propertyString;
  }


  // Method for getting remote filters and sorters, ExtJS integration
  public int getPlayersCount(List<Map<String, String>> filters) {

    int totPlayers = 0;

    try {

      Connection connection = Connector.getConnection();


      String query = "SELECT COUNT(id_rec) " +
          "AS NUMERO_GIOCATORI " +
          "FROM GIOCATORI " +
          getFilterQueryString(filters, true);

      System.out.println(query);  // FOR DEBUG PURPOSES

      PreparedStatement statement = connection.prepareStatement(query);

      // Apply filters to the statement
      for (int i = 0; (filters != null) && (i < filters.size()); i++) {

        System.out.println("Count statement string: " + filters.get(i).get("value"));
        statement.setString(i + 1, valueString(filters.get(i)));
      }

      ResultSet resultSet = statement.executeQuery();

      // Count the totality of players
      totPlayers = 0;
      while (resultSet.next()) {

        totPlayers = resultSet.getInt("NUMERO_GIOCATORI");
      }

      System.out.println("Total Players: " + totPlayers);  // FOR DEBUG PURPOSES
      connection.close();

    } catch (SQLException e) {

      System.err.println("Error connecting to the DB!");
      e.printStackTrace();
    }

    return totPlayers;
  }


  // Get remote filters from ExtJS, either several or single
  private String getFilterQueryString(List<Map<String, String>> filters, boolean where) {

    StringBuilder filtersQueryString = new StringBuilder();

    if (filters == null) {

      return "";
    }

    for (Map<String, String> filter : filters) {

      if (where) {

        where = false;
        filtersQueryString.append(" WHERE");

      } else {

        filtersQueryString.append(" AND");
      }

      filtersQueryString.append(getOperation(filter));
    }

    return filtersQueryString.toString();
  }

}
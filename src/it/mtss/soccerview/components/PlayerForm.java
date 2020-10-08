package it.mtss.soccerview.components;

import it.mtss.soccerview.dao.PlayersDAO;
import it.mtss.soccerview.model.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.time.ZoneId;
import java.util.Date;

@SuppressWarnings(value = "unused")
public class PlayerForm {

  private final GridPane playerForm;


  public PlayerForm() {

    this.playerForm = new GridPane();
    playerForm.setId("form");

    // Define form's pane style
    playerForm.setPadding(new Insets(20));
    playerForm.setAlignment(Pos.CENTER);

    // Define a vbox layout with a 15px spacing
    VBox formLayout = new VBox(15);

    BorderStroke[] borderStrokes = new BorderStroke[] {
        new BorderStroke(Color.DARKGRAY,
                         BorderStrokeStyle.SOLID,
                         new CornerRadii(0.05, true),
                         new BorderWidths(1.0))
    };
    formLayout.setBorder(new Border(borderStrokes));
    formLayout.setPadding(new Insets(30));
    formLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                                                               new CornerRadii(0.04, true),
                                                               new Insets(0))));

    // Form header
    Text formHeader = new Text("Add/edit players");
    formHeader.setFont(Font.font("SanSerif", FontWeight.LIGHT, 20));
    formHeader.setFill(Color.BLACK);

    // First name
    Label firstNameLabel = new Label("First Name");
    TextField firstNameField = new TextField();
    firstNameField.setId("fname-field");

    // Last name
    Label lastNameLabel = new Label("Last Name");
    TextField lastNameField = new TextField();
    lastNameField.setId("lname-field");

    // Alias
    Label aliasLabel = new Label("Alias");
    TextField aliasField = new TextField();
    aliasField.setId("alias-field");

    // Shirt number
    Label squadNumberLabel = new Label("Squad number");
    TextField squadNumberField = new TextField();
    squadNumberField.setId("number-field");

    // Birthdate
    Label birthdateLabel = new Label("Birthdate (month/day/year)");
    DatePicker birthdateField = new DatePicker();
    birthdateField.setId("birth-field");

    // Height
    Label heightLabel = new Label("Height (in cm)");
    TextField heightField = new TextField();
    heightField.setId("height-field");

    // Weight
    Label weightLabel = new Label("Weight (in kg)");
    TextField weightField = new TextField();
    weightField.setId("weight-field");

    // Create Clear and Submit buttons
    HBox formButtons = new HBox(15);

    Button clearBtn = new Button("Clear");
    clearBtn.setId("clear-btn");

    Button submitBtn = new Button("Submit");
    submitBtn.setId("submit-btn");

    // Add form buttons to the hbox
    formButtons.getChildren().addAll(clearBtn, submitBtn);

    // Add listener to Clear button, iterate through its children and for
    // every textfield clear it, if it's a datepicker set its value at null
    clearBtn.setOnMouseClicked(event -> clearForm(formLayout));

    // Add listener to Submit button, calling the DAO to commit the player
    submitBtn.setOnMouseClicked(event -> submitForm(formLayout));

    // Add listeners to squad number, height and weight
    // fields for numeric only inputs
    addNumericOnlyListener(squadNumberField);
    addNumericOnlyListener(heightField);
    addNumericOnlyListener(weightField);

    // Add fields to form
    formLayout.getChildren().addAll(formHeader,
                                    firstNameLabel,
                                    firstNameField,
                                    lastNameLabel,
                                    lastNameField,
                                    aliasLabel,
                                    aliasField,
                                    squadNumberLabel,
                                    squadNumberField,
                                    birthdateLabel,
                                    birthdateField,
                                    heightLabel,
                                    heightField,
                                    weightLabel,
                                    weightField,
                                    formButtons);

    playerForm.getChildren().add(formLayout);

  }


  public Pane getPlayerForm() {

    return this.playerForm;
  }


  private void addNumericOnlyListener(TextField thisField) {

    // Remove every non-numeric character
    thisField.textProperty().addListener((ob, ov, nv) -> {
      if (!nv.matches("\\d")) {
        thisField.setText(nv.replaceAll("\\D+", ""));
      }
    });
  }


  private void clearForm(VBox thisForm) {

    for (Node node : thisForm.getChildren()) {

      if (node.getClass().getName().equals("javafx.scene.control.TextField")) {

        TextField thisField = (TextField) node;
        thisField.setText("");

      } else if (node.getClass().getName().equals("javafx.scene.control.DatePicker")) {

        DatePicker thisField = (DatePicker) node;
        thisField.getEditor().clear();
        thisField.setValue(null);
      }
    }

    System.out.println("Form cleared!");
  }


  private void submitForm(VBox thisForm) {

    // Fetch fields values
    TextField firstName = (TextField) thisForm.lookup("#fname-field");
    TextField lastName = (TextField) thisForm.lookup("#lname-field");
    TextField alias = (TextField) thisForm.lookup("#alias-field");
    TextField number = (TextField) thisForm.lookup("#number-field");
    DatePicker birthdate = (DatePicker) thisForm.lookup("#birth-field");
    TextField height = (TextField) thisForm.lookup("#height-field");
    TextField weight = (TextField) thisForm.lookup("#weight-field");

    // Set the values to a new player
    Player thisPlayer = new Player();
    thisPlayer.setFirstName(firstName.getText());
    thisPlayer.setLastName(lastName.getText());
    thisPlayer.setAlias(alias.getText());
    if (!number.getText().equals("")) {
      thisPlayer.setSquadNumber(Integer.parseInt(number.getText()));
    }
    if (birthdate.getValue() != null) {
      ZoneId thisZone = ZoneId.systemDefault();
      thisPlayer.setBirthdate(Date.from(birthdate.getValue().atStartOfDay(thisZone).toInstant()));
    }
    if (!height.getText().equals("")) {
      thisPlayer.setHeight(Integer.parseInt(height.getText()));
    }
    if (!weight.getText().equals("")) {
      thisPlayer.setWeight(Integer.parseInt(weight.getText()));
    }
    thisPlayer.setOperationType("C");

    // Commit the new player to the DB
    PlayersDAO dao = new PlayersDAO();
    boolean success = dao.commitPlayer(thisPlayer);
    // boolean success = true; // UNCOMMENT FOR DEBUG PURPOSES

    if (success) {

      System.out.println("Player added successfully!");

      // User feedback
      Alert successAlert = new Alert(AlertType.INFORMATION);
      successAlert.setTitle("Success!");
      successAlert.setHeaderText("Player added successfully!");
      successAlert.setContentText("You can see the newly added player on the list.");
      successAlert.getButtonTypes().set(0, ButtonType.OK);
      successAlert.show();

      // Clear the form
      clearForm(thisForm);

      // Refresh the list of players


    }
  }

}

package it.mtss.soccerview.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Player {

  @SerializedName("ID_REC")
  private Integer id;

  @SerializedName("NOME")
  private String firstName;

  @SerializedName("COGNOME")
  private String lastName;

  @SerializedName("ALIAS")
  private String alias;

  @SerializedName("NUMERO_MAGLIA_ABITUALE")
  private Integer squadNumber;

  @SerializedName("DATA_DI_NASCITA")
  private Date birthdate;

  @SerializedName("ALTEZZA")
  private Integer height;

  @SerializedName("PESO")
  private Integer weight;

  @SerializedName("OPERATION_TYPE")
  private String operationType;


  public Integer getId() {

    return id;
  }


  public void setId(Integer id) {

    this.id = id;
  }


  public String getFirstName() {

    return firstName;
  }


  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }


  public String getLastName() {

    return lastName;
  }


  public void setLastName(String lastName) {

    this.lastName = lastName;
  }


  public String getAlias() {

    return alias;
  }


  public void setAlias(String alias) {

    this.alias = alias;
  }


  public Integer getSquadNumber() {

    return squadNumber;
  }


  public void setSquadNumber(Integer squadNumber) {

    this.squadNumber = squadNumber;
  }


  public Date getBirthdate() {

    return birthdate;
  }


  public void setBirthdate(Date birthdate) {

    this.birthdate = birthdate;
  }


  public Integer getHeight() {

    return height;
  }


  public void setHeight(Integer height) {

    this.height = height;
  }


  public Integer getWeight() {

    return weight;
  }


  public void setWeight(Integer weight) {

    this.weight = weight;
  }


  public String getOperationType() {

    return operationType;
  }


  public void setOperationType(String operationType) {

    this.operationType = operationType;
  }

}
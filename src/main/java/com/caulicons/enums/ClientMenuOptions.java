package com.caulicons.enums;

public enum ClientMenuOptions {
  LOGIN(1, "Login"),
  REGISTER(2, "Register"),
  LIST_ALL_CLIENTS(3, "List all clients"),
  UPDATE_CLIENT(4, "Update client"),
  DELETE_CLIENT(5, "Delete client"),
  EXIT(6, "Exit");

  public final int value;
  public final String description;

  ClientMenuOptions(int value, String description) {

    this.value = value;
    this.description = description;
  }

  public static ClientMenuOptions fromValue(int value) {

    for (ClientMenuOptions option : values()) {

      if (option.value == value)
        return option;
    }

    throw new IllegalArgumentException("Invalid menu option " + value);
  }

  public String getDescription() {
    return description;
  }
}

package com.caulicons.enums;

public enum MenuOption {
  CREATE_ACCOUNT(1, "Create a new account"),
  LIST_ACCOUNTS(2, "List all accounts"),
  FIND_ACCOUNT(3, "Find account by ID"),
  UPDATE_ACCOUNT(4, "Update account"),
  DELETE_ACCOUNT(5, "Delete account"),
  WITHDRAW(6, "Withdraw"),
  DEPOSIT(7, "Deposit"),
  TRANSFER(8, "Transfer value between accounts"),
  EXIT(9, "Exit");

  private final int value;
  private final String description;

  MenuOption(int value, String description) {
    this.value = value;
    this.description = description;
  }

  public static MenuOption fromValue(int value) {
    for (MenuOption option : values()) {
      if (option.value == value)
        return option;
    }
    throw new IllegalArgumentException("Invalid menu option: " + value);
  }

  public String getDescription() {
    return description;
  }
}
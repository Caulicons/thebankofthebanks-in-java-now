package com.caulicons.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.UUID;

import com.caulicons.Services.BankService;
import com.caulicons.models.account.Account;
import com.caulicons.models.account.CurrentAccount;
import com.caulicons.models.account.SaveAccount;
import com.caulicons.models.client.Client;
import com.caulicons.types.TypeAccount;
import com.caulicons.utils.InputHandler;
import com.caulicons.utils.MenuOption;

public class BankMenu {

  private final BankService bankService;
  private final InputHandler input;
  private Client client;

  public BankMenu() {
    this.input = new InputHandler();
    this.bankService = new BankService();
  }

  public void start(Client client) {
    this.client = client;
    while (true) {
      printMenu();
      int option = getOption();
      handleOption(MenuOption.fromValue(option));
      keyPress();
    }
  }

  private void handleOption(MenuOption option) {
    switch (option) {
      case CREATE_ACCOUNT -> createAccount();
      case LIST_ACCOUNTS -> listAllAccounts();
      case FIND_ACCOUNT -> findAccountById();
      case UPDATE_ACCOUNT -> updateAccount();
      case DELETE_ACCOUNT -> deleteAccount();
      case WITHDRAW -> withdraw();
      case DEPOSIT -> deposit();
      case TRANSFER -> transfer();
      case EXIT -> exit();
    }
  }

  private static void printMenu() {
    System.out.println("""
        ********************************************
               Welcome to The Bank of Banks! 🏦
        ********************************************
        Please, choose one of the following options:
        """);

    for (MenuOption option : MenuOption.values())
      System.out.printf("%d - %s%n", option.ordinal() + 1, option.getDescription());

  }

  private int getOption() {
    try {
      return input.readInt("Option: ");
    } catch (InputMismatchException e) {
      return 0;
    }
  }

  private Optional<Account> getAccount() {

    UUID uuid = input.readUUID("Enter the account's UUID: ");
    Optional<Account> acc = bankService.findById(uuid);

    acc.ifPresentOrElse(account -> {
    }, () -> System.out.println("Account not found."));
    return acc;
  }

  private void createAccount() {
    int agency = input.readInt("Enter the account's agency: ");
    int type = input.readInt("Enter the account's type (1 - Current | 2 - Save): ");

    if (type == 1)
      bankService.register(new CurrentAccount(agency, client));
    else if (type == 2) {
      LocalDate birthday = input.readDate("Enter the account's birthday (yyyy-mm-dd): ");
      bankService.register(new SaveAccount(agency, client, birthday));
    } else {
      System.out.println("Invalid account type. Please, try again.");
    }
  }

  private void listAllAccounts() {
    bankService.listAll();
  }

  private void findAccountById() {
    getAccount().ifPresent(Account::info);
  }

  private void updateAccount() {

    getAccount().ifPresent(accountUp -> {
      int agencyUp = input.readInt("Enter the new agency: ");

      Account newAccount;
      if (accountUp.getType() == TypeAccount.SAVE) {
        var birthdayUp = input.readDate("Enter the new birthday (yyyy-mm-dd): ");
        newAccount = new SaveAccount(agencyUp, client, birthdayUp);
      } else {
        newAccount = new CurrentAccount(agencyUp, client);
      }

      newAccount.setId(accountUp.getId());
      bankService.update(newAccount);
    });

  }

  private void deleteAccount() {

    getAccount().ifPresent(acc -> bankService.remove(acc.getId()));
  }

  private void withdraw() {

    getAccount().ifPresent(acc -> {
      float value = input.readFloat("Enter the value to withdraw: ");
      bankService.withdraw(acc.getId(), value);
    });
  }

  private void deposit() {

    getAccount().ifPresent(acc -> {
      float value = input.readFloat("Enter the value to deposit: ");
      bankService.deposit(acc.getId(), value);
    });
  }

  private void transfer() {

    UUID source = input.readUUID("Enter the source account's UUID: ");
    UUID target = input.readUUID("Enter the target account's UUID: ");
    float value = input.readFloat("Enter the value to transfer: ");

    bankService.transfer(source, target, value);
  }

  private void exit() {
    input.close();
    System.out.println("Thank you for using The Bank of Banks!");
    System.exit(0);
  }

  private static void keyPress() {
    try {
      System.out.println("\n\nPress any key to continue...");
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
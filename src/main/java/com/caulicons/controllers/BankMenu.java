package com.caulicons.controllers;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.UUID;

import com.caulicons.builders.AccountBuilder;
import com.caulicons.enums.MenuOption;
import com.caulicons.interfaces.MenuI;
import com.caulicons.models.account.Account;
import com.caulicons.models.account.CurrentAccount;
import com.caulicons.models.account.SaveAccount;
import com.caulicons.models.client.Client;
import com.caulicons.services.BankService;
import com.caulicons.types.TypeAccount;
import com.caulicons.utils.InputHandler;
import com.caulicons.utils.Utils;

public class BankMenu implements MenuI<MenuOption, Account> {

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
      if (option == 9) {
        exit();
        break;
      }
      handleOption(MenuOption.fromValue(option));
      Utils.keyPress();
    }
  }

  public void handleOption(MenuOption option) {
    switch (option) {
      case CREATE_ACCOUNT -> register();
      case LIST_ACCOUNTS -> listAll();
      case FIND_ACCOUNT -> findAccountById();
      case UPDATE_ACCOUNT -> update();
      case DELETE_ACCOUNT -> delete();
      case WITHDRAW -> withdraw();
      case DEPOSIT -> deposit();
      case TRANSFER -> transfer();
      case EXIT -> exit();
    }
  }

  public void printMenu() {
    System.out.println("""
        ********************************************
               Welcome to The Bank of Banks! 🏦
        ********************************************
        Please, choose one of the following options:
        """);

    for (MenuOption option : MenuOption.values())
      System.out.printf("%d - %s%n", option.ordinal() + 1, option.getDescription());

  }

  public int getOption() {
    try {
      return input.readInt("Option: ");
    } catch (InputMismatchException e) {
      return 0;
    }
  }

  public Optional<Account> getOne() {

    UUID uuid = input.readUUID("Enter the account's UUID: ");
    Optional<Account> acc = bankService.findById(uuid);

    acc.ifPresentOrElse(account -> {
    }, () -> System.out.println("Account not found."));
    return acc;
  }

  public void register() {

    int agency = input.readInt("Enter the account's agency: ");
    int type = input.readInt("Enter the account's type (1 - Current | 2 - Save): ");

    AccountBuilder newAcc = new AccountBuilder()
        .withAgency(agency)
        .withClient(client)
        .withType(type == 1 ? TypeAccount.CURRENT : TypeAccount.SAVE);

    if (type == 2) {
      LocalDate birthday = input.readDate("Enter the account's birthday (yyyy-mm-dd): ");
      newAcc.withBirthday(birthday);
    }

    bankService.register(newAcc.build());
  }

  public void listAll() {
    bankService.listAll();
  }

  public void findAccountById() {
    getOne().ifPresent(Account::info);
  }

  public void update() {

    getOne().ifPresent(accountUp -> {
      int agencyUp = input.readInt("Enter the new agency: ");

      AccountBuilder updateAcc = new AccountBuilder()
          .withId(accountUp.getId())
          .withAgency(agencyUp)
          .withClient(client)
          .withType(accountUp.getType())
          .withBalance(accountUp.getBalance());

      if (accountUp.getType() == TypeAccount.SAVE) {
        var birthdayUp = input.readDate("Enter the new birthday (yyyy-mm-dd): ");
        updateAcc.withBirthday(birthdayUp);
      }

      bankService.update(updateAcc.build());
    });

  }

  public void delete() {

    getOne().ifPresent(acc -> bankService.remove(acc.getId()));
  }

  public void withdraw() {

    getOne().ifPresent(acc -> {
      float value = input.readFloat("Enter the value to withdraw: ");
      bankService.withdraw(acc.getId(), value);
    });
  }

  public void deposit() {

    getOne().ifPresent(acc -> {
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

  public void exit() {
    System.out.format("Thank for trust you money with us %s%n 💌", client.getName());
  }

}
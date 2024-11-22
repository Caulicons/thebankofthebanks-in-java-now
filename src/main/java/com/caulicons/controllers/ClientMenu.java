package com.caulicons.controllers;

import java.util.InputMismatchException;
import java.util.Optional;

import com.caulicons.builders.ClientBuilder;
import com.caulicons.enums.ClientMenuOptions;
import com.caulicons.interfaces.InputValidator;
import com.caulicons.interfaces.MenuI;
import com.caulicons.models.client.Client;
import com.caulicons.services.ClientService;
import com.caulicons.utils.InputHandler;
import com.caulicons.utils.Utils;

public class ClientMenu implements MenuI<ClientMenuOptions, Client> {

  private final InputHandler input;
  private final ClientService clientService;
  private final BankMenu bankMenu;

  public ClientMenu() {
    this.input = new InputHandler();
    this.clientService = new ClientService();
    this.bankMenu = new BankMenu();
  }

  public void start() {
    while (true) {
      printMenu();
      handleOption(ClientMenuOptions.fromValue(getOption()));
      Utils.keyPress();
    }
  }

  public void printMenu() {

    System.out.println("""
        ********************************************
               Welcome to The Bank of Banks! 🏦
        ********************************************
        Please, choose one of the following options:
        """);

    for (ClientMenuOptions option : ClientMenuOptions.values())
      System.out.format("%d - %s%n", option.ordinal() + 1, option.getDescription());
  }

  public int getOption() {

    try {
      return input.readInt("Choose an option: ");
    } catch (InputMismatchException e) {
      System.out.println("Invalid option, please try again.");
      return getOption();
    }
  }

  public void handleOption(ClientMenuOptions option) {
    switch (option) {
      case LOGIN -> login();
      case REGISTER -> register();
      case LIST_ALL_CLIENTS -> listAll();
      case UPDATE_CLIENT -> update();
      case DELETE_CLIENT -> delete();
      case EXIT -> exit();
      default -> System.out.println("Invalid option, please try again.");
    }
  }

  public void register() {

    String name = input.readString("Enter the client's name: ");
    String cpf = input.readString("Enter the client's CPF: ");

    Client client = new ClientBuilder()
        .withName(name)
        .withCpf(cpf)
        .build();

    clientService.register(client);
  }

  public void listAll() {

    clientService.listAll();
  }

  public void update() {
    getOne().ifPresent(c -> {

      String name = input.readString("Enter the new client's name: ");

      Client updatedClient = new ClientBuilder()
          .withName(name)
          .withCpf(c.getCpf())
          .build();

      clientService.update(updatedClient);
    });
  }

  public void delete() {

    getOne().ifPresent(c -> clientService.remove(c.getCpf()));
  }

  public void exit() {

    input.close();
    System.out.println("Thank you for using The Bank of Banks!");
    System.exit(0);
  }

  public Optional<Client> getOne() {

    String cpf = getCPF();
    Optional<Client> client = clientService.findById(cpf);

    client.ifPresentOrElse(c -> {
    }, () -> System.out.println("Client not found."));
    return client;
  }

  public void login() {

    getOne().ifPresent(bankMenu::start);
  }

  private String getCPF() {
    return input.readWithValidation(
        "Enter the client's CPF: ",

        new InputValidator<String>() {

          public String get(String prompt) {
            return input.readString(prompt);
          }

          @Override
          public boolean isValid(String value) {
            return value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
          }

          @Override
          public String getErrorMessage() {
            return "Invalid CPF format. Use XXX.XXX.XXX-XX";
          }
        });
  }

}

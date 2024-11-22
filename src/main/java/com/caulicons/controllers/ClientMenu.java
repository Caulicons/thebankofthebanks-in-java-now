package com.caulicons.controllers;

import java.util.InputMismatchException;
import java.util.Optional;

import com.caulicons.enums.ClientMenuOptions;
import com.caulicons.models.client.Client;
import com.caulicons.services.ClientService;
import com.caulicons.utils.InputHandler;
import com.caulicons.utils.Utils;

public class ClientMenu {

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

  private void printMenu() {

    System.out.println("""
        ********************************************
               Welcome to The Bank of Banks! 🏦
        ********************************************
        Please, choose one of the following options:
        """);

    for (ClientMenuOptions option : ClientMenuOptions.values())
      System.out.format("%d - %s%n", option.ordinal() + 1, option.getDescription());
  }

  private int getOption() {

    try {
      return input.readInt("Choose an option: ");
    } catch (InputMismatchException e) {
      System.out.println("Invalid option, please try again.");
      return getOption();
    }
  }

  private void handleOption(ClientMenuOptions option) {
    switch (option) {
      case LOGIN -> login();
      case REGISTER -> register();
      case LIST_ALL_CLIENTS -> allClients();
      case UPDATE_CLIENT -> updateClient();
      case DELETE_CLIENT -> deleteClient();
      case EXIT -> exit();
      default -> System.out.println("Invalid option, please try again.");
    }
  }

  private void register() {

    String name = input.readString("Enter the client's name: ");
    String cpf = input.readString("Enter the client's CPF: ");
    clientService.register(new Client(cpf, name));
  }

  private void login() {

    getClient().ifPresent(bankMenu::start);
  }

  private void allClients() {

    clientService.listAll();
  }

  private void updateClient() {
    getClient().ifPresent(c -> {

      String name = input.readString("Enter the new client's name: ");
      c.setName(name);
      clientService.update(c);
    });
  }

  private void deleteClient() {

    getClient().ifPresent(c -> clientService.remove(c.getCpf()));
  }

  private void exit() {

    input.close();
    System.out.println("Thank you for using The Bank of Banks!");
    System.exit(0);
  }

  private Optional<Client> getClient() {

    String cpf = input.readString("Enter the client's CPF: ");
    Optional<Client> client = clientService.findById(cpf);

    client.ifPresentOrElse(c -> {
    }, () -> System.out.println("Client not found."));
    return client;
  }
}

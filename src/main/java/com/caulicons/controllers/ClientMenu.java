package com.caulicons.controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;

import com.caulicons.Services.ClientService;
import com.caulicons.models.client.Client;
import com.caulicons.utils.InputHandler;

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
      handleOption(getOption());
      keyPress();
    }
  }

  /* TODO: Extract this option and create enums */
  private void printMenu() {

    System.out.println("""
        ********************************************
               Welcome to The Bank of Banks! 🏦
        ********************************************
        Please, choose one of the following options:
        1 - Login
        2 - Register
        3 - List all clients
        4 - Update client
        5 - Delete client
        6 - Exit
        """);
  }

  private int getOption() {

    try {
      return input.readInt("Choose an option: ");
    } catch (InputMismatchException e) {
      System.out.println("Invalid option, please try again.");
      return getOption();
    }
  }

  // TODO: Extract this method to a new util class
  private void keyPress() {
    try {
      System.out.println("\n\nPress any key to continue...");
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handleOption(int option) {
    switch (option) {
      case 1 -> login();
      case 2 -> register();
      case 3 -> allClients();
      case 4 -> updateClient();
      case 5 -> deleteClient();
      case 6 -> exit();

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

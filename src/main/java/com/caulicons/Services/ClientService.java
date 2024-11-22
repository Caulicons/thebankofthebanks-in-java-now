package com.caulicons.Services;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import com.caulicons.models.client.Client;
import com.caulicons.repositories.ClientRepository;

public class ClientService implements ClientRepository {

  public final List<Client> clients;

  public ClientService() {
    this.clients = List.of(
        new Client("123.456.789-00", "Vítor Oliveira"),
        new Client("987.654.321-00", "João Silva"),
        new Client("123.456.999-00", "Eduardo Oliveira"));
  }

  @Override
  public Optional<Client> findById(String id) {
    return clients.stream().filter(client -> client.getCpf().equals(id)).findFirst();
  }

  @Override
  public void register(Client client) {

    clients.add(client);
    System.out.format("Client with the CPF %s created successfully", client.getCpf());
  }

  @Override
  public void update(Client client) {

    clients.stream()
        .filter(c -> c.getCpf().equals(client.getCpf()))
        .findFirst()
        .ifPresent(c -> {
          clients.set(clients.indexOf(c), client);
          System.out.format("Client with the CPF %s updated successfully", client.getCpf());
        });
  }

  @Override
  public void remove(String id) {

    if (!clients.removeIf(client -> client.getCpf().equals(id))) {
      System.out.format("Client with the CPF %s not found", id);
      return;
    }

    System.out.format("Client with the CPF %s removed successfully", id);
  }

  @Override
  public void listAll() {

    clients.stream().forEach(Client::info);
  }

  @Override
  public Client login(String cpf) {

    Optional<Client> client = findById(cpf);

    if (client.isPresent()) {
      System.out.println("Login successful");
      return client.get();
    }

    System.out.println("Client not found");
    return null;
  }

}

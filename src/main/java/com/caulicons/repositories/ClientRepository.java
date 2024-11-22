package com.caulicons.repositories;

import java.util.Optional;

import com.caulicons.models.client.Client;

/* case 1 -> login();
      case 2 -> register();
      case 3 -> allClients();
      case 4 -> updateClient();
      case 5 -> deleteClient();
      case 6 -> exit(); */
public interface ClientRepository {

  void register(Client client);

  void update(Client client);

  void remove(String id);

  void listAll();

  Optional<Client> findById(String id);

  Client login(String cpf);
}

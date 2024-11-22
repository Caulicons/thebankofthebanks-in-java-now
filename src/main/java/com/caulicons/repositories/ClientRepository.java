package com.caulicons.repositories;

import com.caulicons.models.client.Client;

public interface ClientRepository extends Repository<Client, String> {

  Client login(String cpf);
}

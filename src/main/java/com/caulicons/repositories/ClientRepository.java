package com.caulicons.repositories;

import com.caulicons.interfaces.RepositoryI;
import com.caulicons.models.client.Client;

public interface ClientRepository extends RepositoryI<Client, String> {

  Client login(String cpf);
}

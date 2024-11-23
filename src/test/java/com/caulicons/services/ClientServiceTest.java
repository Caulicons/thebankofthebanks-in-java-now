package com.caulicons.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.caulicons.models.client.Client;

class ClientServiceTest {

  private ClientService clientService;

  @BeforeEach
  public void setUp() {

    clientService = new ClientService();
    clientService.clients.clear();
  }

  @Test
  void shouldRegisterNewClient() {

    Client client = new Client("123.456.789-00", "Test Client");

    clientService.register(client);

    Optional<Client> found = clientService.findById("123.456.789-00");

    assertTrue(found.isPresent());
    assertEquals("Test Client", found.get().getName());
  }

  @Test
  void cantDuplicateClientsWithTheSameCPF() {

    Client client1 = new Client("123.456.789-00", "Test Client1");
    Client client2 = new Client("123.456.789-00", "Test Client2");

    clientService.register(client1);
    clientService.register(client2);

    Optional<Client> found = clientService.findById("123.456.789-00");

    assertTrue(found.isPresent());
    assertEquals("Test Client1", found.get().getName());
  }
}

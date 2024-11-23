package com.caulicons.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.caulicons.models.account.Account;
import com.caulicons.models.account.CurrentAccount;
import com.caulicons.models.client.Client;

class BankServiceTest {
  private BankService bankService;

  @Test
  void shouldTransferMoneyBetweenAccounts() {
    bankService = new BankService();
    Client client = new Client("123.456.789-00", "Test Client");

    Account source = new CurrentAccount(123, client);
    Account target = new CurrentAccount(123, client);

    source.deposit(1000);
    bankService.register(source);
    bankService.register(target);

    bankService.transfer(source.getId(), target.getId(), 500);

    assertEquals(500, source.getBalance());
    assertEquals(500, target.getBalance());
  }
}
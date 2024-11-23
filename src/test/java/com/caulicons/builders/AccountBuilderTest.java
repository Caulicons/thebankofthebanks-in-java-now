package com.caulicons.builders;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.caulicons.models.account.Account;
import com.caulicons.models.client.Client;
import com.caulicons.types.TypeAccount;

class AccountBuilderTest {
  @Test
  void shouldBuildCurrentAccount() {
    Client client = new Client("123.456.789-00", "Test Client");

    Account account = new AccountBuilder()
        .withAgency(123)
        .withClient(client)
        .withType(TypeAccount.CURRENT)
        .build();

    assertNotNull(account);
    assertEquals(TypeAccount.CURRENT, account.getType());
    assertEquals(123, account.getAgency());
    assertEquals(client, account.getCardHolder());
  }
}
package com.caulicons.builders;

import java.time.LocalDate;
import java.util.UUID;

import com.caulicons.models.account.Account;
import com.caulicons.models.account.CurrentAccount;
import com.caulicons.models.account.SaveAccount;
import com.caulicons.models.client.Client;
import com.caulicons.types.TypeAccount;

public class AccountBuilder {
  private UUID id;
  private int agency;
  private Client client;
  private TypeAccount type;
  private LocalDate birthday;
  private float balance;

  public AccountBuilder withId(UUID id) {
    this.id = id;
    return this;
  }

  public AccountBuilder withAgency(int agency) {
    this.agency = agency;
    return this;
  }

  public AccountBuilder withClient(Client client) {
    this.client = client;
    return this;
  }

  public AccountBuilder withType(TypeAccount type) {
    this.type = type;
    return this;
  }

  public AccountBuilder withBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  public AccountBuilder withBalance(float balance) {
    this.balance = balance;
    return this;
  }

  public Account build() {
    validateFields();

    Account account = type == TypeAccount.SAVE
        ? new SaveAccount(agency, client, birthday)
        : new CurrentAccount(agency, client);

    if (id != null) {
      account.setId(id);
    }
    if (balance > 0) {
      account.deposit(balance);
    }
    return account;
  }

  private void validateFields() {
    if (agency <= 0) {
      throw new IllegalArgumentException("Agency must be positive");
    }
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null");
    }
    if (type == null) {
      throw new IllegalArgumentException("Account type cannot be null");
    }
    if (type == TypeAccount.SAVE && birthday == null) {
      throw new IllegalArgumentException("Birthday required for savings account");
    }
  }
}
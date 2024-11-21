package com.caulicons.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.caulicons.models.account.Account;
import com.caulicons.models.account.CurrentAccount;
import com.caulicons.models.account.SaveAccount;
import com.caulicons.models.client.Client;
import com.caulicons.repositories.AccountRepository;

public class BankService implements AccountRepository {

  private final List<Account> accounts;

  public BankService() {
    this.accounts = new ArrayList<>();
    Client c1 = new Client("Vítor Oliveira", "123.456.789-00");
    accounts.add(new CurrentAccount(123, c1));
    accounts.add(new SaveAccount(123, c1, LocalDate.of(2019, 11, 25)));
  }

  @Override
  public void register(Account account) {
    accounts.add(account);
    System.out.format("Account with the id %s created successfully", account.getId());
  }

  @Override
  public void update(Account account) {
    accounts.stream()
        .filter(acc -> acc.getId().equals(account.getId()))
        .findFirst()
        .ifPresent(acc -> {
          accounts.set(accounts.indexOf(acc), account);
          System.out.format("Account with the id %s updated successfully", account.getId());
        });
  }

  @Override
  public void remove(UUID id) {
    accounts.removeIf(account -> account.getId().equals(id));
    System.out.format("Account with the id %s removed successfully", id);
  }

  @Override
  public void listAll() {
    accounts.stream().forEach(Account::info);
  }

  @Override
  public Optional<Account> findById(UUID id) {
    return accounts.stream().filter(account -> account.getId().equals(id)).findFirst();
  }

  @Override
  public void deposit(UUID id, float value) {
    findById(id).ifPresentOrElse(
        acc -> acc.deposit(value),
        () -> System.out.println("Account not found"));
  }

  @Override
  public void withdraw(UUID id, float value) {

    findById(id).ifPresentOrElse(
        acc -> acc.withdraw(value),
        () -> System.out.println("Account not found"));
  }

  @Override
  public void transfer(UUID idOrigin, UUID idDestiny, float value) {

    Optional<Account> accountOrigin = findById(idOrigin);
    Optional<Account> accountDestiny = findById(idDestiny);

    if (accountOrigin.isPresent() && accountDestiny.isPresent()) {
      accountOrigin.get().withdraw(value);
      accountDestiny.get().deposit(value);
    } else {
      System.out.println("Account not found");
    }
  }

}

package com.caulicons.repositories;

import java.util.Optional;
import java.util.UUID;

import com.caulicons.models.account.Account;

public interface AccountRepository {

  void register(Account account);

  void update(Account account);

  void remove(UUID id);

  void listAll();

  Optional<Account> findById(UUID id);

  void deposit(UUID id, float value);

  void withdraw(UUID id, float value);

  void transfer(UUID idOrigin, UUID idDestiny, float value);
}

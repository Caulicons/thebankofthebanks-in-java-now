package com.caulicons.repositories;

import java.util.UUID;

import com.caulicons.models.account.Account;

public interface AccountRepository extends Repository<Account, UUID> {

  void deposit(UUID id, float value);

  void withdraw(UUID id, float value);

  void transfer(UUID idOrigin, UUID idDestiny, float value);
}

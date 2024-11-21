package com.caulicons.models.account;

import java.util.Random;

import com.caulicons.models.client.Client;
import com.caulicons.types.TypeAccount;

public class CurrentAccount extends Account {

  private float overdraft;

  public CurrentAccount(int agency, Client cardHolder) {
    super(agency, TypeAccount.CURRENT, cardHolder);
    this.overdraft = new Random().nextFloat() * 1000;
  }

  public float getOverdraft() {
    return overdraft;
  }

  public void setOverdraft(float overdraft) {
    this.overdraft = overdraft;
  }

  @Override
  public boolean withdraw(float value) {
    if (value > (this.getBalance() + this.overdraft)) {
      System.out.println("Insufficient funds!");
      return false;
    } else {
      this.setBalance(this.getBalance() - value);
      System.out.println("Withdrawal of " + getBRCurrency(value) + " made successfully!");
      return true;
    }
  }

  @Override
  public void info() {
    System.out.println("""
        Account information:
        ID: %s
        Agency: %d
        Type: %s
        Cardholder: %s
        Balance: %s
        Overdraft: %s
        """.formatted(this.getId(), this.getAgency(), this.getType(), this.getCardHolder(),
        this.getBRCurrency(this.getBalance()),
        this.getBRCurrency(this.getOverdraft())));
  }

}

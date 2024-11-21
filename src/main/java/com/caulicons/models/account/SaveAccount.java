package com.caulicons.models.account;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Random;

import com.caulicons.models.client.Client;
import com.caulicons.types.TypeAccount;

public class SaveAccount extends Account {

  private float interestRate;
  private LocalDate birthday;

  public SaveAccount(int agency, Client cardHolder, LocalDate birthday) {
    super(agency, TypeAccount.SAVE, cardHolder);
    this.interestRate = new Random().nextFloat(60);
    this.birthday = birthday;
  }

  public float getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(float interestRate) {
    this.interestRate = interestRate;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
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
        Interest rate: %s
        Birthday: %s
        """.formatted(this.getId(), this.getAgency(), this.getType(), this.getCardHolder(),
        this.getBRCurrency(this.getBalance()),
        NumberFormat.getPercentInstance().format(this.getInterestRate()),
        this.getBirthday().toString()));
  }

  public void updateInterestRate(float interestRate) {
    this.interestRate = interestRate;
  }

  public void updateBalance() {
    this.setBalance(this.getBalance() + (this.getBalance() * this.interestRate));
  }
}

package com.caulicons.models.account;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

import com.caulicons.models.client.Client;
import com.caulicons.types.TypeAccount;

public abstract class Account {

  private UUID id;
  private int agency;
  private TypeAccount type;
  private Client cardHolder;
  private float balance;

  /**
   * @param agency
   * @param type
   * @param cardHolder
   * @param balance
   */
  protected Account(int agency, TypeAccount type, Client cardHolder) {
    this.id = UUID.randomUUID();
    this.agency = agency;
    this.type = type;
    this.cardHolder = cardHolder;
    this.balance = 0;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getAgency() {
    return agency;
  }

  public void setAgency(int agency) {
    this.agency = agency;
  }

  public TypeAccount getType() {
    return type;
  }

  public void setType(TypeAccount type) {
    this.type = type;
  }

  public Client getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(Client cardHolder) {
    this.cardHolder = cardHolder;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public boolean withdraw(float value) {

    if (value > this.getBalance()) {
      System.out.println("Insufficient funds!");
      return false;
    }

    this.setBalance(this.getBalance() - value);
    return true;
  }

  public void deposit(float value) {
    this.setBalance(this.getBalance() + value);
  }

  public void info() {

    System.out.println("""

        Account Information
        -------------------
        ID: %s
        Agency: %d
        Type: %s
        Card Holder: %s
        Balance: %s
        """.formatted(this.getId(), this.getAgency(), this.getType(), this.getCardHolder(),
        this.getBRCurrency(this.getBalance())));
  }

  protected String getBRCurrency(float value) {
    Locale brazil = new Locale("pt", "BR");
    NumberFormat nf = NumberFormat.getCurrencyInstance(brazil);
    return nf.format(value);
  }

  @Override
  public String toString() {
    return "Account [id=" + id + ", agency=" + agency + ", type=" + type + ", cardHolder=" + cardHolder + ", balance="
        + balance + "]";
  }
}

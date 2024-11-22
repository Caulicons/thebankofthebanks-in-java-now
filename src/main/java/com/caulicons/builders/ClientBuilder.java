package com.caulicons.builders;

import com.caulicons.models.client.Client;

public class ClientBuilder {
  private String cpf;
  private String name;

  public ClientBuilder withCpf(String cpf) {
    this.cpf = cpf;
    return this;
  }

  public ClientBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public Client build() {
    validateFields();
    return new Client(cpf, name);
  }

  private void validateFields() {
    if (cpf == null || cpf.trim().isEmpty()) {
      throw new IllegalArgumentException("CPF cannot be null or empty");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
      throw new IllegalArgumentException("Invalid CPF format");
    }
  }
}
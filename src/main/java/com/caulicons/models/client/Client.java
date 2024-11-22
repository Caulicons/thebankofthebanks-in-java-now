package com.caulicons.models.client;

public class Client {

  private String cpf;
  private String name;

  public Client(String cpf, String name) {
    this.cpf = cpf;
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void info() {
    System.out.println("""
        {
            cpf: %s,
            name: %s
        }""".formatted(cpf, name));

  }

  @Override
  public String toString() {
    return """
        {
            cpf: %s,
            name: %s
        }""".formatted(cpf, name);
  }
}

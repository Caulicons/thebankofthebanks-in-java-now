// InputHandler.java
package com.caulicons.utils;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class InputHandler implements AutoCloseable {
  private final Scanner scanner;

  public InputHandler() {
    this.scanner = new Scanner(System.in);
  }

  public int readInt(String prompt) {
    System.out.print(prompt);
    return scanner.nextInt();
  }

  public String readString(String prompt) {
    System.out.print(prompt);
    return scanner.next();
  }

  public float readFloat(String prompt) {
    System.out.print(prompt);
    return scanner.nextFloat();
  }

  public UUID readUUID(String prompt) {
    try {
      return UUID.fromString(readString(prompt));
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID. Please, try again.");
      return readUUID(prompt);
    }
  }

  public LocalDate readDate(String prompt) {
    return LocalDate.parse(readString(prompt));
  }

  @Override
  public void close() {
    scanner.close();
  }
}
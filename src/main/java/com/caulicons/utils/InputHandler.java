package com.caulicons.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

import com.caulicons.interfaces.InputValidator;

public class InputHandler implements AutoCloseable {
  private final Scanner scanner;

  public InputHandler() {
    this.scanner = new Scanner(System.in);
  }

  public int readInt(String prompt) {
    try {
      System.out.print(prompt);
      int value = scanner.nextInt();
      scanner.nextLine();
      return value;
    } catch (InputMismatchException e) {
      System.out.println("Invalid integer. Please try again.");
      scanner.nextLine();
      return readInt(prompt);
    }
  }

  public String readString(String prompt) {
    try {
      System.out.print(prompt);
      String value = scanner.nextLine().trim();
      if (value.isEmpty()) {
        System.out.println("Input cannot be empty. Please try again.");
        return readString(prompt);
      }
      return value;
    } catch (Exception e) {
      System.out.println("Invalid input. Please try again.");
      return readString(prompt);
    }
  }

  public float readFloat(String prompt) {
    try {
      System.out.print(prompt);
      float value = scanner.nextFloat();
      scanner.nextLine(); // Clear buffer
      if (value < 0) {
        System.out.println("Value must be positive. Please try again.");
        return readFloat(prompt);
      }
      return value;
    } catch (InputMismatchException e) {
      System.out.println("Invalid decimal number. Please try again.");
      scanner.nextLine(); // Clear buffer
      return readFloat(prompt);
    }
  }

  public UUID readUUID(String prompt) {
    try {
      String input = readString(prompt);
      return UUID.fromString(input);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID format. Please try again.");
      return readUUID(prompt);
    }
  }

  public LocalDate readDate(String prompt) {
    try {
      String input = readString(prompt);
      return LocalDate.parse(input);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid date format. Use yyyy-MM-dd. Please try again.");
      return readDate(prompt);
    }
  }

  public String readCPF(String prompt) {

    return readWithValidation(
        prompt,
        new InputValidator<String>() {

          public String get(String prompt) {
            return readString(prompt);
          }

          @Override
          public boolean isValid(String value) {
            return value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
          }

          @Override
          public String getErrorMessage() {
            return "Invalid CPF format. Use XXX.XXX.XXX-XX";
          }
        });
  }

  public <T> String readWithValidation(String prompt, InputValidator<T> validator) {
    while (true) {
      try {
        T value = validator.get(prompt);
        if (validator.isValid(value)) {
          return (String) value;
        }
        System.out.println(validator.getErrorMessage());
      } catch (Exception e) {
        System.out.println("Invalid input: " + e.getMessage());
      }
    }
  }

  @Override
  public void close() {
    scanner.close();
  }
}

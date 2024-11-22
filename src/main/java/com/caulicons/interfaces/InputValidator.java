package com.caulicons.interfaces;

@FunctionalInterface
public interface InputValidator<T> {
  T get(String prompt);

  default boolean isValid(T value) {
    return true;
  }

  default String getErrorMessage() {
    return "Invalid input";
  }
}
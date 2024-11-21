package com.caulicons.types;

public enum TypeAccount {
  SAVE {
    @Override
    public String toString() {
      return "Savings";
    }
  },
  CURRENT {
    @Override
    public String toString() {
      return "Current";
    }
  }
}

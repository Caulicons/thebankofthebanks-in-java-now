package com.caulicons.utils;

import java.io.IOException;

public class Utils {

  private Utils() {
  }

  public static void keyPress() {
    try {
      System.out.println("\n\nPress any key to continue...");
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

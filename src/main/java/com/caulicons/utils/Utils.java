package com.caulicons.utils;

import java.io.IOException;

import com.caulicons.controllers.ClientMenu;

public class Utils {

  private Utils() {
  }

  public static void bootstrap() {
    ClientMenu menu = new ClientMenu();

    menu.start();
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

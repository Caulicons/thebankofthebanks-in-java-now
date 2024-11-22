package com.caulicons.interfaces;

import java.util.Optional;

public interface MenuI<O, E> {

  void printMenu();

  int getOption();

  void handleOption(O options);

  void register();

  void listAll();

  void update();

  void delete();

  void exit();

  Optional<E> getOne();
}

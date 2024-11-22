package com.caulicons.interfaces;

import java.util.Optional;

public interface RepositoryI<T, I> {

  void register(T t);

  void update(T t);

  void remove(I id);

  void listAll();

  Optional<T> findById(I id);
}

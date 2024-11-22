package com.caulicons.repositories;

import java.util.Optional;

public interface Repository<T, I> {

  void register(T t);

  void update(T t);

  void remove(I id);

  void listAll();

  Optional<T> findById(I id);
}

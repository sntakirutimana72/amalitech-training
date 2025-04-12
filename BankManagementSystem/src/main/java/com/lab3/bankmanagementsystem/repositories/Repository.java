package com.lab3.bankmanagementsystem.repositories;

public interface Repository<T, U> {
  void add(T entity);
  void remove(U uid);
  T getUnique(U uid);
}

package com.lab3.bankmanagementsystem.util.errors;

public class IllegalAmountException extends IllegalArgumentException {
  public IllegalAmountException(String message) {
    super(message + " amount must be positive");
  }
}

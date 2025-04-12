package com.lab3.bankmanagementsystem.util.errors;

public class InsufficientFundsException extends IllegalArgumentException {
  public InsufficientFundsException(String message) {
    super(message);
  }

  public InsufficientFundsException() {
    this("Insufficient funds to perform the operation");
  }
}

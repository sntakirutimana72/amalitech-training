package com.lab3.bankmanagementsystem.models;

import java.util.List;

public interface Account {
  void deposit(double amount, String description);
  void withdraw(double amount, String description);
  void withdraw(double amount);

  // Get account holder name
  String getAccountHolder();

  // Get account number
  String getAccountNumber();

  // Balance inquiry
  double getBalance();

  AcccountType getAcccountType();

  // Create a transaction history
  void addTransaction(Transaction transaction);

  // Query transaction history
  List<Transaction> getTransactionHistory();

  // Query some recent transaction history
  List<Transaction> getLastNTransactions(int number);
}

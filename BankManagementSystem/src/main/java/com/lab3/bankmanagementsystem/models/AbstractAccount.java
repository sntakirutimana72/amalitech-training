package com.lab3.bankmanagementsystem.models;

import com.lab3.bankmanagementsystem.util.UIDGenerator;
import com.lab3.bankmanagementsystem.util.errors.IllegalAmountException;

public abstract class AbstractAccount implements Account {
  private final String accountNumber;
  private final String accountHolder;
  private final AcccountType acccountType;
  private double balance;
  private final TransactionHistory transactionHistory;

  public AbstractAccount(String accountHolder, AcccountType acccountType, double initialBalance) {
    this.accountNumber = UIDGenerator.getUID();
    this.accountHolder = accountHolder;
    this.acccountType = acccountType;
    this.balance = initialBalance;
    this.transactionHistory = new TransactionHistory();
  }

  protected final void updateBalance(double amount) {
    balance += amount;
  }

  @Override
  public double getBalance() {
    return balance;
  }

  @Override
  public void deposit(double amount, String description) {
    if (amount <= 0)
      throw new IllegalAmountException("Deposit");
    updateBalance(amount);
    addTransaction(new Transaction("DEPOSIT", amount, description));
  }

  @Override
  public final void withdraw(double amount, String description) {
    updateBalance(-amount);
    addTransaction(new Transaction("WITHDRAWAL", amount, description));
  }

  @Override
  public String getAccountNumber() {
    return accountNumber;
  }

  @Override
  public String getAccountHolder() {
    return accountHolder;
  }

  @Override
  public AcccountType getAcccountType() {
    return acccountType;
  }

  @Override
  public void addTransaction(Transaction transaction) {
    transactionHistory.add(transaction);
  }

  @Override
  public Transaction[] getTransactionHistory() {
    return getLastNTransactions(transactionHistory.getSize());
  }

  @Override
  public Transaction[] getLastNTransactions(int num) {
    return transactionHistory.getMany(num);
  }

  @Override
  public String toString() {
    return
      "Account Number: " + getAccountNumber() + "\n" +
      "Account Holder: " + getAccountHolder() + "\n" +
      "Current Balance: $" + getBalance() + "\n";
  }
}

package com.lab3.bankmanagementsystem.models;

import com.lab3.bankmanagementsystem.util.errors.IllegalAmountException;
import com.lab3.bankmanagementsystem.util.errors.InsufficientFundsException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SavingsAccount extends AbstractAccount {
  private static final double MINIMUM_BALANCE = 100.0;
  private static final double INTEREST_RATE = 0.03;  // annual interest
  private LocalDate lastInterestApplied;

  public SavingsAccount(String accountHolder, double initialBalance) {
    super(accountHolder, AcccountType.SAVINGS, initialBalance);

    lastInterestApplied = LocalDate.now();
  }

  private void applyInterest() {
    LocalDate now = LocalDate.now();
    long daysPassed = ChronoUnit.DAYS.between(lastInterestApplied, now);

    if (daysPassed > 0) {
      double dailyRate = INTEREST_RATE / 365;
      double interest = balance * dailyRate * daysPassed;
      balance += interest;
      lastInterestApplied = now;
    }
  }

  @Override
  public double getBalance() {
    applyInterest();
    return super.getBalance();
  }

  @Override
  public void deposit(double amount, String description) {
    applyInterest();
    super.deposit(amount, description);
  }

  @Override
  public void withdraw(double amount) {
    if (amount <= 0)
      throw new IllegalAmountException("Withdrawal");
    if (getBalance() - amount < MINIMUM_BALANCE)
      throw new InsufficientFundsException("Cannot withdraw. Minimum balance requirement not met.");
    super.withdraw(amount, "Withdrawal from savings account");
  }
}

package com.lab3.bankmanagementsystem.models;

import com.lab3.bankmanagementsystem.util.errors.IllegalAmountException;
import com.lab3.bankmanagementsystem.util.errors.InsufficientFundsException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FixedDepositAccount extends AbstractAccount {
  private static final double INTEREST_RATE = 0.05; // annual interest rate
  private LocalDate lastInterestApplied;
  private LocalDate maturityDate;

  public FixedDepositAccount(String accountHolder, double initialBalance, int months) {
    super(accountHolder, AcccountType.FIXED_DEPOSIT, initialBalance);
    lastInterestApplied = LocalDate.now();
    setMaturityDate(months);
  }

  private void setMaturityDate(int period) {
    maturityDate = lastInterestApplied.plusMonths(period);
  }

  private boolean cannotTransact() {
    return LocalDate.now().isBefore(getMaturityDate());
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
    throw new IllegalArgumentException("Please provide maturity period");
  }

  public void deposit(double amount, int maturity, String description) {
    if (cannotTransact())
      throw new IllegalArgumentException("Cannot deposit before " + getMaturityDate());

    applyInterest();
    super.deposit(amount, description);
    setMaturityDate(maturity);
  }

  @Override
  public void withdraw(double amount) {
    if (cannotTransact())
      throw new IllegalArgumentException("Cannot withdraw before maturity date");
    if (amount <= 0)
      throw new IllegalAmountException("Withdrawal");
    if (getBalance() < amount)
      throw new InsufficientFundsException();
    super.withdraw(amount, "Withdrawal from fixed deposit account");
  }

  public LocalDate getMaturityDate() {
    return maturityDate;
  }
}

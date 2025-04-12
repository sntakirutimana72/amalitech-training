package com.lab3.bankmanagementsystem.models;

import com.lab3.bankmanagementsystem.util.errors.IllegalAmountException;
import com.lab3.bankmanagementsystem.util.errors.InsufficientFundsException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FixedDepositAccount extends AbstractAccount {
  private static final double INTEREST_RATE = 0.05;
  private boolean hasAccumulatedInterest;
  private LocalDate depositDate;
  private LocalDate maturityDate;

  public FixedDepositAccount(String accountHolder, double initialBalance, int months) {
    super(accountHolder, AcccountType.FIXED_DEPOSIT, initialBalance);
    setMaturity(months);
  }

  private void setMaturity(int period) {
    setHasAccumulatedInterest(true);

    depositDate = LocalDate.now();
    maturityDate = depositDate.plusMonths(period);
  }

  private boolean cannotTransact() {
    return LocalDate.now().isBefore(getMaturityDate());
  }

  private void setHasAccumulatedInterest(boolean state) {
    hasAccumulatedInterest = state;
  }

  private void consumeInterest() {
    if (hasAccumulatedInterest) {
      updateBalance(calculateInterest());
      setHasAccumulatedInterest(false);
    }
  }

  @Override
  public void deposit(double amount, String description) {
    throw new IllegalArgumentException("Please provide maturity period");
  }

  public void deposit(double amount, int maturity, String description) {
    if (cannotTransact())
      throw new IllegalArgumentException("Cannot deposit before " + getMaturityDate());

    consumeInterest();
    super.deposit(amount, description);
    setMaturity(maturity);
  }

  @Override
  public void withdraw(double amount) throws InsufficientFundsException {
    if (cannotTransact())
      throw new IllegalArgumentException("Cannot withdraw before maturity date");
    if (amount <= 0)
      throw new IllegalAmountException("Withdrawal");

    consumeInterest();

    if (amount > getBalance())
      throw new InsufficientFundsException();
    super.withdraw(amount, "Withdrawal from fixed deposit account");
  }

  public double calculateInterest() {
    long months = ChronoUnit.MONTHS.between(getDepositDate(), LocalDate.now());
    return getBalance() * INTEREST_RATE * (months / 12.0);
  }

  public LocalDate getMaturityDate() {
    return maturityDate;
  }

  public LocalDate getDepositDate() {
    return depositDate;
  }
}

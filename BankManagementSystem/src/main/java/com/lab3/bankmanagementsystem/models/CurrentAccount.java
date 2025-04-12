package com.lab3.bankmanagementsystem.models;

import com.lab3.bankmanagementsystem.util.errors.IllegalAmountException;
import com.lab3.bankmanagementsystem.util.errors.InsufficientFundsException;

public class CurrentAccount extends AbstractAccount {
  private static final double OVERDRAFT_LIMIT = 1000.0;

  public CurrentAccount(String accountHolder, double initialBalance) {
    super(accountHolder, AcccountType.CURRENT, initialBalance);
  }

  @Override
  public void withdraw(double amount) throws InsufficientFundsException {
    if (amount <= 0)
      throw new IllegalAmountException("Withdrawal");
    if ((getBalance() - amount) < -OVERDRAFT_LIMIT)
      throw new InsufficientFundsException("Cannot withdraw. Overdraft limit exceeded.");
    super.withdraw(amount, "Withdrawal from current account");
  }
}

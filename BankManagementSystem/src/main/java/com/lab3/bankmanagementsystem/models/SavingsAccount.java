package com.lab3.bankmanagementsystem.models;

import com.lab3.bankmanagementsystem.util.errors.IllegalAmountException;
import com.lab3.bankmanagementsystem.util.errors.InsufficientFundsException;

public class SavingsAccount extends AbstractAccount {
    private static final double MINIMUM_BALANCE = 100.0;
    private static final double INTEREST_RATE = 0.02;

    public SavingsAccount(String accountHolder, double initialBalance) {
      super(accountHolder, AcccountType.SAVINGS, initialBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
      if (amount <= 0)
        throw new IllegalAmountException("Withdrawal");
      if (getBalance() - amount < MINIMUM_BALANCE)
        throw new InsufficientFundsException("Cannot withdraw. Minimum balance requirement not met.");
      super.withdraw(amount, "Withdrawal from savings account");
    }

    public double calculateInterest() {
      return getBalance() * INTEREST_RATE;
    }
}

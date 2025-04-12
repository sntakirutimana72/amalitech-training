package com.lab3.bankmanagementsystem.repositories;

import com.lab3.bankmanagementsystem.models.Account;
import com.lab3.bankmanagementsystem.util.errors.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository implements Repository<Account, String> {
  private final Map<String, Account> accounts;

  public AccountRepository() {
    this.accounts = new HashMap<>();
  }

  @Override
  public void add(Account account) {
    accounts.put(account.getAccountNumber(), account);
    System.out.println(account);
  }

  @Override
  public Account getUnique(String accountNumber) throws NotFoundException {
    Account entity = accounts.get(accountNumber);
    if (entity == null)
      throw new NotFoundException("No account found");
    return entity;
  }

  @Override
  public void remove(String accountNumber) {
    accounts.remove(accountNumber);
  }
}

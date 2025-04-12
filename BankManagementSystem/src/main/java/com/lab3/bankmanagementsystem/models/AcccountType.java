package com.lab3.bankmanagementsystem.models;

public enum AcccountType {
  SAVINGS("Savings"),
  CURRENT("Current"),
  FIXED_DEPOSIT("FixedDeposit");

  private final String label;

  AcccountType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public String toString() {
    return label;
  }
}

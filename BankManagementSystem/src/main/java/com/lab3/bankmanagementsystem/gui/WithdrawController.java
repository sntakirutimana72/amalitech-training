package com.lab3.bankmanagementsystem.gui;

import com.lab3.bankmanagementsystem.models.Account;
import com.lab3.bankmanagementsystem.util.Alerts;
import com.lab3.bankmanagementsystem.util.Parsers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WithdrawController extends PageController {
  @FXML private TextField accountNumberField;
  @FXML private TextField amountField;

  private void reset() {
    accountNumberField.setText(null);
    amountField.setText(null);
  }

  @FXML
  private void onWithdraw() {
    try {
      // Query amount
      double amount = Parsers.getFloat(amountField.getText(), "Amount must be > 0");
      // Get account
      Account entity = getApp().getAccountsRepository().getUnique(accountNumberField.getText());
      // Perform withdraw
      entity.withdraw(amount);
      // Display transaction confirmation
      Alerts.confirmation(
        "Withdrawal Process - SUCCESS", null, "Balance: " + entity.getBalance());
      reset();
    } catch (Exception e) {
      Alerts.error("Withdrawal Process - FAILED", null, e.getMessage());
    }
  }
}

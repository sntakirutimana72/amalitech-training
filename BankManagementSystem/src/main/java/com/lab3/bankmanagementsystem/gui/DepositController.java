package com.lab3.bankmanagementsystem.gui;

import com.lab3.bankmanagementsystem.models.AcccountType;
import com.lab3.bankmanagementsystem.models.Account;
import com.lab3.bankmanagementsystem.models.FixedDepositAccount;
import com.lab3.bankmanagementsystem.util.Alerts;
import com.lab3.bankmanagementsystem.util.Parsers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Objects;

public class DepositController extends PageController {
  @FXML
  private Button submitTrigger;

  @FXML
  private TextField accountNumber;

  @FXML
  private TextField amount;

  private void setSubmitTriggerText(String text) {
    submitTrigger.setText(text);
  }

  private void reset() {
    accountNumber.setText(null);
    amount.setText(null);
    setSubmitTriggerText("Process Transaction");
  }

  @FXML
  private void onDeposit() {
    try {
      setSubmitTriggerText("Processing..");
      // Query & parse :accountNumber
      double depositAmount = Parsers.getFloat(amount.getText(), "Amount should be > 0");
      Account account = getApp().getAccountsRepository().getUnique(accountNumber.getText());
      // Check type of the account
      if (account.getAcccountType() == AcccountType.FIXED_DEPOSIT) {// Request for maturity period from user
        ((FixedDepositAccount) account).deposit(depositAmount, 6, "Deposit");
      } else {
        account.deposit(depositAmount, "Deposit");
      }
      Alerts.confirmation(
        "Deposit Success!!", null, "New balance: $" + account.getBalance());
      reset();
    } catch (Exception e) {
      Alerts.error("Deposit Failed", null, e.getMessage());
      setSubmitTriggerText("Process Transaction");
    }
  }
}

package com.lab3.bankmanagementsystem.gui;

import com.lab3.bankmanagementsystem.models.Account;
import com.lab3.bankmanagementsystem.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BalanceInquiryController extends PageController {
  @FXML private TextField accountNumber;

  @FXML
  private void onBalanceCheck() {
    try {
      Account entity = getApp().getAccountsRepository().getUnique(accountNumber.getText());

      Alerts.confirmation(
        "Balance Inquiry", null, "Balance: " + entity.getBalance());
    } catch (Exception e) {
      Alerts.error("Balance Inquiry - ERROR", null, e.getMessage());
    }
  }
}

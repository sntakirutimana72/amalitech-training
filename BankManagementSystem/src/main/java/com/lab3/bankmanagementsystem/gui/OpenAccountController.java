package com.lab3.bankmanagementsystem.gui;

import com.lab3.bankmanagementsystem.models.*;
import com.lab3.bankmanagementsystem.util.Alerts;
import com.lab3.bankmanagementsystem.util.Parsers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.Period;

public class OpenAccountController extends PageController {
  @FXML
  private ChoiceBox<AcccountType> accountType;

  @FXML
  private TextField accountName;

  @FXML
  private TextField initialBalance;

  @FXML
  private DatePicker maturityDate;

  @FXML
  private HBox maturityDateField;

  @FXML
  private Button submitTrigger;

  private void setSubmitTriggerText(String text) {
    submitTrigger.setText(text);
  }

  private void reset() {
    accountName.setText(null);
    accountType.setValue(null);
    initialBalance.setText(null);
    maturityDate.setValue(null);
    setSubmitTriggerText("Create Account");
  }

  @FXML
  private void onCreateAccount() {
    try {
      setSubmitTriggerText("Processing...");
      // Query & parse :accountName
      String name = accountName.getText().trim();
      if (!name.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$"))
        throw new IllegalArgumentException("Account Name is invalid");

      // Query & parse :accountType
      AcccountType type = accountType.getValue();
      if (type == null)
        throw new IllegalArgumentException("Please select account type");

      // Query & parse :initialBalance
      double balance = Parsers.getFloat(
        initialBalance.getText(),
        "Balance amount should be >= 100"
      );

      Account account;
      switch (type) {
        case SAVINGS:
          account = new SavingsAccount(name, balance);
          break;
        case CURRENT:
          account = new CurrentAccount(name, balance);
          break;
        default:
          // Query & parse maturity date
          LocalDate maturity = Parsers.getDate(maturityDate, "Please set maturity date");
          // Should be at least 6 months
          int months = Period.between(LocalDate.now(), maturity).getMonths();
          if (months < 6)
            throw new IllegalArgumentException("Maturity period must be at least 6 months");
          account = new FixedDepositAccount(name, balance, months);
      }
      // Finalize operation
      getApp().getAccountsRepository().add(account);
      Alerts.confirmation("Account Created", null, account.toString());
      reset();
    } catch (Exception e) {
      Alerts.error("Error occurred", null, e.getMessage());
      setSubmitTriggerText("Create Account");
    }
  }

  private void toggleMaturityDateField(boolean visibility) {
    maturityDateField.setVisible(visibility);
    maturityDateField.setManaged(visibility);
  }

  public void initialize() {
    toggleMaturityDateField(false);
    accountType.getItems().setAll(AcccountType.values());
    accountType.getSelectionModel().selectedItemProperty().addListener(
      (listener, previous, current) -> {
        toggleMaturityDateField(current != null && current.equals(AcccountType.FIXED_DEPOSIT));
    });
  }
}

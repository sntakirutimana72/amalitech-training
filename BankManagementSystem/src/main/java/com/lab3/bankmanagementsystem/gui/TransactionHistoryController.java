package com.lab3.bankmanagementsystem.gui;

import com.lab3.bankmanagementsystem.models.Account;
import com.lab3.bankmanagementsystem.models.Transaction;
import com.lab3.bankmanagementsystem.util.Alerts;
import com.lab3.bankmanagementsystem.util.Parsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class TransactionHistoryController extends PageController {
  @FXML private TextField accountNumberField;
  @FXML private TextField numberField;
  @FXML private TableView<Transaction> historyTable;

  @FXML
  private void onQueryHistory() {
    try {
      // Query account entity
      Account entity = getApp().getAccountsRepository().getUnique(accountNumberField.getText());
      // Query history
      String bulk = numberField.getText();
      int historySize = bulk.isBlank() ? 0 : Parsers.getNumber(bulk, "");
      ObservableList<Transaction> history = historySize == 0 ?
        FXCollections.observableArrayList(entity.getTransactionHistory()) :
        FXCollections.observableArrayList(entity.getLastNTransactions(historySize));
      // Populate history
      historyTable.setItems(history);
    } catch (Exception e) {
      Alerts.error("Transactions", null, e.getMessage());
    }
  }
}

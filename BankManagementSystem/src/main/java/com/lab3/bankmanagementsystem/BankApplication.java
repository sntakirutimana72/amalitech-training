package com.lab3.bankmanagementsystem;

import com.lab3.bankmanagementsystem.gui.Controller;
import com.lab3.bankmanagementsystem.repositories.AccountRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BankApplication extends Application {
  private AccountRepository accounts;

  private void onStartup() {
    configureRepositories();
  }

  private void configureRepositories() {
    accounts = new AccountRepository();
  }

  public AccountRepository getAccountsRepository() {
    return accounts;
  }

  @Override
  public void start(Stage stage) throws IOException {
    onStartup();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
    Parent root = loader.load();
    Controller rootController = loader.getController();
    Scene scene = new Scene(root);

    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
    rootController.setApp(this);
    stage.setTitle("Bank Account Management System");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
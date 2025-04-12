package com.lab3.bankmanagementsystem.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardController extends Controller {
  @FXML
  private BorderPane mainLayout;

  @FXML
  private void switchToHome() {
    switchTo("home");
  }

  @FXML
  private void switchToCheckBalance() {
    switchTo("check-balance");
  }

  @FXML
  private void switchToOpenAccount() {
    switchTo("open-account");
  }

  @FXML
  private void switchToDeposit() {
    switchTo("deposit");
  }

  @FXML
  private void switchToWithdraw() {
    switchTo("withdraw");
  }

  private FXMLLoader getLoader(String view) {
    return new FXMLLoader(getClass().getResource(
      "/com/lab3/bankmanagementsystem/" +
        view +
        ".fxml"
    ));
  }

  private void switchTo(String view) {
    try {
      FXMLLoader loader = getLoader(view);
      Parent page = loader.load();
      PageController controller = loader.getController();

      controller.setApp(getApp());
      controller.setMainLayout(mainLayout);
      mainLayout.setCenter(page);
    } catch (IOException ignored) {}
  }

  @FXML
  private void initialize() {
    switchToHome();
  }
}
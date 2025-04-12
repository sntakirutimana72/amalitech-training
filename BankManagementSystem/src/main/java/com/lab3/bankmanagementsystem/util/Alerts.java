package com.lab3.bankmanagementsystem.util;

import javafx.scene.control.Alert;

public class Alerts {
  private static void display(Alert.AlertType level, String title, String header, String message) {
    Alert alert = new Alert(level);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public static void error(String title, String header, String message) {
    display(Alert.AlertType.ERROR, title, header, message);
  }

  public static void confirmation(String title, String header, String message) {
    display(Alert.AlertType.CONFIRMATION, title, header, message);
  }
}

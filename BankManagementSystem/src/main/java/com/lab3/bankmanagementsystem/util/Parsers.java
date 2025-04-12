package com.lab3.bankmanagementsystem.util;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Parsers {
  public static int getNumber(String val, String error) throws NumberFormatException {
    try {
      return Integer.parseInt(val);
    } catch (NumberFormatException e) {
      throw new NumberFormatException(error);
    }
  }

  public static double getFloat(String val, String error) throws NumberFormatException {
    try {
      return Double.parseDouble(val);
    } catch (NumberFormatException e) {
      throw new NumberFormatException(error);
    }
  }

  public static LocalDate getDate(DatePicker picker, String error) throws IllegalArgumentException {
    LocalDate date = picker.getValue();
    if (date == null)
      throw new IllegalArgumentException(error);
    return date;
  }
}

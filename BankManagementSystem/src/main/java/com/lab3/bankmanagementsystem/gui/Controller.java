package com.lab3.bankmanagementsystem.gui;

import com.lab3.bankmanagementsystem.BankApplication;

public abstract class Controller {
  private BankApplication app;

  public void setApp(BankApplication app) {
    this.app = app;
  }

  public BankApplication getApp() {
    return app;
  }
}

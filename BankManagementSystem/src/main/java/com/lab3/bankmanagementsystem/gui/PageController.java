package com.lab3.bankmanagementsystem.gui;

import javafx.scene.layout.BorderPane;

public abstract class PageController extends Controller {
  private BorderPane mainLayout;

  public void setMainLayout(BorderPane layout) {
    mainLayout = layout;
  }

  public BorderPane getMainLayout() {
    return mainLayout;
  }
}

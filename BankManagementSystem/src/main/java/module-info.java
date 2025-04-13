module com.lab3.bankmanagementsystem {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires net.synedra.validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;

  opens com.lab3.bankmanagementsystem to javafx.fxml;
  exports com.lab3.bankmanagementsystem;
  exports com.lab3.bankmanagementsystem.gui;
  opens com.lab3.bankmanagementsystem.gui to javafx.fxml;
  exports com.lab3.bankmanagementsystem.models;
  opens com.lab3.bankmanagementsystem.models to javafx.base;

}
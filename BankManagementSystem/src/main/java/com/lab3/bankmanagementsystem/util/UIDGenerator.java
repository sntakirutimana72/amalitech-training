package com.lab3.bankmanagementsystem.util;

import java.util.UUID;

public class UIDGenerator {
  public static String getUID() {
    return UUID.randomUUID().toString();
  }
}

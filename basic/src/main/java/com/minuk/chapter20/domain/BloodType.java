package com.minuk.chapter20.domain;

public enum BloodType {
  A, AB, B, O;

  public static BloodType fromString(String type) {
    return switch (type.toUpperCase()) {
      case "A" -> A;
      case "AB" -> AB;
      case "B" -> B;
      case "O" -> O;
      default -> throw new IllegalArgumentException("Invalid BloodType: " + type);
    };
  }
}

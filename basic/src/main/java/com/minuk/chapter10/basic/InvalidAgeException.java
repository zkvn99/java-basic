package com.minuk.chapter10.basic;

public class InvalidAgeException extends RuntimeException {

  public InvalidAgeException(String message) {
    super(message);
  }

  public InvalidAgeException(String message, Throwable cause) {
    super(message, cause);
  }
}

package com.minuk.chapter20.domain.exception;

public class MissingRequiredValueException extends RuntimeException {

  public MissingRequiredValueException(String fieldName) {
    super("필수 값 누락입니다: " + fieldName);
  }
}

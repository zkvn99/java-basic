package com.minuk.chapter20.domain.exception;

public class WhitespaceNotAllowedException extends RuntimeException {

  public WhitespaceNotAllowedException(String fieldName) {
    super("공백을 포함할 수 없습니다: " + fieldName);
  }
}

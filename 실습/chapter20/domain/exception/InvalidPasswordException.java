package com.minuk.chapter20.domain.exception;

public class InvalidPasswordException extends RuntimeException {
  public InvalidPasswordException(String reason) {
    super("유효하지 않은 패스워드입니다: " + reason);
  }
}

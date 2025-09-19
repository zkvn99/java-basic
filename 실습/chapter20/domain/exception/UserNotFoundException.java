package com.minuk.chapter20.domain.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String id) {
    super("존재하지 않는 회원입니다: " + id);
  }
}

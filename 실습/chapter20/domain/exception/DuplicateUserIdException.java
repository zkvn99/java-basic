package com.minuk.chapter20.domain.exception;

public class DuplicateUserIdException extends RuntimeException {
  public DuplicateUserIdException(String id) {
    super("중복된 회원 아이디입니다: " + id);
  }
}

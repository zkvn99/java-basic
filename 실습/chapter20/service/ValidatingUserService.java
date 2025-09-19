package com.minuk.chapter20.service;

import com.minuk.chapter20.domain.User;
import com.minuk.chapter20.domain.exception.WhitespaceNotAllowedException;
import com.minuk.chapter20.domain.policy.IdPolicy;
import com.minuk.chapter20.domain.policy.InputPolicy;
import com.minuk.chapter20.domain.policy.PasswordPolicy;
import java.util.List;
import java.util.Objects;

public class ValidatingUserService implements UserService {

  private final UserService delegate;      // Core(UserServiceImpl)
  private final IdPolicy idPolicy;         // ID: 영문/숫자만 + trim + 공백금지
  private final PasswordPolicy pwdPolicy;  // PW: 공백금지 + 8자 + 대/소/숫/특
  private final InputPolicy inputPolicy;   // 기타 입력: trim + 공백금지

  public ValidatingUserService(UserService delegate,
      IdPolicy idPolicy,
      PasswordPolicy pwdPolicy,
      InputPolicy inputPolicy) {
    this.delegate = Objects.requireNonNull(delegate);
    this.idPolicy = Objects.requireNonNull(idPolicy);
    this.pwdPolicy = Objects.requireNonNull(pwdPolicy);
    this.inputPolicy = Objects.requireNonNull(inputPolicy);
  }

  // ---------- 조회 ----------
  @Override
  public List<User> findAllUsers() {
    return delegate.findAllUsers();
  }

  @Override
  public User findUserById(String id) {
    idPolicy.validate(id);
    return delegate.findUserById(id);
  }

  // ---------- 등록 ----------
  @Override
  public void registerUser(User user) {
    idPolicy.validate(user.getId());
    pwdPolicy.validate(user.getPassword());
    inputPolicy.trimAndEnsureNoWhitespace(user.getName(), "name");

    delegate.registerUser(user);
  }

  // ---------- 수정 ----------
  @Override
  public void modifyUser(User user) {
    idPolicy.validate(user.getId());
    pwdPolicy.validate(user.getPassword());
    inputPolicy.trimAndEnsureNoWhitespace(user.getName(), "name");

    delegate.modifyUser(user);
  }

  // ---------- 삭제 ----------
  @Override
  public void deleteUserById(String id) {
    idPolicy.validate(id);

    delegate.deleteUserById(id);
  }

  // ---------- 유틸 ----------
  @Override
  public boolean isDuplicateId(String id) {
    idPolicy.validate(id);
    return delegate.isDuplicateId(id);
  }

  // ---------- 로그인 ----------
  @Override
  public User login(String id, String password) {
    idPolicy.validate(id);

    if (password == null || password.chars().anyMatch(Character::isWhitespace)) {
      throw new WhitespaceNotAllowedException("password");
    }

    return delegate.login(id, password);
  }

  @Override
  public boolean isAdmin(User user) {
    return delegate.isAdmin(user);
  }

  @Override
  public void modifyRole(User user) {
    delegate.modifyRole(user);
  }
}

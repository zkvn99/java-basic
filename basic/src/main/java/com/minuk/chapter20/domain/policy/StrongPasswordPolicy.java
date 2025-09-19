package com.minuk.chapter20.domain.policy;

import com.minuk.chapter20.domain.exception.InvalidPasswordException;
import com.minuk.chapter20.domain.exception.MissingRequiredValueException;
import java.util.regex.Pattern;

public class StrongPasswordPolicy implements PasswordPolicy {
  private static final Pattern UPPER   = Pattern.compile("[A-Z]");
  private static final Pattern LOWER   = Pattern.compile("[a-z]");
  private static final Pattern DIGIT   = Pattern.compile("\\d");
  private static final Pattern SPECIAL = Pattern.compile("[^A-Za-z0-9]"); // 알파벳/숫자 외
  private static final int MIN_LEN = 8;

  @Override
  public void validate(String rawPassword) {
    if (rawPassword == null) throw new MissingRequiredValueException("password");
    if (rawPassword.chars().anyMatch(Character::isWhitespace))
      throw new InvalidPasswordException("비밀번호에 공백을 포함할 수 없습니다.");
    if (rawPassword.length() < MIN_LEN)
      throw new InvalidPasswordException("비밀번호는 최소 " + MIN_LEN + "자 이상이어야 합니다.");
    if (!UPPER.matcher(rawPassword).find())
      throw new InvalidPasswordException("대문자가 최소 1자 이상 필요합니다.");
    if (!LOWER.matcher(rawPassword).find())
      throw new InvalidPasswordException("소문자가 최소 1자 이상 필요합니다.");
    if (!DIGIT.matcher(rawPassword).find())
      throw new InvalidPasswordException("숫자가 최소 1자 이상 필요합니다.");
    if (!SPECIAL.matcher(rawPassword).find())
      throw new InvalidPasswordException("특수문자가 최소 1자 이상 필요합니다.");
  }
}

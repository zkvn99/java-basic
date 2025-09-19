package com.minuk.chapter20.domain.policy;

import com.minuk.chapter20.domain.exception.MissingRequiredValueException;
import com.minuk.chapter20.domain.exception.WhitespaceNotAllowedException;

public class NoWhitespacePolicy implements InputPolicy{

  @Override
  public void trimAndEnsureNoWhitespace(String input, String fieldName) {
    if (input == null) throw new MissingRequiredValueException(fieldName);
    String trimmed = input.trim();
    if (trimmed.isEmpty()) throw new MissingRequiredValueException(fieldName);
    if (trimmed.chars().anyMatch(Character::isWhitespace)) {
      throw new WhitespaceNotAllowedException(fieldName);
    }
  }
}

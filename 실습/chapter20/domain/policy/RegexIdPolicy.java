package com.minuk.chapter20.domain.policy;

import com.minuk.chapter20.domain.exception.MissingRequiredValueException;
import com.minuk.chapter20.domain.exception.WhitespaceNotAllowedException;
import java.util.regex.Pattern;

public class RegexIdPolicy implements IdPolicy {

  private static final Pattern AlphabetAndNumber = Pattern.compile("^[A-Za-z0-9]+$"); // 영문/숫자만

  @Override
  public void validate(String id) {
    if (id == null) throw new MissingRequiredValueException("id");
    String trimmed = id.trim();
    if (trimmed.isEmpty()) throw new MissingRequiredValueException("id");
    if (!AlphabetAndNumber.matcher(trimmed).matches()) {
      throw new WhitespaceNotAllowedException("id");
    }
  }
}

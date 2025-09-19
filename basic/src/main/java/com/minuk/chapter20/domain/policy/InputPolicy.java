package com.minuk.chapter20.domain.policy;

public interface InputPolicy {
  void trimAndEnsureNoWhitespace(String input, String fieldName);
}

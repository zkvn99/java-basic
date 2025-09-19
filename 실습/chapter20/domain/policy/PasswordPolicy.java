package com.minuk.chapter20.domain.policy;

public interface PasswordPolicy {
   void validate(String rawPassword);
}

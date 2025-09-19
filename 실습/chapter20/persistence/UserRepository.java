package com.minuk.chapter20.persistence;

import com.minuk.chapter20.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
  List<User> findAll();
  Optional<User> findById(String id);
  boolean existsById(String id);
  void saveUser(User user);
  void deleteById(String id);
}

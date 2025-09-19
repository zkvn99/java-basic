package com.minuk.chapter20.service;

import com.minuk.chapter20.domain.User;
import java.util.List;

public interface UserService {
  List<User> findAllUsers();
  User findUserById(String id);
  void registerUser(User user);
  void modifyUser(User user);
  void deleteUserById(String id);
  boolean isDuplicateId(String id);
  User login(String id, String password);
  boolean isAdmin(User user);
  void modifyRole(User user);
}

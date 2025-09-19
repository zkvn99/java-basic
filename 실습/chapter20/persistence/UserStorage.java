package com.minuk.chapter20.persistence;

import com.minuk.chapter20.domain.User;
import java.util.List;

public interface UserStorage {
  void saveUsers(List<User> users);
  List<User> loadUsers();
}

package com.minuk.chapter20.service;

import com.minuk.chapter20.domain.Role;
import com.minuk.chapter20.domain.User;
import com.minuk.chapter20.domain.exception.DuplicateUserIdException;
import com.minuk.chapter20.domain.exception.InvalidPasswordException;
import com.minuk.chapter20.domain.exception.UserNotFoundException;
import com.minuk.chapter20.domain.policy.IdPolicy;
import com.minuk.chapter20.domain.policy.InputPolicy;
import com.minuk.chapter20.domain.policy.PasswordPolicy;
import com.minuk.chapter20.persistence.UserRepository;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = Objects.requireNonNull(userRepository);
  }

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User findUserById(String id) {
    return userRepository.findById(id)
                         .orElseThrow(() -> new UserNotFoundException(id));
  }

  @Override
  public void registerUser(User user) {

    if (userRepository.existsById(user.getId())) {
      throw new DuplicateUserIdException(user.getId());
    }

    userRepository.saveUser(user);
  }

  @Override
  public void modifyUser(User user) {

    userRepository.findById(user.getId())
                                  .orElseThrow(() -> new UserNotFoundException(user.getId()));

    userRepository.saveUser(user);
  }

  @Override
  public void deleteUserById(String id) {

    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException(id);
    }

    userRepository.deleteById(id);
  }

  @Override
  public boolean isDuplicateId(String id) {
    return userRepository.existsById(id);
  }

  @Override
  public User login(String id, String password) {
    if (id == null || password == null) throw new IllegalArgumentException("ID/PW 필요");
    return userRepository.findAll().stream()
                         .filter(u -> u.getId().equals(id) && u.getPassword().equals(password))
                         .findFirst()
                         .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다."));
  }

  @Override
  public boolean isAdmin(User user) {
    return user.getRole() == Role.ADMIN;
  }

  @Override
  public void modifyRole(User user) {
    user.changeRole(Role.ADMIN);
    userRepository.saveUser(user);
  }
}

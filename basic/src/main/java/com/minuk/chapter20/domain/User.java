package com.minuk.chapter20.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class User implements Serializable {
  private String id;
  private String password;
  private String name;
  private int age;
  private List<String> hobbies;
  private BloodType bloodType;
  private LocalDateTime createdAt;

  private Role role = Role.MEMBER;

  public User(String id, String password, String name, int age, List<String> hobbies, BloodType bloodType) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.age = age;
    this.hobbies = List.copyOf(hobbies == null ? List.of() : hobbies);;
    this.bloodType = bloodType;
    this.createdAt = LocalDateTime.now();
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public int getAge() {
    return age;
  }

  public BloodType getBloodType() {
    return bloodType;
  }

  public List<String> getHobbies() {
    return Collections.unmodifiableList(hobbies);
  }

  public Role getRole() {
    return role;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "User{id='%s', age=%d, name=%s, hobbies=%s, bloodType=%s, role=%s}"
        .formatted(id, age, name, hobbies, bloodType, role);
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void changeAge(int age) {
    this.age = age;
  }

  public void changeHobbies(List<String> hobbies) {
    this.hobbies = hobbies;
  }

  public void changeBloodType(BloodType bloodType) {
    this.bloodType = bloodType;
  }

  public void changeRole(Role role) {
    this.role = role;
  }
}

package com.minuk.chapter20.ui;

import com.minuk.chapter20.domain.BloodType;
import com.minuk.chapter20.domain.User;
import com.minuk.chapter20.domain.policy.IdPolicy;
import com.minuk.chapter20.domain.policy.InputPolicy;
import com.minuk.chapter20.domain.policy.NoWhitespacePolicy;
import com.minuk.chapter20.domain.policy.PasswordPolicy;
import com.minuk.chapter20.domain.policy.RegexIdPolicy;
import com.minuk.chapter20.domain.policy.StrongPasswordPolicy;
import com.minuk.chapter20.persistence.FileUserRepository;
import com.minuk.chapter20.persistence.FileUserStorage;
import com.minuk.chapter20.persistence.UserRepository;
import com.minuk.chapter20.service.UserService;
import com.minuk.chapter20.service.UserServiceImpl;
import com.minuk.chapter20.service.ValidatingUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
  private final UserService userService;
  private final Scanner scanner;
  private User currentUser;

  public Application() {
    UserRepository userRepository = new FileUserRepository(new FileUserStorage());
    IdPolicy idPolicy = new RegexIdPolicy();
    PasswordPolicy passwordPolicy = new StrongPasswordPolicy();
    InputPolicy inputPolicy = new NoWhitespacePolicy();
    UserService core = new UserServiceImpl(userRepository);

    this.userService = new ValidatingUserService(core, idPolicy, passwordPolicy, inputPolicy);
    this.scanner = new Scanner(System.in);
  }

  public void run() {
    while (true) {
      try {
        if (currentUser == null) {
          showMenuGuest();
          int choice = readInt("메뉴 선택: ");
          switch (choice) {
            case 1 -> login();
            case 2 -> registerUser();
            case 9 -> { System.out.println("프로그램 종료"); return; }
            default -> System.out.println("잘못된 입력");
          }
        } else if (userService.isAdmin(currentUser)) {
          showMenuAdmin();
          int choice = readInt("메뉴 선택: ");
          switch (choice) {
            case 1 -> showAllUsers();
            case 2 -> findUserByNo();
            case 3 -> registerUser();
            case 4 -> modifyUserAdmin();
            case 5 -> deleteUserAdmin();
            case 6 -> logout();
            case 9 -> { System.out.println("프로그램 종료"); return; }
            default -> System.out.println("잘못된 입력");
          }
        } else {
          showMenuMember();
          int choice = readInt("메뉴 선택: ");
          switch (choice) {
            case 1 -> showMyInfo();
            case 2 -> modifyMyInfo();
            case 3 -> deleteMyAccount();
            case 4 -> logout();
            case 5 -> modifyRole();
            case 9 -> { System.out.println("프로그램 종료"); return; }
            default -> System.out.println("잘못된 입력");
          }
        }
      } catch (Exception e) {
        System.out.println("오류: " + e.getMessage());
      }
    }
  }

  private void showMenuGuest() {
    System.out.println("\n===== 통합 회원 관리 (게스트) =====");
    System.out.println("1. 로그인");
    System.out.println("2. 회원 가입");
    System.out.println("9. 종료");
  }

  private void showMenuAdmin() {
    System.out.println("\n===== 통합 회원 관리 (관리자) =====");
    System.out.println("1. 모든 회원 조회");
    System.out.println("2. 회원 찾기(번호)");
    System.out.println("3. 회원 가입(관리자 권한으로)");
    System.out.println("4. 회원 정보 수정(관리자)");
    System.out.println("5. 회원 삭제(관리자)");
    System.out.println("6. 로그아웃");
    System.out.println("9. 종료");
  }

  private void showMenuMember() {
    System.out.println("\n===== 통합 회원 관리 (회원) =====");
    System.out.println("1. 내 정보 보기");
    System.out.println("2. 내 정보 수정");
    System.out.println("3. 회원 탈퇴");
    System.out.println("4. 로그아웃");
    System.out.println("5. 관리자 업그레이드");
    System.out.println("9. 종료");
  }

  // ---------- 공통/게스트 기능 ----------

  private void login() {
    String id = readLine("아이디: ");
    String pwd = readLine("비밀번호: ");
    currentUser = userService.login(id, pwd);
    System.out.println("로그인 성공: " + currentUser.getId() + " (" + currentUser.getRole() + ")");
  }

  private void registerUser() {
    String id = readLine("아이디: ");
    String pwd = readLine("비밀번호(8자 이상): ");
    String name = readLine("이름: ");
    int age = readInt("나이: ");
    List<String> hobbies = inputHobbies();
    BloodType bloodType = inputBloodType();

    User newUser = new User(id, pwd, name, age, hobbies, bloodType);

    userService.registerUser(newUser);
    System.out.println("회원 가입 성공: " + id);
  }

  // ---------- 관리자 기능 ----------

  private void showAllUsers() {
    userService.findAllUsers().forEach(System.out::println);
  }

  private void findUserByNo() {
    String id = readLine("조회할 회원 아이디: ");
    User user = userService.findUserById(id);
    if (user != null) System.out.println(user);
    else System.out.println("해당 아이디의 회원 없음");
  }

  private void modifyUserAdmin() {
    String id = readLine("수정할 회원 아이디: ");
    User existing = userService.findUserById(id);
    if (existing == null) { System.out.println("존재하지 않는 회원입니다."); return; }

    String newPassword = readLine("새 비밀번호(Enter=유지): ");
    String newName = readLine("새 이름(Enter=유지): ");
    String newAge = readLine("새 나이(Enter=유지, 현재: %d): ".formatted(existing.getAge()));
    List<String> newHobbies = inputHobbiesOptional();
    BloodType newBloodType = inputBloodTypeOptional(existing.getBloodType());

    User updated = new User(
        id,
        newPassword.isBlank() ? existing.getPassword() : newPassword,
        newName.isBlank() ? existing.getName() : newName,
        newAge.isBlank() ? existing.getAge() : Integer.parseInt(newAge),
        newHobbies == null ? existing.getHobbies() : newHobbies,
        newBloodType == null ? existing.getBloodType() : newBloodType
    );

    userService.modifyUser(updated);
    System.out.println("수정 완료");
  }

  private void deleteUserAdmin() {
    String id = readLine("삭제할 회원 아이디: ");
    userService.deleteUserById(id);
    System.out.println("삭제 완료");
  }

  // ---------- 회원 기능 ----------

  private void showMyInfo() {
    System.out.println(currentUser);
  }

  private void modifyMyInfo() {
    if (currentUser == null) { System.out.println("로그인이 필요합니다."); return; }

    String id = currentUser.getId();
    String newName = readLine("새 이름(Enter=유지): ");
    String newPassword = readLine("새 비밀번호(Enter=유지): ");
    String newAge = readLine("새 나이(Enter=유지, 현재: %d): ".formatted(currentUser.getAge()));
    List<String> newHobbies = inputHobbiesOptional();
    BloodType newBloodType = inputBloodTypeOptional(currentUser.getBloodType());

    User updated = new User(
        id,
        newPassword.isBlank() ? currentUser.getPassword() : newPassword,
        newName.isBlank() ? currentUser.getName() : newName,
        newAge.isBlank() ? currentUser.getAge() : Integer.parseInt(newAge),
        newHobbies == null ? currentUser.getHobbies() : newHobbies,
        newBloodType == null ? currentUser.getBloodType() : newBloodType
    );

    userService.modifyUser(updated);

    currentUser = userService.findUserById(currentUser.getId());
    System.out.println("내 정보 수정 완료");
  }

  private void deleteMyAccount() {
    if (currentUser == null) { System.out.println("로그인이 필요합니다."); return; }
    String confirm = readLine("정말 탈퇴하시겠습니까? (yes): ");
    if (!"yes".equalsIgnoreCase(confirm)) { System.out.println("취소됨"); return; }

    userService.deleteUserById(currentUser.getId());
    System.out.println("탈퇴 완료");
    currentUser = null;
  }

  private void logout() {
    currentUser = null;
    System.out.println("로그아웃 완료");
  }

  private void modifyRole() {
    if (currentUser == null) { System.out.println("로그인이 필요합니다."); return; }
    userService.modifyRole(currentUser);
    currentUser = userService.findUserById(currentUser.getId());
    System.out.println("관리자로 변경 완료");
  }

  // ---------- 입력 유틸 ----------

  private int readInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        String s = scanner.nextLine();
        return Integer.parseInt(s.trim());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private String readLine(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  private List<String> inputHobbies() {
    int count = readInt("취미 개수: ");
    List<String> hobbies = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      hobbies.add(readLine("취미 %d/%d: ".formatted(i + 1, count)));
    }
    return hobbies;
  }

  private List<String> inputHobbiesOptional() {
    String ans = readLine("취미 변경? (y/N): ");
    if (!ans.equalsIgnoreCase("y")) return null;
    return inputHobbies();
  }

  private BloodType inputBloodType() {
    while (true) {
      String s = readLine("혈액형 입력 (A, AB, B, O): ");
      try {
        return BloodType.fromString(s);
      } catch (Exception e) {
        System.out.println("다시 입력하세요.");
      }
    }
  }

  private BloodType inputBloodTypeOptional(BloodType current) {
    String s = readLine("혈액형 변경? (Enter=유지, 현재: %s): ".formatted(current));
    if (s.isBlank()) return null;
    return BloodType.fromString(s);
  }

  public static void main(String[] args) {
    new Application().run();
  }
}
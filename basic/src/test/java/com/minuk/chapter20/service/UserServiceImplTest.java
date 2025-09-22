package com.minuk.chapter20.service;

import com.minuk.chapter20.domain.BloodType;
import com.minuk.chapter20.domain.Role;
import com.minuk.chapter20.domain.User;
import com.minuk.chapter20.domain.exception.DuplicateUserIdException;
import com.minuk.chapter20.domain.exception.UserNotFoundException;
import com.minuk.chapter20.persistence.UserRepository;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplPureTest {

  /* 인메모리 레포지토리 사용
  * 유닛 테스트 실행 전 주입 */
  static class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> store = new LinkedHashMap<>();

    @Override public List<User> findAll() {
      return new ArrayList<>(store.values());
    }
    @Override public Optional<User> findById(String id) {
      return Optional.ofNullable(store.get(id));
    }
    @Override public boolean existsById(String id) {
      return store.containsKey(id);
    }
    @Override public void saveUser(User user) {
      store.put(user.getId(), user); // 존재하면 덮어씀(수정), 없으면 추가
    }
    @Override public void deleteById(String id) {
      store.remove(id);
    }
  }

  private InMemoryUserRepository repo;
  private UserServiceImpl service;

  @BeforeEach
  void setUp() {
    repo = new InMemoryUserRepository();
    service = new UserServiceImpl(repo);
  }

  /* 유저 생성 메소드 */
  private User makeUser(String id) {
    return new User(id, "!Aa123456", "이민욱", 27, List.of("코딩"), BloodType.A);
  }

  @Test
  @DisplayName("저장된 모든 사용자 반환")
  void findAllUsers() {
    repo.saveUser(makeUser("u1"));
    repo.saveUser(makeUser("u2"));

    List<User> all = service.findAllUsers();
    assertEquals(2, all.size());
    assertTrue(all.stream().anyMatch(u -> u.getId().equals("u1")));
    assertTrue(all.stream().anyMatch(u -> u.getId().equals("u2")));
  }

  @Nested
  class FindById {
    @Test @DisplayName("아이디 존재하면 반환")
    void ok() {
      repo.saveUser(makeUser("min"));
      User found = service.findUserById("min");
      assertEquals("min", found.getId());
    }

    @Test @DisplayName("없으면 UserNotFoundException")
    void notFound() {
      assertThrows(UserNotFoundException.class, () -> service.findUserById("nope"));
    }
  }

  @Nested
  class Register {
    @Test @DisplayName("아이디 중복이 아니면 저장")
    void saveNew() {
      User u = makeUser("newId");
      service.registerUser(u);
      assertEquals(u, repo.findById("newId").orElseThrow());
    }

    @Test @DisplayName("중복이면 DuplicateUserIdException")
    void duplicate() {
      repo.saveUser(makeUser("dup"));
      assertThrows(DuplicateUserIdException.class, () -> service.registerUser(makeUser("dup")));
    }
  }

  @Nested
  class Modify {
    @Test @DisplayName("아이디 존재하면 수정 저장")
    void ok() {
      User origin = makeUser("abc");
      repo.saveUser(origin);

      User changed = makeUser("abc");
      changed.changeRole(Role.ADMIN); // 뭐라도 바꿔보기
      service.modifyUser(changed);

      assertSame(changed, repo.findById("abc").orElseThrow());
    }

    @Test @DisplayName("없으면 UserNotFoundException")
    void notFound() {
      assertThrows(UserNotFoundException.class, () -> service.modifyUser(makeUser("missing")));
    }
  }

  @Nested
  class Delete {
    @Test @DisplayName("아이디 존재하면 삭제")
    void ok() {
      repo.saveUser(makeUser("abc"));
      service.deleteUserById("abc");
      assertFalse(repo.existsById("abc"));
    }

    @Test @DisplayName("없으면 UserNotFoundException")
    void notFound() {
      assertThrows(UserNotFoundException.class, () -> service.deleteUserById("missing"));
    }
  }

  @Test
  @DisplayName("아이디 존재 여부 반환")
  void isDuplicate() {
    assertFalse(service.isDuplicateId("who"));
    repo.saveUser(makeUser("who"));
    assertTrue(service.isDuplicateId("who"));
  }

  @Nested
  class Login {
    @Test @DisplayName("아이디/비밀번호 일치하면 사용자 반환")
    void ok() {
      User u = makeUser("min");
      repo.saveUser(u);

      User logged = service.login("min", "!Aa123456");
      assertSame(u, logged);
    }

    @Test @DisplayName("불일치면 IllegalArgumentException")
    void fail() {
      repo.saveUser(makeUser("minuk"));
      assertThrows(IllegalArgumentException.class, () -> service.login("minuk2", "wrong"));
    }

    @Test @DisplayName("ID 또는 PW null이면 IllegalArgumentException")
    void nullArgs() {
      assertThrows(IllegalArgumentException.class, () -> service.login(null, "x"));
      assertThrows(IllegalArgumentException.class, () -> service.login("x", null));
    }
  }

  @Nested
  class RoleOps {
    @Test @DisplayName("ADMIN 여부 반환")
    void isAdmin() {
      User admin = makeUser("admin");
      admin.changeRole(Role.ADMIN);
      assertTrue(service.isAdmin(admin));

      User member = makeUser("member");
      assertFalse(service.isAdmin(member));
    }

    @Test @DisplayName("ADMIN으로 변경하고 저장")
    void modifyRole() {
      User u = makeUser("admin");
      repo.saveUser(u);

      service.modifyRole(u);

      User saved = repo.findById("admin").orElseThrow();
      assertEquals(Role.ADMIN, saved.getRole());
    }
  }
}

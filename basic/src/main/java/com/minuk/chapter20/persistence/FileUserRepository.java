package com.minuk.chapter20.persistence;

import com.minuk.chapter20.domain.User;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileUserRepository implements UserRepository {

  private final UserStorage storage;
  private final Map<String, User> cache = new LinkedHashMap<>();
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

  public FileUserRepository(UserStorage storage) {
    this.storage = Objects.requireNonNull(storage);

    List<User> loaded = storage.loadUsers();
    for (User u : loaded) {
      cache.put(u.getId(), u);
    }
  }

  @Override
  public List<User> findAll() {
    lock.readLock().lock();
    try {
      return new ArrayList<>(cache.values());
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public Optional<User> findById(String id) {
    lock.readLock().lock();
    try {
      return Optional.ofNullable(cache.get(id));
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public boolean existsById(String id) {
    lock.readLock().lock();
    try {
      return cache.containsKey(id);
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public void saveUser(User user) {
    lock.writeLock().lock();
    try {
      cache.put(user.getId(), user);
      flush();
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public void deleteById(String id) {
    lock.writeLock().lock();
    try {
      cache.remove(id);
      flush();
    } finally {
      lock.writeLock().unlock();
    }
  }

  private void flush() {
    storage.saveUsers(new ArrayList<>(cache.values()));
  }
}

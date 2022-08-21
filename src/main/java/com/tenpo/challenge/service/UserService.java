package com.tenpo.challenge.service;

import com.tenpo.challenge.entity.model.User;

import java.util.Optional;

public interface UserService {

  User create(User user);
  Optional<User> findByUsername(String username);
}

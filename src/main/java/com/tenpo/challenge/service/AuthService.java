package com.tenpo.challenge.service;

import com.tenpo.challenge.entity.model.User;

public interface AuthService {

  String login(User user);

  void logout(String token);
}

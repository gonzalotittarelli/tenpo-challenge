package com.tenpo.challenge.service.impl;

import com.tenpo.challenge.adapter.mapper.jpa.UserJpaMapper;
import com.tenpo.challenge.adapter.mapper.model.UserMapper;
import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.entity.model.User;
import com.tenpo.challenge.exception.UserAlreadyExistsException;
import com.tenpo.challenge.repository.UserJpaRepository;
import com.tenpo.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private static final String ERROR_USER_EXISTS = "User %s already exists";
  @Autowired private final UserJpaRepository userJpaRepository;
  @Autowired private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  @Transactional
  public User save(User user) {
    checkUserExistence(user);
    UserJpaEntity userJpaEntity =
        userJpaRepository.save(UserMapper.map(user, bCryptPasswordEncoder));
    return UserJpaMapper.map(userJpaEntity);
  }

  private void checkUserExistence(User user) {
    if (userJpaRepository.existsByUsername(user.getUsername())) {
      String message = String.format(ERROR_USER_EXISTS, user.getUsername());
      log.error(message);
      throw new UserAlreadyExistsException(message);
    }
  }
}

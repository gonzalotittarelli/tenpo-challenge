package com.tenpo.challenge.service.impl;

import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.entity.jpa.UserRole;
import com.tenpo.challenge.entity.model.User;
import com.tenpo.challenge.exception.UserAlreadyExistsException;
import com.tenpo.challenge.repository.UserJpaRepository;
import com.tenpo.challenge.service.UserService;
import com.tenpo.challenge.service.auth.JWTTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private static final String ERROR_USER_EXISTS = "User %s already exists";
  @Autowired private final UserJpaRepository userJpaRepository;
  @Autowired private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired private final JWTTokenServiceImpl jwtTokenService;

  @Override
  @Transactional
  public User save(User user) {
    checkUserExistence(user);
    UserJpaEntity userJpaEntity = userJpaRepository.save(map(user));
    return map(userJpaEntity);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return userJpaRepository.findByUsername(username).map(this::map);
  }

  private void checkUserExistence(User user) {
    if (userJpaRepository.existsByUsername(user.getUsername())) {
      String message = String.format(ERROR_USER_EXISTS, user.getUsername());
      log.error(message);
      throw new UserAlreadyExistsException(message);
    }
  }

  private User map(UserJpaEntity user) {
    return User.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .email(user.getEmail())
        .createdAt(user.getCreatedAt())
        .build();
  }

  private UserJpaEntity map(User user) {
    return UserJpaEntity.builder()
        .username(user.getUsername())
        .password(bCryptPasswordEncoder.encode(user.getPassword()))
        .email(user.getEmail())
        .userRole(UserRole.USER)
        .build();
  }
}

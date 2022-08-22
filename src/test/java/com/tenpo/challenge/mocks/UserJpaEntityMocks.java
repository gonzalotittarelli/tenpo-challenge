package com.tenpo.challenge.mocks;

import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.entity.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserJpaEntityMocks {

  public static UserJpaEntity getDefault() {
    return UserJpaEntity.builder()
        .id(DefaultValues.ID)
        .username(DefaultValues.USERNAME)
        .password(DefaultValues.PASSWORD)
        .email(DefaultValues.EMAIL)
        .createdAt(LocalDateTime.now())
        .userRole(DefaultValues.ROLE)
        .build();
  }

  public static UserJpaEntity getBy(User user) {
    return UserJpaEntity.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .email(user.getEmail())
        .userRole(DefaultValues.ROLE)
        .build();
  }
}

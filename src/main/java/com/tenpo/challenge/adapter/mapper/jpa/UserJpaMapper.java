package com.tenpo.challenge.adapter.mapper.jpa;

import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.entity.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJpaMapper {

  public static User map(UserJpaEntity user) {
    return User.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .email(user.getEmail())
        .createdAt(user.getCreatedAt())
        .build();
  }
}

package com.tenpo.challenge.adapter.mapper.model;

import com.tenpo.challenge.controller.dto.response.UserResponseDTO;
import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.entity.jpa.UserRole;
import com.tenpo.challenge.entity.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

  public static UserResponseDTO map(User user) {
    return UserResponseDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .build();
  }

  public static UserJpaEntity map(User user, PasswordEncoder encoder) {
    return UserJpaEntity.builder()
        .username(user.getUsername())
        .password(encoder.encode(user.getPassword()))
        .email(user.getEmail())
        .userRole(UserRole.USER)
        .build();
  }
}

package com.tenpo.challenge.adapter.mapper.dto;

import com.tenpo.challenge.controller.dto.request.UserLoginRequestDTO;
import com.tenpo.challenge.entity.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginRequestDTOMapper {

  public static User map(UserLoginRequestDTO userLoginRequestDTO) {
    return User.builder()
        .username(userLoginRequestDTO.getUsername())
        .password(userLoginRequestDTO.getPassword())
        .build();
  }
}

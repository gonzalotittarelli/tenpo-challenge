package com.tenpo.challenge.adapter.mapper.dto;

import com.tenpo.challenge.controller.dto.request.UserRequestDTO;
import com.tenpo.challenge.entity.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestDTOMapper {

  public static User map(UserRequestDTO userRequestDTO) {
    return User.builder()
        .username(userRequestDTO.getUsername())
        .password(userRequestDTO.getPassword())
        .email(userRequestDTO.getEmail())
        .build();
  }
}

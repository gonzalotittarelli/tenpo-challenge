package com.tenpo.challenge.mocks;

import com.tenpo.challenge.controller.dto.request.UserRequestDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserRequestDTOMocks {

  public static UserRequestDTO getDefault() {
    return UserRequestDTO.builder()
        .username(DefaultValues.USERNAME)
        .password(DefaultValues.PASSWORD)
        .email(DefaultValues.EMAIL)
        .build();
  }
}

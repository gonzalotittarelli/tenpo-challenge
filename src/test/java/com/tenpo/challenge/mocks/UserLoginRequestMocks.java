package com.tenpo.challenge.mocks;

import com.tenpo.challenge.controller.dto.request.UserLoginRequestDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserLoginRequestMocks {

  public static UserLoginRequestDTO getDefault() {
    return UserLoginRequestDTO.builder()
        .username(DefaultValues.USERNAME)
        .password(DefaultValues.PASSWORD)
        .build();
  }
}

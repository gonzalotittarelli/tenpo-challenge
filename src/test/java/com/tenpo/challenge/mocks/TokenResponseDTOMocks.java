package com.tenpo.challenge.mocks;

import com.tenpo.challenge.controller.dto.response.TokenResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenResponseDTOMocks {

  public static TokenResponseDTO getDefault() {
    return TokenResponseDTO.builder().accessToken(DefaultValues.TOKEN).build();
  }
}

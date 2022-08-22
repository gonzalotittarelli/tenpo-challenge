package com.tenpo.challenge.mocks;

import com.tenpo.challenge.controller.dto.response.TransactionHistoryResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionHistoryResponseMocks {

  public static TransactionHistoryResponseDTO getDefault() {
    return TransactionHistoryResponseDTO.builder()
        .endpoint(DefaultValues.ENDPOINT)
        .status(DefaultValues.STATUS)
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static List<TransactionHistoryResponseDTO> getDefaultList() {
    return List.of(getDefault());
  }
}

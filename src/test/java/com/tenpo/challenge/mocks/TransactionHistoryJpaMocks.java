package com.tenpo.challenge.mocks;

import com.tenpo.challenge.entity.jpa.TransactionHistoryJpaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionHistoryJpaMocks {

  public static TransactionHistoryJpaEntity getDefault() {
    return TransactionHistoryJpaEntity.builder()
        .id(DefaultValues.ID)
        .endpoint(DefaultValues.ENDPOINT)
        .status(DefaultValues.STATUS)
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static List<TransactionHistoryJpaEntity> getDefaultList() {
    return List.of(
        TransactionHistoryJpaEntity.builder()
            .id(DefaultValues.ID)
            .endpoint(DefaultValues.ENDPOINT)
            .status(DefaultValues.STATUS)
            .createdAt(LocalDateTime.now())
            .build());
  }
}

package com.tenpo.challenge.adapter.mapper.jpa;

import com.tenpo.challenge.entity.jpa.TransactionHistoryJpaEntity;
import com.tenpo.challenge.entity.model.TransactionHistory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionHistoryJpaMapper {

  public static TransactionHistory map(TransactionHistoryJpaEntity transactionHistory) {
    return TransactionHistory.builder()
        .endpoint(transactionHistory.getEndpoint())
        .status(transactionHistory.getStatus())
        .createdAt(transactionHistory.getCreatedAt())
        .build();
  }
}

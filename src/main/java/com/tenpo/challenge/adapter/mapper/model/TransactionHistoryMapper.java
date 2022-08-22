package com.tenpo.challenge.adapter.mapper.model;

import com.tenpo.challenge.controller.dto.response.TransactionHistoryResponseDTO;
import com.tenpo.challenge.entity.jpa.TransactionHistoryJpaEntity;
import com.tenpo.challenge.entity.model.TransactionHistory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionHistoryMapper {

  public static TransactionHistoryResponseDTO mapResponse(TransactionHistory transactionHistory) {
    return TransactionHistoryResponseDTO.builder()
        .status(transactionHistory.getStatus())
        .endpoint(transactionHistory.getEndpoint())
        .createdAt(transactionHistory.getCreatedAt())
        .build();
  }

  public static TransactionHistoryJpaEntity map(TransactionHistory transactionHistory) {
    return TransactionHistoryJpaEntity.builder()
        .endpoint(transactionHistory.getEndpoint())
        .status(transactionHistory.getStatus())
        .build();
  }

  public static List<TransactionHistoryResponseDTO> map(List<TransactionHistory> histories) {
    return histories.stream().map(TransactionHistoryMapper::mapResponse).toList();
  }
}

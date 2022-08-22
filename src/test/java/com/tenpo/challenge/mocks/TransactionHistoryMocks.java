package com.tenpo.challenge.mocks;

import com.tenpo.challenge.entity.model.TransactionHistory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionHistoryMocks {

  public static TransactionHistory getDefault() {
    return TransactionHistory.builder()
        .endpoint(DefaultValues.ENDPOINT)
        .status(DefaultValues.STATUS)
        .build();
  }

  public static List<TransactionHistory> getDefaultList() {
    return List.of(getDefault());
  }
}

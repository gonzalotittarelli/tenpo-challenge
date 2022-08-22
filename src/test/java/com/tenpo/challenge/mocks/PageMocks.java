package com.tenpo.challenge.mocks;

import com.tenpo.challenge.entity.jpa.TransactionHistoryJpaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import static org.mockito.Mockito.mock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageMocks {

  public static Page<TransactionHistoryJpaEntity> getDefault() {
    return mock(Page.class);
  }
}

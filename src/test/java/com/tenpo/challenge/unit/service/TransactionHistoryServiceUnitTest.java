package com.tenpo.challenge.unit.service;

import com.tenpo.challenge.entity.jpa.TransactionHistoryJpaEntity;
import com.tenpo.challenge.entity.model.TransactionHistory;
import com.tenpo.challenge.mocks.DefaultValues;
import com.tenpo.challenge.mocks.PageMocks;
import com.tenpo.challenge.mocks.TransactionHistoryJpaMocks;
import com.tenpo.challenge.mocks.TransactionHistoryMocks;
import com.tenpo.challenge.repository.TransactionHistoryJpaRepository;
import com.tenpo.challenge.service.impl.TransactionHistoryServiceImpl;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TransactionHistoryServiceUnitTest extends BaseUnitTest {
  @Mock TransactionHistoryJpaRepository transactionHistoryJpaRepository;

  @InjectMocks TransactionHistoryServiceImpl transactionHistoryService;

  @Test
  void givenTransactionHistory_whenSave_thenOk() {
    TransactionHistory transactionHistory = TransactionHistoryMocks.getDefault();

    when(transactionHistoryJpaRepository.save(any(TransactionHistoryJpaEntity.class)))
        .thenReturn(any(TransactionHistoryJpaEntity.class));

    assertDoesNotThrow(() -> transactionHistoryService.save(transactionHistory));

    verify(transactionHistoryJpaRepository, times(1)).save(any(TransactionHistoryJpaEntity.class));
  }

  @Test
  void givenPageSizeSortBy_whenFindAll_thenSomeContent() {
    List<TransactionHistoryJpaEntity> transactionHistoriesJpa =
        TransactionHistoryJpaMocks.getDefaultList();
    Page<TransactionHistoryJpaEntity> page = PageMocks.getDefault();
    when(transactionHistoryJpaRepository.findAll(any(Pageable.class))).thenReturn(page);
    when(page.hasContent()).thenReturn(true);
    when(page.getContent()).thenReturn(transactionHistoriesJpa);

    List<TransactionHistory> transactionHistories =
        transactionHistoryService.findAll(
            DefaultValues.PAGE, DefaultValues.SIZE, DefaultValues.SORT_BY);
    verify(transactionHistoryJpaRepository, times(1)).findAll(any(Pageable.class));
    assertNotNull(transactionHistories);
    assertEquals(transactionHistories.size(), transactionHistoriesJpa.size());
  }

  @Test
  void givenPageSizeSortBy_whenFindAll_thenEmptyList() {
    Page<TransactionHistoryJpaEntity> page = PageMocks.getDefault();
    when(transactionHistoryJpaRepository.findAll(any(Pageable.class))).thenReturn(page);
    when(page.hasContent()).thenReturn(false);
    when(page.getContent()).thenReturn(List.of());

    List<TransactionHistory> transactionHistories =
        transactionHistoryService.findAll(
            DefaultValues.PAGE, DefaultValues.SIZE, DefaultValues.SORT_BY);
    verify(transactionHistoryJpaRepository, times(1)).findAll(any(Pageable.class));
    assertNotNull(transactionHistories);
    assertTrue(transactionHistories.isEmpty());
  }
}

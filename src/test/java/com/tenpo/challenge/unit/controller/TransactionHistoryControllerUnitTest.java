package com.tenpo.challenge.unit.controller;

import com.tenpo.challenge.controller.TransactionHistoryController;
import com.tenpo.challenge.controller.dto.response.TransactionHistoryResponseDTO;
import com.tenpo.challenge.entity.model.TransactionHistory;
import com.tenpo.challenge.mocks.*;
import com.tenpo.challenge.service.TransactionHistoryService;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionHistoryControllerUnitTest extends BaseUnitTest {

  @Mock TransactionHistoryService transactionHistoryService;
  @InjectMocks TransactionHistoryController transactionHistoryController;

  @Test
  void givenRequest_whenFindAll_thenContent() {
    List<TransactionHistory> transactionHistories = TransactionHistoryMocks.getDefaultList();
    List<TransactionHistoryResponseDTO> histories =
        TransactionHistoryResponseMocks.getDefaultList();

    when(transactionHistoryService.findAll(anyInt(), anyInt(), anyString()))
        .thenReturn(transactionHistories);

    ResponseEntity<List<TransactionHistoryResponseDTO>> response =
        transactionHistoryController.findAll(
            DefaultValues.TOKEN, DefaultValues.PAGE, DefaultValues.SIZE, DefaultValues.SORT_BY);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().size(), histories.size());
    verify(transactionHistoryService, times(1)).findAll(anyInt(), anyInt(), anyString());
  }

  @Test
  void givenRequest_whenFindAll_thenNoContent() {
    when(transactionHistoryService.findAll(anyInt(), anyInt(), anyString())).thenReturn(List.of());

    ResponseEntity<List<TransactionHistoryResponseDTO>> response =
        transactionHistoryController.findAll(
            DefaultValues.TOKEN, DefaultValues.PAGE, DefaultValues.SIZE, DefaultValues.SORT_BY);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertNotNull(response.getBody());
    assertTrue(response.getBody().isEmpty());
    verify(transactionHistoryService, times(1)).findAll(anyInt(), anyInt(), anyString());
  }
}

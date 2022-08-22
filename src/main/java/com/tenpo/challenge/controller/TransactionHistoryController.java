package com.tenpo.challenge.controller;

import com.tenpo.challenge.annotation.TrackOperation;
import com.tenpo.challenge.controller.dto.response.TransactionHistoryResponseDTO;
import com.tenpo.challenge.entity.model.TransactionHistory;
import com.tenpo.challenge.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class TransactionHistoryController {

  private static final String DEFAULT_PAGE = "0";
  private static final String DEFAULT_SIZE = "10";
  private static final String DEFAULT_SORT = "id";

  @Autowired private final TransactionHistoryService transactionHistoryService;

  @TrackOperation(endpoint = "/history")
  @GetMapping
  public ResponseEntity<List<TransactionHistoryResponseDTO>> findAll(
      @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
      @RequestParam(defaultValue = DEFAULT_SIZE) Integer size,
      @RequestParam(defaultValue = DEFAULT_SORT) String sortBy) {
    List<TransactionHistory> histories = transactionHistoryService.findAll(page, size, sortBy);
    return ResponseEntity.ok(map(histories));
  }

  private List<TransactionHistoryResponseDTO> map(List<TransactionHistory> histories) {
    return histories.stream().map(this::map).toList();
  }

  private TransactionHistoryResponseDTO map(TransactionHistory history) {
    return TransactionHistoryResponseDTO.builder()
        .status(history.getStatus())
        .endpoint(history.getEndpoint())
        .createdAt(history.getCreatedAt())
        .build();
  }
}

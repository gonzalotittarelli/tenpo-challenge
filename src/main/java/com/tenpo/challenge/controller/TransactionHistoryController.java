package com.tenpo.challenge.controller;

import com.tenpo.challenge.adapter.mapper.model.TransactionHistoryMapper;
import com.tenpo.challenge.annotation.TrackOperation;
import com.tenpo.challenge.controller.dto.response.TransactionHistoryResponseDTO;
import com.tenpo.challenge.entity.model.TransactionHistory;
import com.tenpo.challenge.service.TransactionHistoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class TransactionHistoryController {

  private static final String DEFAULT_PAGE = "0";
  private static final String DEFAULT_SIZE = "10";
  private static final String DEFAULT_SORT = "id";

  @Autowired private final TransactionHistoryService transactionHistoryService;

  @TrackOperation(endpoint = "/history")
  @ApiOperation(
      value = "Transaction histories",
      notes = "Gets transaction histories with pagination")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Transaction histories"),
      })
  @GetMapping
  public ResponseEntity<List<TransactionHistoryResponseDTO>> findAll(
      @RequestHeader(AUTHORIZATION) String token,
      @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
      @RequestParam(defaultValue = DEFAULT_SIZE) Integer size,
      @RequestParam(defaultValue = DEFAULT_SORT) String sortBy) {
    List<TransactionHistory> histories = transactionHistoryService.findAll(page, size, sortBy);
    return ResponseEntity.ok(TransactionHistoryMapper.map(histories));
  }
}

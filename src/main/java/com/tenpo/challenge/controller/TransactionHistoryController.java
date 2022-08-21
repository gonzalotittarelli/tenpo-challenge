package com.tenpo.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class TransactionHistoryController {

  @GetMapping()
  public ResponseEntity<Void> all() {
    return ResponseEntity.ok().build();
  }
}

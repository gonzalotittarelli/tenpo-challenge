package com.tenpo.challenge.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {

  private Long id;

  private String endpoint;

  private String status;

  private String response;

  private LocalDateTime createdAt;
}

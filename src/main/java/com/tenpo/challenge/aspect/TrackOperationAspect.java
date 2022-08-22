package com.tenpo.challenge.aspect;

import com.tenpo.challenge.annotation.TrackOperation;
import com.tenpo.challenge.entity.model.TransactionHistory;
import com.tenpo.challenge.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackOperationAspect {

  @Autowired private final TransactionHistoryService transactionHistoryService;

  @AfterReturning(pointcut = "@annotation(trackOperation)", returning = "responseEntity")
  public void track(
      JoinPoint joinPoint, TrackOperation trackOperation, ResponseEntity<?> responseEntity) {
    transactionHistoryService.save(
        TransactionHistory.builder()
            .endpoint(trackOperation.endpoint())
            .status(responseEntity.getStatusCode().name())
            .build());
  }
}

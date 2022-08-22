package com.tenpo.challenge.unit.aspect;

import com.tenpo.challenge.annotation.TrackOperation;
import com.tenpo.challenge.aspect.TrackOperationAspect;
import com.tenpo.challenge.entity.model.TransactionHistory;
import com.tenpo.challenge.mocks.DefaultValues;
import com.tenpo.challenge.service.TransactionHistoryService;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class TrackOperationAspectUnitTest extends BaseUnitTest {

  @Mock JoinPoint joinPoint;
  @Mock TransactionHistoryService transactionHistoryService;

  @InjectMocks TrackOperationAspect trackOperationAspect;

  @Test
  void whenTrack_thenOk() {
    TrackOperation operation = mock(TrackOperation.class);
    when(operation.endpoint()).thenReturn(DefaultValues.ENDPOINT);
    ResponseEntity<?> response = mock(ResponseEntity.class);
    when(response.getStatusCode()).thenReturn(HttpStatus.OK);
    assertDoesNotThrow(() -> trackOperationAspect.track(joinPoint, operation, response));
    verify(transactionHistoryService, times(1)).save(any(TransactionHistory.class));
  }
}

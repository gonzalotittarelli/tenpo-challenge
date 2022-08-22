package com.tenpo.challenge.unit.controller;

import com.tenpo.challenge.controller.CalculatorController;
import com.tenpo.challenge.controller.dto.response.CalculatorResponseDTO;
import com.tenpo.challenge.mocks.DefaultValues;
import com.tenpo.challenge.service.CalculatorService;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalculatorControllerUnitTest extends BaseUnitTest {

  @Mock CalculatorService calculatorService;

  @InjectMocks CalculatorController calculatorController;

  @Test
  void givenSomeNumbers_whenAddWithRandomPercentage_thenOk() {

    when(calculatorService.addWithRandomPercentage(any(BigDecimal.class), any(BigDecimal.class)))
        .thenReturn(new BigDecimal(DefaultValues.RESULT));

    ResponseEntity<CalculatorResponseDTO> response =
        calculatorController.add(
            DefaultValues.TOKEN, DefaultValues.NUMBER_X, DefaultValues.NUMBER_Y);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertNotNull(response.getBody());
    assertNotNull(response.getBody().getResult());
    assertEquals(response.getBody().getResult().intValue(), DefaultValues.RESULT);
    verify(calculatorService, times(1))
        .addWithRandomPercentage(any(BigDecimal.class), any(BigDecimal.class));
  }
}

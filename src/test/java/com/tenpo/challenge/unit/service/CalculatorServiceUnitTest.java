package com.tenpo.challenge.unit.service;

import com.tenpo.challenge.mocks.DefaultValues;
import com.tenpo.challenge.service.PercentageService;
import com.tenpo.challenge.service.impl.CalculatorServiceImpl;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CalculatorServiceUnitTest extends BaseUnitTest {

  @Mock PercentageService percentageService;

  @InjectMocks CalculatorServiceImpl calculatorService;

  @Test
  void givenNumbers_whenAdd_thenThrowUserAlreadyExistsException() {

    when(percentageService.getRandomPercentage()).thenReturn(DefaultValues.PERCENTAGE);

    BigDecimal result =
        calculatorService.addWithRandomPercentage(DefaultValues.NUMBER_X, DefaultValues.NUMBER_Y);

    verify(percentageService, times(1)).getRandomPercentage();
    assertNotNull(result);
    assertEquals(result.intValue(), DefaultValues.RESULT);
  }
}

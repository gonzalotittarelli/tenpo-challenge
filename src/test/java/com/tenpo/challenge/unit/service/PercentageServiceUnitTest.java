package com.tenpo.challenge.unit.service;

import com.tenpo.challenge.exception.PercentageNotFoundException;
import com.tenpo.challenge.mocks.DefaultValues;
import com.tenpo.challenge.service.external.PercentageServiceImpl;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PercentageServiceUnitTest extends BaseUnitTest {

  @Mock RestTemplate restTemplate;

  @InjectMocks PercentageServiceImpl percentageService;

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(percentageService, "baseUrl", DefaultValues.EMPTY);
  }

  @Test
  void givenEmptyList_whenRandomPercentage_thenThrowPercentageNotFoundException() {

    when(restTemplate.getForObject(anyString(), any())).thenReturn(List.of());

    assertThrows(PercentageNotFoundException.class, () -> percentageService.getRandomPercentage());

    verify(restTemplate, times(1)).getForObject(anyString(), any());
  }

  @Test
  void givenNull_whenRandomPercentage_thenThrowPercentageNotFoundException() {

    when(restTemplate.getForObject(anyString(), any())).thenReturn(null);

    assertThrows(PercentageNotFoundException.class, () -> percentageService.getRandomPercentage());

    verify(restTemplate, times(1)).getForObject(anyString(), any());
  }

  @Test
  void givenNumber_whenRandomPercentage_thenThrowPercentageNotFoundException() {

    when(restTemplate.getForObject(anyString(), any()))
        .thenReturn(List.of(DefaultValues.PERCENTAGE));

    Integer response = percentageService.getRandomPercentage();

    verify(restTemplate, times(1)).getForObject(anyString(), any());
    assertNotNull(response);
    assertEquals(response, DefaultValues.PERCENTAGE);
  }
}

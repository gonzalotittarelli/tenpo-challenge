package com.tenpo.challenge.service.impl;

import com.tenpo.challenge.service.CalculatorService;
import com.tenpo.challenge.service.PercentageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

  @Autowired private final PercentageService percentageService;

  @Override
  public BigDecimal addWithRandomPercentage(BigDecimal x, BigDecimal y) {
    BigDecimal sum = x.add(y);
    int percent = percentageService.getRandomPercentage();
    return sum.add((sum.multiply(BigDecimal.valueOf(percent / 100.0f))));
  }
}

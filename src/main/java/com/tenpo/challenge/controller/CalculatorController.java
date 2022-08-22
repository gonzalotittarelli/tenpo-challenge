package com.tenpo.challenge.controller;

import com.tenpo.challenge.annotation.TrackOperation;
import com.tenpo.challenge.controller.dto.response.CalculatorResponseDTO;
import com.tenpo.challenge.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

  private static final String NUMBER_X = "x";
  private static final String NUMBER_Y = "y";

  @Autowired private final CalculatorService calculatorService;

  @TrackOperation(endpoint = "/calculator/add")
  @GetMapping("/add")
  public ResponseEntity<CalculatorResponseDTO> add(
      @RequestParam(name = NUMBER_X) BigDecimal x, @RequestParam(name = NUMBER_Y) BigDecimal y) {
    BigDecimal result = calculatorService.addWithRandomPercentage(x, y);
    return ResponseEntity.ok(CalculatorResponseDTO.builder().result(result).build());
  }
}

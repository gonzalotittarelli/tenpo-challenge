package com.tenpo.challenge.controller;

import com.tenpo.challenge.annotation.TrackOperation;
import com.tenpo.challenge.controller.dto.response.CalculatorResponseDTO;
import com.tenpo.challenge.service.CalculatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

  private static final String NUMBER_X = "x";
  private static final String NUMBER_Y = "y";

  @Autowired private final CalculatorService calculatorService;

  @TrackOperation(endpoint = "/calculator/add")
  @ApiOperation(
      value = "Calculator with add capability",
      notes = "Add two given numbers and apply random percentage")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Result of add two given numbers with apply random percentage"),
      })
  @GetMapping("/add")
  public ResponseEntity<CalculatorResponseDTO> add(
      @RequestHeader(AUTHORIZATION) String token,
      @RequestParam(name = NUMBER_X) BigDecimal x,
      @RequestParam(name = NUMBER_Y) BigDecimal y) {
    BigDecimal result = calculatorService.addWithRandomPercentage(x, y);
    return ResponseEntity.ok(CalculatorResponseDTO.builder().result(result).build());
  }
}

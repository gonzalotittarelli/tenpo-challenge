package com.tenpo.challenge.service.external;

import com.tenpo.challenge.exception.PercentageNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PercentageServiceImpl implements PercentageService {
  private static final String PERCENTAGE_SERVICE = "percentageService";
  private Optional<Integer> lastValue = Optional.empty();
  private static final String PERCENTAGE_RECEIVED = "Percentage received {}";
  private static final String ERROR_PERCENTAGE_NOT_FOUND = "Percentage not found";

  @Value("${tenpo.rest.client.get-percent.baseUrl}")
  private String baseUrl;

  @Autowired private final RestTemplate restTemplate;

  @Override
  @CircuitBreaker(name = PERCENTAGE_SERVICE, fallbackMethod = "percentageByDefault")
  @Retry(name = PERCENTAGE_SERVICE, fallbackMethod = "percentageByDefault")
  @RateLimiter(name = PERCENTAGE_SERVICE, fallbackMethod = "percentageByDefault")
  public int getRandomPercentage() {
    Object response = restTemplate.getForObject(baseUrl, Object.class);
    List<Integer> result =
        Optional.ofNullable((List<Integer>) response)
            .orElseThrow(() -> new PercentageNotFoundException(ERROR_PERCENTAGE_NOT_FOUND));
    if (!result.isEmpty()) {
      lastValue = Optional.of(result.get(0));
      log.debug(PERCENTAGE_RECEIVED, lastValue);
      return lastValue.get();
    }
    throw new PercentageNotFoundException(ERROR_PERCENTAGE_NOT_FOUND);
  }

  public int percentageByDefault(Exception e) {
    if (lastValue.isPresent()) return lastValue.get();
    throw new PercentageNotFoundException(ERROR_PERCENTAGE_NOT_FOUND);
  }
}

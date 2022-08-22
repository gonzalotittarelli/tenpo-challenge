package com.tenpo.challenge.unit;

import com.tenpo.challenge.config.SpringTestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
public class BaseUnitTest {

  @Before
  void before() {
    MockitoAnnotations.openMocks(this);
  }

  @After
  void after() {}
}

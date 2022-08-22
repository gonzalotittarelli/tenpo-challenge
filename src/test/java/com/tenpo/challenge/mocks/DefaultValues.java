package com.tenpo.challenge.mocks;

import com.tenpo.challenge.entity.jpa.UserRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultValues {
  public static final Long ID = 1L;

  public static final String USERNAME = "test";

  public static final String PASSWORD = "password";

  public static final String EMAIL = "email";

  public static final UserRole ROLE = UserRole.USER;

  public static final String ENDPOINT = "endpoint";

  public static final String STATUS = "status";

  public static final Integer PAGE = 1;

  public static final Integer SIZE = 10;

  public static final String SORT_BY = "id";

  public static final BigDecimal NUMBER_X = new BigDecimal(5);

  public static final BigDecimal NUMBER_Y = new BigDecimal(5);

  public static final Integer PERCENTAGE = 10;

  public static final Integer RESULT = 11;

  public static final String TOKEN = "token";

  public static final String EMPTY = "";
}

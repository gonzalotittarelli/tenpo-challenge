package com.tenpo.challenge.mocks;

import com.tenpo.challenge.entity.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMocks {

  public static User getDefault() {
    return User.builder()
        .username(DefaultValues.USERNAME)
        .password(DefaultValues.PASSWORD)
        .email(DefaultValues.EMAIL)
        .build();
  }

  public static User getDefaultWithId() {
    return User.builder()
        .id(DefaultValues.ID)
        .username(DefaultValues.USERNAME)
        .password(DefaultValues.PASSWORD)
        .email(DefaultValues.EMAIL)
        .build();
  }
}

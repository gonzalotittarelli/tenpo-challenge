package com.tenpo.challenge.unit.service;

import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.mocks.DefaultValues;
import com.tenpo.challenge.mocks.UserJpaEntityMocks;
import com.tenpo.challenge.repository.UserJpaRepository;
import com.tenpo.challenge.service.auth.UserDetailsServiceImpl;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserDetailsServiceUnitTest extends BaseUnitTest {

  @Mock UserJpaRepository userJpaRepository;
  @InjectMocks UserDetailsServiceImpl userDetailsService;

  @Test
  void givenUsername_whenLoadUserByUsername_thenThrowUsernameNotFoundException() {

    when(userJpaRepository.findByUsername(DefaultValues.USERNAME))
        .thenThrow(UsernameNotFoundException.class);

    assertThrows(
        UsernameNotFoundException.class,
        () -> userDetailsService.loadUserByUsername(DefaultValues.USERNAME));
    verify(userJpaRepository, times(1)).findByUsername(anyString());
  }

  @Test
  void givenUsername_whenLoadUserByUsername_thenOk() {
    UserJpaEntity user = UserJpaEntityMocks.getDefault();
    when(userJpaRepository.findByUsername(DefaultValues.USERNAME)).thenReturn(Optional.of(user));

    UserDetails result = userDetailsService.loadUserByUsername(DefaultValues.USERNAME);
    verify(userJpaRepository, times(1)).findByUsername(anyString());
    assertNotNull(result);
    assertEquals(user, result);
  }
}

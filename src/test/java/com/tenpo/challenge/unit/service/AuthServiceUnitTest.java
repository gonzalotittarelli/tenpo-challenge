package com.tenpo.challenge.unit.service;

import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.entity.model.User;
import com.tenpo.challenge.mocks.AuthenticationMocks;
import com.tenpo.challenge.mocks.DefaultValues;
import com.tenpo.challenge.mocks.UserJpaEntityMocks;
import com.tenpo.challenge.mocks.UserMocks;
import com.tenpo.challenge.service.auth.AuthServiceImpl;
import com.tenpo.challenge.service.auth.JWTTokenServiceImpl;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthServiceUnitTest extends BaseUnitTest {
  @Mock AuthenticationManager authenticationManager;

  @Mock JWTTokenServiceImpl jwtTokenServiceImpl;

  @InjectMocks AuthServiceImpl authService;

  @Test
  void givenUser_whenLogin_thenOk() {
    User user = UserMocks.getDefault();
    UserJpaEntity logged = UserJpaEntityMocks.getDefault();

    Authentication authentication = AuthenticationMocks.getDefault();

    when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(Boolean.TRUE);
    when(authentication.getPrincipal()).thenReturn(logged);
    when(jwtTokenServiceImpl.getJWTToken(any(Authentication.class)))
        .thenReturn(DefaultValues.TOKEN);

    String token = authService.login(user);

    verify(authenticationManager, times(1)).authenticate(any(Authentication.class));
    verify(jwtTokenServiceImpl, times(1)).getJWTToken(any(Authentication.class));
    assertNotNull(token);
    assertEquals(token, DefaultValues.TOKEN);
  }

  @Test
  void givenUser_whenLogin_thenThrowBadCredentialsException() {
    User user = UserMocks.getDefault();

    when(authenticationManager.authenticate(any(Authentication.class)))
        .thenThrow(BadCredentialsException.class);

    assertThrows(BadCredentialsException.class, () -> authService.login(user));
    verify(authenticationManager, times(1)).authenticate(any(Authentication.class));
    verify(jwtTokenServiceImpl, never()).getJWTToken(any(Authentication.class));
  }

  @Test
  void givenUser_whenLogout_thenOk() {
    assertDoesNotThrow(() -> authService.logout(DefaultValues.TOKEN));
    verify(jwtTokenServiceImpl, times(1)).invalidateUserToken(anyString());
  }
}

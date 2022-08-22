package com.tenpo.challenge.unit.controller;

import com.tenpo.challenge.controller.UserController;
import com.tenpo.challenge.controller.dto.request.UserLoginRequestDTO;
import com.tenpo.challenge.controller.dto.request.UserRequestDTO;
import com.tenpo.challenge.controller.dto.response.TokenResponseDTO;
import com.tenpo.challenge.controller.dto.response.UserResponseDTO;
import com.tenpo.challenge.entity.model.User;
import com.tenpo.challenge.mocks.*;
import com.tenpo.challenge.service.AuthService;
import com.tenpo.challenge.service.UserService;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerUnitTest extends BaseUnitTest {

  @Mock UserService userService;
  @Mock AuthService authService;

  @InjectMocks UserController userController;

  @Test
  void givenUserRequest_whenSignup_thenOk() {
    UserRequestDTO request = UserRequestDTOMocks.getDefault();
    User user = UserMocks.getDefaultWithId();

    when(userService.save(any(User.class))).thenReturn(user);

    ResponseEntity<UserResponseDTO> response = userController.signup(request);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getEmail(), user.getEmail());
    assertEquals(response.getBody().getUsername(), user.getUsername());
    assertEquals(response.getBody().getId(), user.getId());
    verify(userService, times(1)).save(any(User.class));
  }

  @Test
  void givenUserLoginRequest_whenLogin_thenOk() {
    UserLoginRequestDTO request = UserLoginRequestMocks.getDefault();
    TokenResponseDTO token = TokenResponseDTOMocks.getDefault();

    when(authService.login(any(User.class))).thenReturn(DefaultValues.TOKEN);

    ResponseEntity<TokenResponseDTO> response = userController.login(request);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getAccessToken(), token.getAccessToken());
    verify(authService, times(1)).login(any(User.class));
  }

  @Test
  void givenUserLoginRequest_whenLogout_thenOk() {

    doNothing().when(authService).logout(anyString());

    ResponseEntity<Void> response = userController.logout(DefaultValues.TOKEN);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertNull(response.getBody());
    verify(authService, times(1)).logout(anyString());
  }
}

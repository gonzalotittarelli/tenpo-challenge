package com.tenpo.challenge.controller;

import com.tenpo.challenge.annotation.TrackOperation;
import com.tenpo.challenge.controller.dto.request.UserLoginRequestDTO;
import com.tenpo.challenge.controller.dto.request.UserRequestDTO;
import com.tenpo.challenge.controller.dto.response.TokenResponseDTO;
import com.tenpo.challenge.controller.dto.response.UserResponseDTO;
import com.tenpo.challenge.entity.model.User;
import com.tenpo.challenge.service.AuthService;
import com.tenpo.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private final UserService userService;
  @Autowired private final AuthService authService;

  @TrackOperation(endpoint = "/user/signup")
  @PostMapping("/signup")
  public ResponseEntity<UserResponseDTO> signup(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    User user = this.userService.save(map(userRequestDTO));
    return ResponseEntity.ok(map(user));
  }

  @TrackOperation(endpoint = "/user/login")
  @PostMapping("/login")
  public ResponseEntity<TokenResponseDTO> login(
      @Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO) {
    String token = authService.login(map(userLoginRequestDTO));
    return ResponseEntity.ok(TokenResponseDTO.builder().accessToken(token).build());
  }

  @TrackOperation(endpoint = "/user/logout")
  @GetMapping("/logout")
  public ResponseEntity<Void> logout(@RequestHeader(AUTHORIZATION) String token) {
    authService.logout(token);
    return ResponseEntity.ok().build();
  }

  private User map(UserLoginRequestDTO userLoginRequestDTO) {
    return User.builder()
        .username(userLoginRequestDTO.getUsername())
        .password(userLoginRequestDTO.getPassword())
        .build();
  }

  private User map(UserRequestDTO userRequestDTO) {
    return User.builder()
        .username(userRequestDTO.getUsername())
        .password(userRequestDTO.getPassword())
        .email(userRequestDTO.getEmail())
        .build();
  }

  private UserResponseDTO map(User user) {
    return UserResponseDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .build();
  }
}

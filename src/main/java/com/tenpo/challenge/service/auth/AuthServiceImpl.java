package com.tenpo.challenge.service.auth;

import com.tenpo.challenge.entity.model.User;
import com.tenpo.challenge.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private static final String ERROR_BAD_CREDENTIALS = "Bad credentials";
  @Autowired private final AuthenticationManager authenticationManager;

  @Autowired private JWTTokenServiceImpl jwtTokenServiceImpl;

  @Override
  public String login(User user) {
    try {
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
      Authentication authentication =
          this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return this.jwtTokenServiceImpl.getJWTToken(authentication);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException(ERROR_BAD_CREDENTIALS);
    }
  }

  @Override
  public void logout(String token) {
    this.jwtTokenServiceImpl.invalidateUserToken(token);
  }
}

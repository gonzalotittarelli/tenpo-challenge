package com.tenpo.challenge.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.service.auth.JWTTokenServiceImpl;
import com.tenpo.challenge.service.auth.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

  private static final String LOGIN = "/user/login";

  private static final String SIGNUP = "/user/signup";

  @Autowired private final JWTTokenServiceImpl jwtTokenService;

  @Autowired private final UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String url = request.getRequestURL().toString();
    if (url.contains(LOGIN) || url.contains(SIGNUP)) {
      filterChain.doFilter(request, response);
    } else {
      String token = request.getHeader(AUTHORIZATION);
      try {
        String username = this.jwtTokenService.getUsernameFromToken(token);
        if (username != null) this.authenticateWithToken(username, token);
        filterChain.doFilter(request, response);
      } catch (Exception e) {
        response.setHeader("error", e.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    }
  }

  private void authenticateWithToken(String username, String token) {
    if (username != null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (this.jwtTokenService.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
  }
}

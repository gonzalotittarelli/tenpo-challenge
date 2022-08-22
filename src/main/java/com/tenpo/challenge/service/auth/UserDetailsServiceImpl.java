package com.tenpo.challenge.service.auth;

import com.tenpo.challenge.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final String USER_NOT_FOUND = "User with username %s not found";
  @Autowired private final UserJpaRepository userJpaRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userJpaRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
  }
}

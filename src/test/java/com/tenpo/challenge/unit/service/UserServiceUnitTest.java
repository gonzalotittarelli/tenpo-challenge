package com.tenpo.challenge.unit.service;

import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import com.tenpo.challenge.entity.model.User;
import com.tenpo.challenge.exception.UserAlreadyExistsException;
import com.tenpo.challenge.mocks.UserJpaEntityMocks;
import com.tenpo.challenge.mocks.UserMocks;
import com.tenpo.challenge.repository.UserJpaRepository;
import com.tenpo.challenge.service.impl.UserServiceImpl;
import com.tenpo.challenge.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceUnitTest extends BaseUnitTest {

  @Mock UserJpaRepository userJpaRepository;
  @Mock BCryptPasswordEncoder bCryptPasswordEncoder;

  @InjectMocks UserServiceImpl userService;

  @Test
  void givenUser_whenSave_thenThrowUserAlreadyExistsException() {
    User user = UserMocks.getDefault();

    when(userJpaRepository.existsByUsername(user.getUsername())).thenReturn(true);

    assertThrows(UserAlreadyExistsException.class, () -> userService.save(user));
    verify(userJpaRepository, times(1)).existsByUsername(anyString());
    verify(userJpaRepository, times(0)).save(any(UserJpaEntity.class));
  }

  @Test
  void givenUser_whenSave_thenOk() {
    User user = UserMocks.getDefault();
    UserJpaEntity userJpaEntity = UserJpaEntityMocks.getDefault();

    when(userJpaRepository.existsByUsername(user.getUsername())).thenReturn(false);
    when(userJpaRepository.save(any(UserJpaEntity.class))).thenReturn(userJpaEntity);

    User response = userService.save(user);

    assertEquals(response.getUsername(), user.getUsername());
    assertEquals(response.getPassword(), user.getPassword());
    assertEquals(response.getEmail(), user.getEmail());
    verify(userJpaRepository, times(1)).existsByUsername(anyString());
    verify(userJpaRepository, times(1)).save(any(UserJpaEntity.class));
  }
}

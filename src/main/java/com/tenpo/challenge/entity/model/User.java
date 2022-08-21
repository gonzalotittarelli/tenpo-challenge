package com.tenpo.challenge.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private Long id;

  private String username;

  private String password;

  private String email;

  private LocalDateTime createdAt;
}

package com.tenpo.challenge.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

  @NotBlank(message = "Username is mandatory")
  private String username;

  @NotBlank(message = "Password is mandatory")
  private String password;

  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  private String email;
}

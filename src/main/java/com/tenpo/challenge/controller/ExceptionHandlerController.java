package com.tenpo.challenge.controller;

import com.tenpo.challenge.controller.dto.response.ApiError;
import com.tenpo.challenge.exception.PercentageNotFoundException;
import com.tenpo.challenge.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

  @ExceptionHandler({
    HttpRequestMethodNotSupportedException.class,
    HttpMessageNotReadableException.class,
    MethodArgumentTypeMismatchException.class
  })
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiError handleBadRequestException(HttpServletRequest req, Exception ex) {
    log.error(
        "BAD REQUEST: {}, uri {}, message {}",
        ex.getClass().getSimpleName(),
        req.getRequestURI(),
        ex.getMessage(),
        ex);
    return ApiError.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message(ex.getLocalizedMessage())
        .build();
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiError handleUserAlreadyExistsException(HttpServletRequest req, Exception ex) {
    log.error(
        "USER ALREADY EXISTS: {}, uri {}, message {}",
        ex.getClass().getSimpleName(),
        req.getRequestURI(),
        ex.getMessage(),
        ex);
    return ApiError.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message(ex.getLocalizedMessage())
        .build();
  }
  @ExceptionHandler(PercentageNotFoundException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handlePercentageNotFoundException(HttpServletRequest req, Exception ex) {
    log.error(
        "PERCENTAGE NOT FOUND: {}, uri {}, message {}",
        ex.getClass().getSimpleName(),
        req.getRequestURI(),
        ex.getMessage(),
        ex);
    return ApiError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(ex.getLocalizedMessage())
        .build();
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiError handleAuthenticationException(HttpServletRequest req, Exception ex) {
    log.error(
        "BAD CREDENTIALS: {}, uri {}, message {}",
        ex.getClass().getSimpleName(),
        req.getRequestURI(),
        ex.getMessage(),
        ex);
    return ApiError.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message(ex.getLocalizedMessage())
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handleUnknownException(HttpServletRequest req, Exception ex) {
    log.error(
        "INTERNAL SERVER ERROR: {}, uri {}, message {}",
        ex.getClass().getSimpleName(),
        req.getRequestURI(),
        ex.getMessage(),
        ex);
    return ApiError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message("The request could not be processed")
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiError handleValidationException(MethodArgumentNotValidException ex) {
    final List<String> errors = new ArrayList<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              errors.add(error.getDefaultMessage());
            });
    log.error(String.join(",", errors), ex);
    return ApiError.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message("Validation failed for arguments")
        .errors(errors)
        .build();
  }
}

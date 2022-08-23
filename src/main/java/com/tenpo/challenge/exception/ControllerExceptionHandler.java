package com.tenpo.challenge.exception;

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
public class ControllerExceptionHandler {

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
    return getError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
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
    return getError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
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
    return getError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
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
    return getError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
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
    return getError(HttpStatus.INTERNAL_SERVER_ERROR, "The request could not be processed");
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
    return getError(HttpStatus.BAD_REQUEST, "Validation failed for arguments", errors);
  }

  private ApiError getError(HttpStatus status, String message) {
    return new ApiError(status, message);
  }

  private ApiError getError(HttpStatus status, String message, List<String> errors) {
    return new ApiError(status, message, errors);
  }
}

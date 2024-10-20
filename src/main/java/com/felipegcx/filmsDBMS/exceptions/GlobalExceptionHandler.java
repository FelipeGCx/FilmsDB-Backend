package com.felipegcx.filmsDBMS.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.*;

//*||||||||||||||||||||||||*\\
//* GlobalExceptionHandler *\\
//*||||||||||||||||||||||||*\\

@ControllerAdvice
public class GlobalExceptionHandler {

  // handling exception parameter must be a number
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<?> handleMissingParams(
      MethodArgumentTypeMismatchException ex) {
    return createErrorResponse(
        "The parameter %s must be a number".formatted(ex.getName()),
        true,
        HttpStatus.BAD_REQUEST);
  }

  // handling exception method not allowed
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<?> handleMethodNotAllowed(
      HttpRequestMethodNotSupportedException ex) {
    return createErrorResponse(
        "Method %s not allowed".formatted(ex.getMethod()),
        true,
        HttpStatus.BAD_REQUEST);
  }

  // handling exception unauthorized
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex) {
    return createErrorResponse("Access Denied", true, HttpStatus.UNAUTHORIZED);
  }

  // handling exception bad credentials
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
    return createErrorResponse("Bad Credentials", true, HttpStatus.BAD_REQUEST);
  }

  // handling exception not found
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFound(NotFoundException ex) {
    return createErrorResponse(ex.getMessage(), false, HttpStatus.NOT_FOUND);
  }

  // handling global exception
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExceptionHandling(
      Exception ex,
      WebRequest request) {
    return createErrorResponse(
        ex.getMessage() + " class: " + ex.getClass(),
        true,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<?> createErrorResponse(
      String message,
      Boolean error,
      HttpStatus status) {
    Map<String, Object> response = new HashMap<String, Object>();
    response.put("error", error);
    response.put("message", message);
    return new ResponseEntity<>(response, status);
  }
}

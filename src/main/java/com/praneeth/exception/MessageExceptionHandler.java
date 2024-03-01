package com.praneeth.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * The type Message exception handler.
 */
@RestControllerAdvice
public class MessageExceptionHandler {

  /**
   * Handle entity not found exception error response.
   *
   * @param ex
   *     the ex
   *
   * @return the error response
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleEntityNotFoundException(
      ResourceNotFoundException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }


  /**
   * Handle exception error response.
   *
   * @param ex
   *     the ex
   *
   * @return the error response
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleException(Exception ex) {
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                             "An error occurred: " + ex.getMessage());
  }

  /**
   * Bad request error response.
   *
   * @param ex
   *     the ex
   *
   * @return the error response
   */
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse badRequest(Exception ex) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                             "An error occurred: " + ex.getMessage());
  }

  /**
   * Handle method argument not valid exception response entity.
   *
   * @param ex
   *     the ex
   *
   * @return the response entity
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    ErrorResponse errorResponse = new ErrorResponse(133, errors.toString());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  /**
   * Handle duplicate email error response.
   *
   * @param ex
   *     the ex
   * @param request
   *     the request
   *
   * @return the error response
   */
  @ExceptionHandler(DuplicateEmailException.class)
  protected ErrorResponse handleDuplicateEmail(DuplicateEmailException ex, WebRequest request) {
    String bodyOfResponse = "Duplicate email: " + ex.getMessage();
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                             "An error occurred: " + ex.getMessage());
  }
}

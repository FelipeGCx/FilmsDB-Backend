package com.felipegcx.filmsDBMS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//*||||||||||||||||*\\
//* NotFoundAdvice *\\
//*||||||||||||||||*\\

@ControllerAdvice
@ResponseBody
public class NotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String EntityNotFoundAdvice(NotFoundException ex) {
    return ex.getMessage();
  }
}

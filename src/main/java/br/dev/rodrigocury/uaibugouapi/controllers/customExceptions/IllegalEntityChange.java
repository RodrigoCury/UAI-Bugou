package br.dev.rodrigocury.uaibugouapi.controllers.customExceptions;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class IllegalEntityChange extends RuntimeException{
  HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

  public IllegalEntityChange(String message) {
    super(message);
  }
}

package br.dev.rodrigocury.uaibugouapi.controllers.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AccessDeniedHandler {

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<String> acessoNegado(AccessDeniedException ex, WebRequest request){
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
  }
}


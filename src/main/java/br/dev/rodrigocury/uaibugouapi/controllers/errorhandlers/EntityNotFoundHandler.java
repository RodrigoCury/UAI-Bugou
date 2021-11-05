package br.dev.rodrigocury.uaibugouapi.controllers.errorhandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class EntityNotFoundHandler {

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<String> funcionarioNaoEncontrado(EntityNotFoundException ex, WebRequest request){
    return ResponseEntity.notFound().build();
  }
}

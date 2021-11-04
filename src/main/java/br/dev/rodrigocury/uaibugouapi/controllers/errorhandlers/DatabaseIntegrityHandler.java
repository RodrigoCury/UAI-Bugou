package br.dev.rodrigocury.uaibugouapi.controllers.errorhandlers;

import br.dev.rodrigocury.uaibugouapi.dto.FormErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class DatabaseIntegrityHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({DataIntegrityViolationException.class})
  protected ResponseEntity<List<FormErrorDto>> handleDatabaseIntegrity(DataIntegrityViolationException ex, WebRequest request){

    List<FormErrorDto> listaErros = new ArrayList<>();

    if (ex.getMessage().contains("FUNCIONARIO(FUNCIONARIO_EMAIL)")){
      listaErros.add(new FormErrorDto("funcionarioEmail", "Email ja está cadastrado"));
    }
    return ResponseEntity.badRequest().body(listaErros);
  }
}

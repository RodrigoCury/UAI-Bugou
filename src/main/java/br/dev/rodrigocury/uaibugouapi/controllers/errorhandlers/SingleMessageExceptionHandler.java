package br.dev.rodrigocury.uaibugouapi.controllers.errorhandlers;

import br.dev.rodrigocury.uaibugouapi.controllers.customExceptions.IllegalEntityChange;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SingleMessageExceptionHandler {

  @ExceptionHandler({EntityNotFoundException.class, IllegalEntityChange.class})
  public ResponseEntity<Map<String, String>> singleMessageHandler(RuntimeException ex, WebRequest request){


    return ResponseEntity.badRequest().body(resObj(ex));
  }

  static Map<String, String> resObj(RuntimeException ex){
    Map<String, String> resObj = new HashMap<>();
    resObj.put("message", ex.getMessage());
    return resObj;
  }
}

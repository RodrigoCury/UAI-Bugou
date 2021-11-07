package br.dev.rodrigocury.uaibugouapi.controllers.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AccessDeniedHandler {

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<Map<String, Object>> acessoNegado(AccessDeniedException ex, WebRequest request){
    Map<String, Object> resObj = new HashMap<>();
    resObj.put("timestamp", LocalDateTime.now().toString());
    resObj.put("status", HttpStatus.FORBIDDEN.value());
    resObj.put("error", HttpStatus.FORBIDDEN.name());
    resObj.put("message", ex.getMessage());
    String path = request.getDescription(false);
    resObj.put("path", path.subSequence(5, path.length()).toString());

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resObj);
  }
}


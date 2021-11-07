package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.configs.security.TokenService;
import br.dev.rodrigocury.uaibugouapi.dto.AuthDTO;
import br.dev.rodrigocury.uaibugouapi.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity<AuthDTO> autenticar(@RequestBody @Valid LoginForm form){

    UsernamePasswordAuthenticationToken dadosLogin = form.converter();
    try {
      Authentication authenticate = authenticationManager.authenticate(dadosLogin);

      String token = tokenService.gerarToken(authenticate);
      return ResponseEntity.ok(new AuthDTO(token, "Bearer"));
    } catch (AuthenticationException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }
}

package br.dev.rodrigocury.uaibugouapi.utils;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import org.springframework.security.core.Authentication;

public class AuthenticationHelper {

  public static Funcionario getAutenticado(Authentication auth) {
    return (Funcionario) auth.getPrincipal();
  }
}

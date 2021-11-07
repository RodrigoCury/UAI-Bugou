package br.dev.rodrigocury.uaibugouapi.utils;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import org.springframework.security.core.Authentication;

public class AuthenticationHelper {

  public static Funcionario getAutenticado(Authentication auth) {
    return (Funcionario) auth.getPrincipal();
  }

  public static Empresa getEmpresaDoAutenticado(Authentication auth) {
    return AuthenticationHelper.getAutenticado(auth).getEmpresa();
  }
}

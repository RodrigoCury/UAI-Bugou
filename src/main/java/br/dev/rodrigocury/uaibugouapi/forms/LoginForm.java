package br.dev.rodrigocury.uaibugouapi.forms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {
  @NotEmpty
  @NotNull
  private String email;

  @NotEmpty
  @NotNull
  private String senha;

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }

  public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(email, senha);
  }
}

package br.dev.rodrigocury.uaibugouapi.dto;

public class AuthDTO {
  private String token;
  private String tipo;

  public String getToken() {
    return token;
  }

  public String getTipo() {
    return tipo;
  }

  public AuthDTO(String token, String tipo) {
    this.tipo = tipo;
    this.token = token;
  }
}

package br.dev.rodrigocury.uaibugouapi.dto;

public class FormErrorDto {
  private String campo;
  private String message;

  public FormErrorDto(String campo, String message) {
    this.campo = campo;
    this.message = message;
  }

  public String getCampo() {
    return campo;
  }

  public String getMessage() {
    return message;
  }
}

package br.dev.rodrigocury.uaibugouapi.dto;

@SuppressWarnings("ALL")
public class FormErrorDto {
  private final String campo;
  private final String message;

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

package br.dev.rodrigocury.uaibugouapi.models.enums;

public enum Privileges {
  ROLE_ADMINISTRADOR,
  ROLE_GERENTE,
  ROLE_FUNCIONARIO;

  @Override
  public String toString() {
    return this.name();
  }
}

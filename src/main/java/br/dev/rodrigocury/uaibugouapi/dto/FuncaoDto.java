package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;

class FuncaoDto {
  private final String funcaoNome;
  private final Long id;
  private final PrivilegiosDeAcesso privilegiosDeAcesso;

  public FuncaoDto(Funcao funcao) {
    this.funcaoNome = funcao.getFuncaoNome();
    this.id = funcao.getId();
    this.privilegiosDeAcesso = funcao.getPrivilegiosDeAcesso();
  }

  public String getFuncaoNome() {
    return funcaoNome;
  }

  public Long getId() {
    return id;
  }

  public PrivilegiosDeAcesso getPrivilegiosDeAcesso() {
    return privilegiosDeAcesso;
  }
}
package br.dev.rodrigocury.uaibugouapi.forms;

import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovaFuncaoForm {

  @NotNull
  @NotEmpty
  @Length(min = 2)
  private String funcaoNome;

  @NotNull
  private PrivilegiosDeAcesso privilegiosDeAcesso;

  public NovaFuncaoForm() {
  }

  public String getFuncaoNome() {
    return funcaoNome;
  }

  public void setFuncaoNome(String funcaoNome) {
    this.funcaoNome = funcaoNome;
  }

  public PrivilegiosDeAcesso getPrivilegiosDeAcesso() {
    return privilegiosDeAcesso;
  }

  public void setPrivilegiosDeAcesso(PrivilegiosDeAcesso privilegiosDeAcesso) {
    this.privilegiosDeAcesso = privilegiosDeAcesso;
  }
}

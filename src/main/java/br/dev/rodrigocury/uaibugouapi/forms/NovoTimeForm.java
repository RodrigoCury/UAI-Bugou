package br.dev.rodrigocury.uaibugouapi.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class NovoTimeForm {

  @NotNull
  @NotEmpty
  @Length(min = 3, max = 50)
  private String timeNome;

  private Set<Long> funcionariosIds;

  public NovoTimeForm() {
  }

  public String getTimeNome() {
    return timeNome;
  }

  public void setTimeNome(String timeNome) {
    this.timeNome = timeNome;
  }

  public Set<Long> getFuncionariosIds() {
    return funcionariosIds;
  }

  public void setFuncionariosIds(Set<Long> funcionariosId) {
    this.funcionariosIds = funcionariosId;
  }
}

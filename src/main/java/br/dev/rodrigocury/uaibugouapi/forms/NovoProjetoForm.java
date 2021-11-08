package br.dev.rodrigocury.uaibugouapi.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class NovoProjetoForm {

  @NotNull
  @NotEmpty
  @Length(min = 3)
  private String projetoNome;

  @NotNull
  private Long funcionarioResponsavelId;

  @NotNull
  private Set<Long> timesIds;

  public NovoProjetoForm(String projetoNome, Long funcionarioResponsavelId, Set<Long> timesIds) {
    this.projetoNome = projetoNome;
    this.funcionarioResponsavelId = funcionarioResponsavelId;
    this.timesIds = timesIds;
  }

  public String getProjetoNome() {
    return projetoNome;
  }

  public void setProjetoNome(String projetoNome) {
    this.projetoNome = projetoNome;
  }

  public Long getFuncionarioResponsavelId() {
    return funcionarioResponsavelId;
  }

  public void setFuncionarioResponsavelId(Long funcionarioResponsavelId) {
    this.funcionarioResponsavelId = funcionarioResponsavelId;
  }

  public Set<Long> getTimesIds() {
    return timesIds;
  }

  public void setTimesIds(Set<Long> timesIds) {
    this.timesIds = timesIds;
  }
}


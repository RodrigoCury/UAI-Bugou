package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;

import java.util.HashSet;
import java.util.Set;

public class TimeDto {

  private final Long timeId;
  private final String timeNome;
  private final Set<FuncionarioDto> funcionarios;
  // TODO Adicionar Projetos Set

  public TimeDto(Time time) {
    this.timeId = time.getTimeId();
    this.timeNome = time.getTimeNome();
    this.funcionarios = new HashSet<>();
    if (time.getFuncionarios() != null) {
      this.funcionarios.addAll(time.getFuncionarios().stream().map(FuncionarioDto::toFuncionarioDto).toList());
    }

  }

  public Long getTimeId() {
    return timeId;
  }

  public String getTimeNome() {
    return timeNome;
  }

  public Set<FuncionarioDto> getFuncionarios() {
    return funcionarios;
  }
}

package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Problema;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Projeto;

import java.util.HashSet;
import java.util.Set;

public class ProjetoDto {
  private final Long projetoId;
  private final String projetoNome;
  private final FuncionarioDto funcionarioResponsavel;

  //TODO problemas DTO
  private final Set<Problema> problemas;
  private final Set<TimeMenosDadosDto> times;

  public ProjetoDto(Projeto projeto) {
    this.projetoId = projeto.getProjetoId();
    this.projetoNome = projeto.getProjetoNome();
    this.funcionarioResponsavel = FuncionarioDto.toFuncionarioDto(projeto.getFuncionarioResponsavel());
    this.problemas = new HashSet<>();
    if (projeto.getProblemas() != null){
      this.problemas.addAll(projeto.getProblemas());
    }
    this.times = new HashSet<>();
    if (projeto.getTimes() != null){
      this.times.addAll( projeto.getTimes().stream().map(TimeMenosDadosDto::new).toList() );
    }
  }

  public Long getProjetoId() {
    return projetoId;
  }

  public String getProjetoNome() {
    return projetoNome;
  }

  public FuncionarioDto getFuncionarioResponsavel() {
    return funcionarioResponsavel;
  }

  public Set<Problema> getProblemas() {
    return problemas;
  }

  public Set<TimeMenosDadosDto> getTimes() {
    return times;
  }
}

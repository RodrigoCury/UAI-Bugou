package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;

import java.time.LocalDateTime;

@SuppressWarnings("ClassCanBeRecord")
public class FuncionarioDto {
  private final Long funcionarioId;
  private final String funcionarioNome;
  private final String funcionarioSobrenome;
  private final String funcionarioEmail;
  private final LocalDateTime adicionadoEm;
  private final boolean ativo;
  private final Funcao funcao;
  private final Time time;

  public FuncionarioDto(Long funcionarioId, String funcionarioNome, String funcionarioSobrenome, String funcionarioEmail, LocalDateTime adicionadoEm, boolean ativo, Funcao funcao, Time time) {
    this.funcionarioId = funcionarioId;
    this.funcionarioNome = funcionarioNome;
    this.funcionarioSobrenome = funcionarioSobrenome;
    this.funcionarioEmail = funcionarioEmail;
    this.adicionadoEm = adicionadoEm;
    this.ativo = ativo;
    this.funcao = funcao;
    this.time = time;
  }

  public Long getFuncionarioId() {
    return funcionarioId;
  }

  public String getFuncionarioNome() {
    return funcionarioNome;
  }

  public String getFuncionarioSobrenome() {
    return funcionarioSobrenome;
  }

  public String getFuncionarioEmail() {
    return funcionarioEmail;
  }

  public LocalDateTime getAdicionadoEm() {
    return adicionadoEm;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public Funcao getFuncao() {
    return funcao;
  }

  public Time getTime() {
    return time;
  }

  public static FuncionarioDto toFuncionarioDto(Funcionario f){
    return new FuncionarioDto(
        f.getFuncionarioId(),
        f.getFuncionarioNome(),
        f.getFuncionarioSobrenome(),
        f.getFuncionarioEmail(),
        f.getDataCriacao(),
        f.isAtivo(),
        f.getFuncao(),
        f.getTime()
    );
  }
}

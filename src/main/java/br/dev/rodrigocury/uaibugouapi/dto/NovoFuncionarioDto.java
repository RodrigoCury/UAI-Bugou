package br.dev.rodrigocury.uaibugouapi.dto;

public class NovoFuncionarioDto {
  private final Long funcionarioId;
  private final String funcionarioNome;
  private final String funcionarioSobrenome;
  private final String funcionarioEmail;

  public NovoFuncionarioDto(Long funcionarioId, String funcionarioNome, String funcionarioSobrenome, String funcionarioEmail) {
    this.funcionarioId = funcionarioId;
    this.funcionarioNome = funcionarioNome;
    this.funcionarioSobrenome = funcionarioSobrenome;
    this.funcionarioEmail = funcionarioEmail;
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
}

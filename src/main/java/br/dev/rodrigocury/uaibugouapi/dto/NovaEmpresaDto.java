package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;

public class NovaEmpresaDto {

  private final Long empresaId;
  private final String empresaNome;

  private final Long funcionarioId;
  private final String funcionarioNome;
  private final String funcionarioSobrenome;
  private final String funcionarioEmail;

  public NovaEmpresaDto(Empresa empresa, Funcionario funcionario) {
    this.empresaId = empresa.getEmpresaId();
    this.empresaNome = empresa.getEmpresaNome();

    this.funcionarioId = funcionario.getFuncionarioId();
    this.funcionarioNome = funcionario.getFuncionarioNome();
    this.funcionarioSobrenome = funcionario.getFuncionarioSobrenome();
    this.funcionarioEmail = funcionario.getFuncionarioEmail();
  }

  public Long getEmpresaId() {
    return empresaId;
  }

  public String getEmpresaNome() {
    return empresaNome;
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

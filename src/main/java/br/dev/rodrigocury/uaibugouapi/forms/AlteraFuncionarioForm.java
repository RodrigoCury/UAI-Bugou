package br.dev.rodrigocury.uaibugouapi.forms;

import br.dev.rodrigocury.uaibugouapi.forms.customvalidators.EmailValidator;
import br.dev.rodrigocury.uaibugouapi.forms.customvalidators.PasswordValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AlteraFuncionarioForm {
  @NotNull
  private Long funcaoId;

  @NotNull
  @Length(min = 3, max = 40)
  private String funcionarioNome;

  @NotNull
  @Length(min = 3, max = 50)
  private String funcionarioSobrenome;

  @EmailValidator
  private String funcionarioEmail;

  public AlteraFuncionarioForm() {
  }

  public Long getFuncaoId() {
    return funcaoId;
  }

  public void setFuncaoId(Long funcaoId) {
    this.funcaoId = funcaoId;
  }

  public String getFuncionarioNome() {
    return funcionarioNome;
  }

  public void setFuncionarioNome(String funcionarioNome) {
    this.funcionarioNome = funcionarioNome;
  }

  public String getFuncionarioSobrenome() {
    return funcionarioSobrenome;
  }

  public void setFuncionarioSobrenome(String funcionarioSobrenome) {
    this.funcionarioSobrenome = funcionarioSobrenome;
  }

  public String getFuncionarioEmail() {
    return funcionarioEmail;
  }

  public void setFuncionarioEmail(String funcionarioEmail) {
    this.funcionarioEmail = funcionarioEmail;
  }
}

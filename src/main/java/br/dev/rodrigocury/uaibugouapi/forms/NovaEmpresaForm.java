package br.dev.rodrigocury.uaibugouapi.forms;

import br.dev.rodrigocury.uaibugouapi.forms.customvalidators.EmailValidator;
import br.dev.rodrigocury.uaibugouapi.forms.customvalidators.PasswordValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovaEmpresaForm {

  @NotNull
  @NotEmpty
  @Length(min = 5, max = 50)
  private String empresaNome;

  @NotNull
  private Long categoriaEmpresaId;

  @NotNull
  @NotEmpty
  @Length(min = 3, max = 40)
  private String funcionarioNome;

  @NotNull
  @NotEmpty
  @Length(min = 3, max = 50)
  private String funcionarioSobrenome;

  @EmailValidator
  private String funcionarioEmail;

  @PasswordValidator
  private String senha;

  public NovaEmpresaForm() {
  }

  public String getEmpresaNome() {
    return empresaNome;
  }

  public void setEmpresaNome(String empresaNome) {
    this.empresaNome = empresaNome;
  }

  public Long getCategoriaEmpresaId() {
    return categoriaEmpresaId;
  }

  public void setCategoriaEmpresaId(Long categoriaEmpresaId) {
    this.categoriaEmpresaId = categoriaEmpresaId;
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

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
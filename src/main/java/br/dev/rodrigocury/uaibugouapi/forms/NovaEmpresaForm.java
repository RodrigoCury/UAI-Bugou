package br.dev.rodrigocury.uaibugouapi.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovaEmpresaForm {

  @NotNull
  @NotEmpty
  @Length(min = 5, max = 50)
  private String empresaNome;***********************************************************************************************************
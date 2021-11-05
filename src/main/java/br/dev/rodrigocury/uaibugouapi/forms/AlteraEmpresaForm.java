package br.dev.rodrigocury.uaibugouapi.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AlteraEmpresaForm {

  @NotNull
  @NotEmpty
  @Length(min = 5, max = 50)
  private String empresaNome;

  @NotNull
  private Long categoriaEmpresaId;

  public AlteraEmpresaForm() {
  }

  @Override
  public String toString() {
    return "AlteraEmpresaForm{" +
        "empresaNome='" + empresaNome + '\'' +
        ", categoriaEmpresaId=" + categoriaEmpresaId +
        '}';
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
}

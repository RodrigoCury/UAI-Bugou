package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;

public class EmpresaAlteradaDto {

  private final Long empresaId;

  private final String empresaNome;

  private final CategoriaDTO categoriaEmpresa;

  public EmpresaAlteradaDto(Empresa empresa) {
    this.empresaId = empresa.getEmpresaId();
    this.empresaNome = empresa.getEmpresaNome();
    this.categoriaEmpresa = new CategoriaDTO(empresa.getCategoriaEmpresa());
  }

  public Long getEmpresaId() {
    return empresaId;
  }

  public String getEmpresaNome() {
    return empresaNome;
  }

  public CategoriaDTO getCategoriaEmpresa() {
    return categoriaEmpresa;
  }
}

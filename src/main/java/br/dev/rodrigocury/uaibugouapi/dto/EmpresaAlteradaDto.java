package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;

public class EmpresaAlteradaDto {

  private final Long empresaId;

  private final String empresaNome;

  private final CategoriaEmpresaDTO categoriaEmpresa;

  public EmpresaAlteradaDto(Empresa empresa) {
    this.empresaId = empresa.getEmpresaId();
    this.empresaNome = empresa.getEmpresaNome();
    this.categoriaEmpresa = new CategoriaEmpresaDTO(empresa.getCategoriaEmpresa());
  }

  public Long getEmpresaId() {
    return empresaId;
  }

  public String getEmpresaNome() {
    return empresaNome;
  }

  public CategoriaEmpresaDTO getCategoriaEmpresa() {
    return categoriaEmpresa;
  }
}

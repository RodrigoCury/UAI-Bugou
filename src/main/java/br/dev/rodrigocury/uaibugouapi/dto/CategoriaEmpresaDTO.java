package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;

public class CategoriaEmpresaDTO {
  private final Long id;

  private final String categoriaNome;

  public CategoriaEmpresaDTO(CategoriaEmpresa categoriaEmpresa) {
    this.id = categoriaEmpresa.getId();
    this.categoriaNome = categoriaEmpresa.getCategoriaNome();
  }

  public Long getId() {
    return id;
  }

  public String getCategoriaNome() {
    return categoriaNome;
  }
}

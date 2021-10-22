package br.dev.rodrigocury.uaibugouapi.models.categorias;

import javax.persistence.*;

@MappedSuperclass
abstract class CategoriaSuper {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "categoria_id", nullable = false)
  private Long categoriaId;

  private String categoriaNome;

  public String getCategoriaNome() {
    return categoriaNome;
  }

  public void setCategoriaNome(String categoriaNome) {
    this.categoriaNome = categoriaNome;
  }

  public Long getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Long categoriaId) {
    this.categoriaId = categoriaId;
  }
}
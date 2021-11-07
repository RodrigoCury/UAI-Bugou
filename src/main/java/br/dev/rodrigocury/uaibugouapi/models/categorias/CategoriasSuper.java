package br.dev.rodrigocury.uaibugouapi.models.categorias;

import javax.persistence.*;

@MappedSuperclass
public class CategoriasSuper {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String categoriaNome;

  public CategoriasSuper() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategoriaNome() {
    return categoriaNome;
  }

  public void setCategoriaNome(String categoriaNome) {
    this.categoriaNome = categoriaNome;
  }
}

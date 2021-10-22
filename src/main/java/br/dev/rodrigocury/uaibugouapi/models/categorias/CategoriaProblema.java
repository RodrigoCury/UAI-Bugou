package br.dev.rodrigocury.uaibugouapi.models.categorias;

import br.dev.rodrigocury.uaibugouapi.models.empresa.Problema;

import javax.persistence.*;
import java.util.List;

@Table(name = "categoria_problema")
@Entity
public class CategoriaProblema extends CategoriaSuper{
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name= "categoria_problema_problema",
      joinColumns = @JoinColumn(name = "categoria_id"),
      inverseJoinColumns = @JoinColumn(name = "problema_id"))
  private List<Problema> problemas;

  public CategoriaProblema() {
  }

  public List<Problema> getProblemas() {
    return problemas;
  }

  public void setProblemas(List<Problema> problemas) {
    this.problemas = problemas;
  }
}
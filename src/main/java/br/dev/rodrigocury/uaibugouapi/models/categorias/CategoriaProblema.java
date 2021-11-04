package br.dev.rodrigocury.uaibugouapi.models.categorias;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Problema;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CategoriaProblema extends CategoriasSuper {

  @OneToMany(mappedBy = "categoriaProblemas")
  private List<Problema> empresas;

  public CategoriaProblema() {
  }

  public List<Problema> getEmpresas() {
    return empresas;
  }

  public void setEmpresas(List<Problema> empresas) {
    this.empresas = empresas;
  }
}

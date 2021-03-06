package br.dev.rodrigocury.uaibugouapi.models.categorias;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CategoriaEmpresa extends CategoriasSuper {

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaEmpresa")
  @JsonBackReference
  @JsonIgnore
  private List<Empresa> empresas;

  public CategoriaEmpresa() {
  }

  public List<Empresa> getEmpresas() {
    return empresas;
  }

  public void setEmpresas(List<Empresa> empresas) {
    this.empresas = empresas;
  }
}

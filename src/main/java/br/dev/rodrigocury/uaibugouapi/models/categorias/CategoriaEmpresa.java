package br.dev.rodrigocury.uaibugouapi.models.categorias;

import br.dev.rodrigocury.uaibugouapi.models.empresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.empresa.Problema;

import javax.persistence.*;
import java.util.List;

@Table(name = "categoria_empresa")
@Entity
public class CategoriaEmpresa extends CategoriaSuper{
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name= "categoria_empresa_empresa",
      joinColumns = @JoinColumn(name = "categoria_id"),
      inverseJoinColumns = @JoinColumn(name = "empresa_id"))
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
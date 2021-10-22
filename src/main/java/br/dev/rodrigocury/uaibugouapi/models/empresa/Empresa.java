package br.dev.rodrigocury.uaibugouapi.models.empresa;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import br.dev.rodrigocury.uaibugouapi.models.users.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.users.Funcionario;

import javax.persistence.*;
import java.util.List;

@Table(name = "empresa")
@Entity
public class Empresa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "empresa_id", nullable = false)
  private Long empresaId;

  private String empresaNome;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "empresas")
  private List<CategoriaEmpresa> categorias;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Funcionario> funcionarios;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Time> times;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Funcao> funcoes;

  public Empresa() {
  }

  public Long getEmpresaId() {
    return empresaId;
  }

  public void setEmpresaId(Long empresaId) {
    this.empresaId = empresaId;
  }

  public String getEmpresaNome() {
    return empresaNome;
  }

  public void setEmpresaNome(String empresaNome) {
    this.empresaNome = empresaNome;
  }

  public List<CategoriaEmpresa> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<CategoriaEmpresa> categorias) {
    this.categorias = categorias;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }

  public List<Time> getTimes() {
    return times;
  }

  public void setTimes(List<Time> times) {
    this.times = times;
  }

  public List<Funcao> getFuncoes() {
    return funcoes;
  }

  public void setFuncoes(List<Funcao> funcoes) {
    this.funcoes = funcoes;
  }
}
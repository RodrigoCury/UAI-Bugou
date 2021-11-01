package br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;

import javax.persistence.*;
import java.util.List;

@Entity
public class Empresa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long empresaId;

  @Column(nullable = false, length = 50)
  private String empresaNome;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "categoria_empresa_id")
  private CategoriaEmpresa categoriaEmpresa;

  @OneToMany(mappedBy = "empresa")
  private List<Funcionario> funcionarios;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
  private List<Projeto> projetos;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
  private List<Time> times;

  @OneToMany(mappedBy = "empresa")
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

  public CategoriaEmpresa getCategoriaEmpresa() {
    return categoriaEmpresa;
  }

  public void setCategoriaEmpresa(CategoriaEmpresa categoriaEmpresa) {
    this.categoriaEmpresa = categoriaEmpresa;
  }

  public List<Projeto> getProjetos() {
    return projetos;
  }

  public void setProjetos(List<Projeto> projetos) {
    this.projetos = projetos;
  }

  public List<Time> getTimes() {
    return times;
  }

  public void setTimes(List<Time> times) {
    this.times = times;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncoes(List<Funcao> funcoes) {
    this.funcoes = funcoes;
  }

  public List<Funcao> getFuncoes() {
    return funcoes;
  }

}

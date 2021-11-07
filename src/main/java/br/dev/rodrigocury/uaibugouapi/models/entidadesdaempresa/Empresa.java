package br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Empresa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long empresaId;

  @Column(nullable = false, length = 50)
  private String empresaNome;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "categoria_empresa_id")
  @JsonManagedReference
  private CategoriaEmpresa categoriaEmpresa;

  @OneToMany(mappedBy = "empresa")
  @JsonBackReference
  private Set<Funcionario> funcionarios;

  @JsonBackReference
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
  private Set<Projeto> projetos;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
  @JsonBackReference
  private Set<Time> times;

  @OneToMany(mappedBy = "empresa")
  @JsonBackReference
  private Set<Funcao> funcoes;

  public Empresa() {
  }

  public Empresa(String empresaNome, CategoriaEmpresa categoriaEmpresa) {
    this.empresaNome = empresaNome;
    this.categoriaEmpresa = categoriaEmpresa;
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

  @Transactional
  public Set<Projeto> getProjetos() {
    return projetos;
  }

  public void setProjetos(Set<Projeto> projetos) {
    this.projetos = projetos;
  }

  @Transactional
  public Set<Time> getTimes() {
    return times;
  }

  public void setTimes(Set<Time> times) {
    this.times = times;
  }

  public void setFuncionarios(Set<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }

  @Transactional
  public Set<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncoes(Set<Funcao> funcoes) {
    this.funcoes = funcoes;
  }

  @Transactional
  public Set<Funcao> getFuncoes() {
    return funcoes;
  }
}

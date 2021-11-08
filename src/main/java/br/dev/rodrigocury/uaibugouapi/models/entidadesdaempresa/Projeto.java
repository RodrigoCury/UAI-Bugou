package br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "projeto")
@Entity
public class Projeto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long projetoId;

  @Column(nullable = false)
  private String projetoNome;

  @ManyToOne
  private Funcionario funcionarioResponsavel;

  @OneToMany(mappedBy = "projeto")
  private List<Problema> problemas;

  @ManyToMany(mappedBy = "projetos")
  private Set<Time> times;

  @ManyToOne
  @JoinColumn(name = "empresa_id")
  @JsonManagedReference
  private Empresa empresa;

  public Projeto() {
  }

  public Projeto(Empresa empresa, Funcionario funcionarioResponsavel, String projetoNome) {
    this.empresa = empresa;
    this.funcionarioResponsavel = funcionarioResponsavel;
    this.projetoNome = projetoNome;
  }

  public Long getProjetoId() {
    return projetoId;
  }

  public void setProjetoId(Long projetoId) {
    this.projetoId = projetoId;
  }

  public String getProjetoNome() {
    return projetoNome;
  }

  public void setProjetoNome(String projetoNome) {
    this.projetoNome = projetoNome;
  }

  public Funcionario getFuncionarioResponsavel() {
    return funcionarioResponsavel;
  }

  public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
    this.funcionarioResponsavel = funcionarioResponsavel;
  }

  public List<Problema> getProblemas() {
    return problemas;
  }

  public void setProblemas(List<Problema> problemas) {
    this.problemas = problemas;
  }

  public Set<Time> getTimes() {
    return times;
  }

  public void setTimes(Set<Time> times) {
    this.times = times;
  }

  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }


}
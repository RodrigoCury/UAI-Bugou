package br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Time {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long timeId;

  @Column(nullable = false)
  private String timeNome;

  @OneToMany(mappedBy = "time")
  private List<Funcionario> funcionarios;

  @ManyToMany
  @JoinTable(
      name = "time_projeto",
      joinColumns = @JoinColumn(name = "time_id"),
      inverseJoinColumns = @JoinColumn(name = "projeto_id"))
  private Set<Projeto> projetos;

  @ManyToOne
  @JoinColumn(name = "empresa_empresa_id")
  private Empresa empresa;

  public Time() {
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public String getTimeNome() {
    return timeNome;
  }

  public void setTimeNome(String timeNome) {
    this.timeNome = timeNome;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }

  public Set<Projeto> getProjetos() {
    return projetos;
  }

  public void setProjetos(Set<Projeto> projetos) {
    this.projetos = projetos;
  }

  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }
}
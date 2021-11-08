package br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Time {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long timeId;

  @Column(nullable = false, name = "time_nome")
  private String timeNome;

  @OneToMany(mappedBy = "time")
  private Set<Funcionario> funcionarios;

  @ManyToMany(cascade = {CascadeType.MERGE})
  @JoinTable(
      name = "time_projeto",
      joinColumns = @JoinColumn(name = "time_id"),
      inverseJoinColumns = @JoinColumn(name = "projeto_id"))
  private Set<Projeto> projetos;

  @ManyToOne
  @JoinColumn(name = "empresa_empresa_id")
  @JsonManagedReference
  private Empresa empresa;

  public Time() {
  }

  public Time(String timeNome, Empresa empresa) {
    this.timeNome = timeNome;
    this.empresa = empresa;
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

  public Set<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(Set<Funcionario> funcionarios) {
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

  public void addProjeto(Projeto projeto){
    this.projetos.add(projeto);
  }
}
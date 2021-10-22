package br.dev.rodrigocury.uaibugouapi.models.empresa;

import br.dev.rodrigocury.uaibugouapi.models.users.Funcionario;

import javax.persistence.*;
import java.util.List;

@Table(name = "time")
@Entity
public class Time {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "time_id", nullable = false)
  private Long timeId;

  private String timeNome;

  private boolean ativo;

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "time_funcionario",
      joinColumns = @JoinColumn(name = "time_id"),
      inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
  private List<Funcionario> funcionarios;

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinTable(name = "time_projeto",
    joinColumns = @JoinColumn(name = "time_id"),
    inverseJoinColumns = @JoinColumn(name = "projeto_id"))
  private List<Projeto> projetos;


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

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }

  public List<Projeto> getProjetos() {
    return projetos;
  }

  public void setProjetos(List<Projeto> projetos) {
    this.projetos = projetos;
  }
}
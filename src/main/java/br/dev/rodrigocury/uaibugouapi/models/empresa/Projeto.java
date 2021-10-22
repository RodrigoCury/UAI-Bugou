package br.dev.rodrigocury.uaibugouapi.models.empresa;

import br.dev.rodrigocury.uaibugouapi.models.users.Funcionario;

import javax.persistence.*;
import java.util.List;

@Table(name = "projeto")
@Entity
public class Projeto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "projeto_id", nullable = false)
  private Long projetoId;

  private String projetoNome;

  @ManyToOne
  private Funcionario funcionarioResponsavel;

  @OneToMany
  private List<Problema> problemas;

  @ManyToMany(mappedBy = "projetos", fetch = FetchType.LAZY)
  private List<Time> times;

  public Projeto() {
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

  public List<Time> getTimes() {
    return times;
  }

  public void setTimes(List<Time> times) {
    this.times = times;
  }
}
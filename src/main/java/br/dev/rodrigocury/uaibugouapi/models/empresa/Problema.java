package br.dev.rodrigocury.uaibugouapi.models.empresa;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaProblema;
import br.dev.rodrigocury.uaibugouapi.models.enums.Severidade;
import br.dev.rodrigocury.uaibugouapi.models.enums.Status;
import br.dev.rodrigocury.uaibugouapi.models.users.Funcionario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "problema")
@Entity
public class Problema {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "problema_id", nullable = false)
  private Long problemaId;

  private String problemaNome;

  private String problemaDescricao;

  private LocalDateTime criadoEm = LocalDateTime.now();

  private LocalDateTime resolvidoEm;

  private LocalDate dataLimite;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Enumerated(EnumType.STRING)
  private Severidade severidade;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "problemas")
  private List<CategoriaProblema> categoriaProblemas;

  @ManyToOne(fetch = FetchType.EAGER)
  private Funcionario funcionarioResponsavel;

  @ManyToOne(fetch = FetchType.EAGER)
  private Funcionario criadoPor;

  public Problema() {
  }

  public Long getProblemaId() {
    return problemaId;
  }

  public void setProblemaId(Long problemaId) {
    this.problemaId = problemaId;
  }

  public String getProblemaNome() {
    return problemaNome;
  }

  public void setProblemaNome(String problemaNome) {
    this.problemaNome = problemaNome;
  }

  public String getProblemaDescricao() {
    return problemaDescricao;
  }

  public void setProblemaDescricao(String problemaDescricao) {
    this.problemaDescricao = problemaDescricao;
  }

  public LocalDateTime getCriadoEm() {
    return criadoEm;
  }

  public void setCriadoEm(LocalDateTime criadoEm) {
    this.criadoEm = criadoEm;
  }

  public LocalDateTime getResolvidoEm() {
    return resolvidoEm;
  }

  public void setResolvidoEm(LocalDateTime resolvidoEm) {
    this.resolvidoEm = resolvidoEm;
  }

  public LocalDate getDataLimite() {
    return dataLimite;
  }

  public void setDataLimite(LocalDate dataLimite) {
    this.dataLimite = dataLimite;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Severidade getSeveridade() {
    return severidade;
  }

  public void setSeveridade(Severidade severidade) {
    this.severidade = severidade;
  }

  public List<CategoriaProblema> getCategoriaProblemas() {
    return categoriaProblemas;
  }

  public void setCategoriaProblemas(List<CategoriaProblema> categoriaProblemas) {
    this.categoriaProblemas = categoriaProblemas;
  }

  public Funcionario getFuncionarioResponsavel() {
    return funcionarioResponsavel;
  }

  public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
    this.funcionarioResponsavel = funcionarioResponsavel;
  }

  public Funcionario getCriadoPor() {
    return criadoPor;
  }

  public void setCriadoPor(Funcionario criadoPor) {
    this.criadoPor = criadoPor;
  }
}
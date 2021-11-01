package br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaProblemas;
import br.dev.rodrigocury.uaibugouapi.models.enums.Severidade;
import br.dev.rodrigocury.uaibugouapi.models.enums.Status;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "problema")
@Entity
public class Problema {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "categoria_problemas_id")
  private CategoriaProblemas categoriaProblemas;

  @Column(nullable = false)
  private String problemaNome;
  @Column(nullable = false, length = 1000)
  private String problemaDescricao;

  @Column(nullable = false)
  private LocalDateTime criadoEm = LocalDateTime.now();
  private LocalDate dataLimite;
  private LocalDateTime resolvidoEm;

  @ManyToOne
  @JoinColumn(name = "funcionario_criador_id", updatable = false)
  private Funcionario criadoPor;

  @ManyToOne
  @JoinColumn(name = "funcionario_responsavel_id")
  private Funcionario funcionarioResponsavel;

  @Enumerated(EnumType.STRING)
  private Severidade severidade;
  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToOne
  @JoinColumn(name = "projeto_projeto_id")
  private Projeto projeto;

  public Problema() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CategoriaProblemas getCategoriaProblemas() {
    return categoriaProblemas;
  }

  public void setCategoriaProblemas(CategoriaProblemas categoriaProblemas) {
    this.categoriaProblemas = categoriaProblemas;
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

  public LocalDate getDataLimite() {
    return dataLimite;
  }

  public void setDataLimite(LocalDate dataLimite) {
    this.dataLimite = dataLimite;
  }

  public LocalDateTime getResolvidoEm() {
    return resolvidoEm;
  }

  public void setResolvidoEm(LocalDateTime resolvidoEm) {
    this.resolvidoEm = resolvidoEm;
  }

  public Funcionario getCriadoPor() {
    return criadoPor;
  }

  public void setCriadoPor(Funcionario criadoPor) {
    this.criadoPor = criadoPor;
  }

  public Funcionario getFuncionarioResponsavel() {
    return funcionarioResponsavel;
  }

  public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
    this.funcionarioResponsavel = funcionarioResponsavel;
  }

  public Severidade getSeveridade() {
    return severidade;
  }

  public void setSeveridade(Severidade severidade) {
    this.severidade = severidade;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Projeto getProjeto() {
    return projeto;
  }

  public void setProjeto(Projeto projeto) {
    this.projeto = projeto;
  }


}
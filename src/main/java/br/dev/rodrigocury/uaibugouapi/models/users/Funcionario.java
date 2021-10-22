package br.dev.rodrigocury.uaibugouapi.models.users;

import br.dev.rodrigocury.uaibugouapi.models.empresa.Time;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "funcionario")
@Entity
public class Funcionario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "funcionario_id", nullable = false)
  private Long funcionarioId;

  @ManyToMany(
      fetch = FetchType.LAZY,
      mappedBy = "funcionarios",
      cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
  private List<Funcao> funcoes;

  @ManyToMany(mappedBy = "funcionarios", fetch = FetchType.LAZY)
  private List<Time> times;

  private String funcionarioNome;
  private String funcionarioSobrenome;
  private String funcionarioEmail;
  private String senha;
  private boolean ativo;
  private boolean tokenExpirado;
  private LocalDateTime dataCriacao = LocalDateTime.now();

  public Funcionario() {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.funcoes;
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    return this.funcionarioEmail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.ativo;
  }

  public List<Time> getTimes() {
    return times;
  }

  public void setTimes(List<Time> times) {
    this.times = times;
  }

  public Long getFuncionarioId() {
    return funcionarioId;
  }

  public void setFuncionarioId(Long funcionarioId) {
    this.funcionarioId = funcionarioId;
  }

  @Transactional
  public List<Funcao> getFuncoes() {
    return funcoes;
  }

  public void setFuncoes(List<Funcao> funcoes) {
    this.funcoes = funcoes;
  }

  public String getFuncionarioNome() {
    return funcionarioNome;
  }

  public void setFuncionarioNome(String funcionarioNome) {
    this.funcionarioNome = funcionarioNome;
  }

  public String getFuncionarioSobrenome() {
    return funcionarioSobrenome;
  }

  public void setFuncionarioSobrenome(String funcionarioSobrenome) {
    this.funcionarioSobrenome = funcionarioSobrenome;
  }

  public String getFuncionarioEmail() {
    return funcionarioEmail;
  }

  public void setFuncionarioEmail(String funcionarioEmail) {
    this.funcionarioEmail = funcionarioEmail;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public boolean isTokenExpirado() {
    return tokenExpirado;
  }

  public void setTokenExpirado(boolean tokenExpirado) {
    this.tokenExpirado = tokenExpirado;
  }

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }
}
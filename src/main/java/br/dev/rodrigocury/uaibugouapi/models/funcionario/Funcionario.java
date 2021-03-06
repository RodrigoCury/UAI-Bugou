package br.dev.rodrigocury.uaibugouapi.models.funcionario;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table(name = "funcionario")
@Entity
public class Funcionario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long funcionarioId;

  @ManyToOne
  @JoinColumn(name = "empresa_id")
  @JsonManagedReference
  private Empresa empresa;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "funcao_id", nullable = false)
  private Funcao funcao;

  @Column(nullable = false, length = 40)
  private String funcionarioNome;

  @Column(nullable = false, length = 50)
  private String funcionarioSobrenome;

  @Column(nullable = false, length = 50, unique = true)
  private String funcionarioEmail;

  @Column(nullable = false)
  private String senha;

  @Column(nullable = false)
  private boolean ativo = true;

  @Column(updatable = false)
  private LocalDateTime dataCriacao = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "time_id")
  private Time time;

  public Funcionario() {
  }

  public Funcionario(Empresa empresa, Funcao funcao, String funcionarioNome, String funcionarioSobrenome, String funcionarioEmail, String senha) {
    this.empresa = empresa;
    this.funcao = funcao;
    this.funcionarioNome = funcionarioNome;
    this.funcionarioSobrenome = funcionarioSobrenome;
    this.funcionarioEmail = funcionarioEmail;
    this.senha = senha;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<Funcao> authorities = new HashSet<>();
    authorities.add(this.funcao);
    return authorities;
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

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public Long getFuncionarioId() {
    return funcionarioId;
  }

  public void setFuncionarioId(Long funcionarioId) {
    this.funcionarioId = funcionarioId;
  }

  @Transactional
  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }

  @Transactional
  public Funcao getFuncao() {
    return funcao;
  }

  public void setFuncao(Funcao funcao) {
    this.funcao = funcao;
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

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDateTime dataCricao) {
    this.dataCriacao = dataCricao;
  }
}
package br.dev.rodrigocury.uaibugouapi.models.funcionario;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "funcionario")
@Entity
public class Funcionario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long funcionarioId;

  @ManyToOne
  @JoinColumn(name = "empresa_id")
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
  private LocalDateTime dataCricao = LocalDateTime.now();

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
    return null;
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
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
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

  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }

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

  public LocalDateTime getDataCricao() {
    return dataCricao;
  }

  public void setDataCricao(LocalDateTime dataCricao) {
    this.dataCricao = dataCricao;
  }
}
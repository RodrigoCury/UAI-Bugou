package br.dev.rodrigocury.uaibugouapi.models.users;

import br.dev.rodrigocury.uaibugouapi.models.empresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.empresa.Time;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String sobrenome;
  private String email;
  private String senha;
  private Boolean enabled;
  private Boolean tokenExpired;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Empresa empresa;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "id")
  )
  private List<Role> roles;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Time time;

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }

  public List<Role> getRole() {
    return roles;
  }

  public void setRole(List<Role> roles) {
    this.roles = roles;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getTokenExpired() {
    return tokenExpired;
  }

  public void setTokenExpired(Boolean tokenExpired) {
    this.tokenExpired = tokenExpired;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}

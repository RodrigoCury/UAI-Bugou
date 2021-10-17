package br.dev.rodrigocury.uaibugouapi.models.users;

import javax.persistence.*;
import java.util.List;

@Entity
public class Privileges {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "privileges")
  private List<Role> roles;

  public Privileges() {
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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}

package br.dev.rodrigocury.uaibugouapi.models.users;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(cascade = CascadeType.DETACH)
  @JoinTable(
    name = "roles_privileges",
    joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
  )
  private List<Privileges> privileges;

  @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "roles")
  private List<User> users;

  private String nome;

  public Role() {
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

  public List<Privileges> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(List<Privileges> privileges) {
    this.privileges = privileges;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}

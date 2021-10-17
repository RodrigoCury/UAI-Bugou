package br.dev.rodrigocury.uaibugouapi.models.empresa;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import br.dev.rodrigocury.uaibugouapi.models.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Empresa {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @ManyToOne(cascade = CascadeType.ALL)
  private CategoriaEmpresa categoria;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Time> times = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<User> funcionario = new ArrayList<>();

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

  public List<Time> getTimes() {
    return times;
  }

  public void setTimes(List<Time> times) {
    this.times = times;
  }

  public List<User> getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(List<User> funcionario) {
    this.funcionario = funcionario;
  }

  public CategoriaEmpresa getCategoria() {
    return categoria;
  }

  public void setCategoria(CategoriaEmpresa categoria) {
    this.categoria = categoria;
  }
}

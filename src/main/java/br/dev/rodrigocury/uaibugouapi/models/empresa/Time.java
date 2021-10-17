package br.dev.rodrigocury.uaibugouapi.models.empresa;

import javax.persistence.*;
import java.util.List;

@Entity
public class Time {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @ManyToMany(mappedBy = "times")
  private List<Projetos> projetos;

  public Time() {
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
}

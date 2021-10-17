package br.dev.rodrigocury.uaibugouapi.models.empresa;

import br.dev.rodrigocury.uaibugouapi.models.issues.Bug;
import br.dev.rodrigocury.uaibugouapi.models.issues.Problema;
import br.dev.rodrigocury.uaibugouapi.models.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Projetos {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @ManyToOne(cascade = CascadeType.DETACH)
  private User funcionarioResponsavel;

  @ManyToMany
  @JoinTable(name = "projetos_times",
    joinColumns = @JoinColumn(name =  "projeto_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name =  "time_id", referencedColumnName = "id")
  )
  private List<Time> times;

  @OneToMany
  private List<Bug> bugs;

  @OneToMany
  private List<Problema> problemas;
}

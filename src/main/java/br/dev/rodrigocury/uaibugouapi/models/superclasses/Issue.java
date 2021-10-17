package br.dev.rodrigocury.uaibugouapi.models.superclasses;

import br.dev.rodrigocury.uaibugouapi.models.users.User;
import br.dev.rodrigocury.uaibugouapi.models.enums.Severidade;
import br.dev.rodrigocury.uaibugouapi.models.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(value = EnumType.STRING)
  private Status status;

  @Enumerated(value = EnumType.STRING)
  private Severidade severidade;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private User funcionarioResponsavel;

  private String descricao;
  private LocalDateTime criadoEm = LocalDateTime.now();
  private LocalDateTime resolvidoEm;
  private LocalDate dataLimite;
}

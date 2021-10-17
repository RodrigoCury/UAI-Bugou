package br.dev.rodrigocury.uaibugouapi.models.superclasses;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Categoria {

  @Id
  private String nome;

}

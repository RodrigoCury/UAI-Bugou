package br.dev.rodrigocury.uaibugouapi.models.issues;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaProblema;
import br.dev.rodrigocury.uaibugouapi.models.superclasses.Issue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Problema extends Issue {

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private CategoriaProblema categoria;
}

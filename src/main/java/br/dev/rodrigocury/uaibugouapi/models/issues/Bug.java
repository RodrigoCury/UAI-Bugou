package br.dev.rodrigocury.uaibugouapi.models.issues;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaBug;
import br.dev.rodrigocury.uaibugouapi.models.superclasses.Issue;

import javax.persistence.*;

@Entity
public class Bug extends Issue {

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private CategoriaBug categoria;
}

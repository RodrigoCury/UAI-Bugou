package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.dto.CategoriaDTO;
import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaProblema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CategoriaProblemaRepository extends JpaRepository<CategoriaProblema, Long> {

  @Query("SELECT new br.dev.rodrigocury.uaibugouapi.dto.CategoriaDTO(CP) FROM CategoriaProblema CP " +
      "ORDER BY CP.categoriaNome ASC ")
  Set<CategoriaDTO> findAllDto();
}

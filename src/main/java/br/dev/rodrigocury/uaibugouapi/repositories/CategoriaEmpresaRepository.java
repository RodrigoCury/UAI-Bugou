package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.dto.CategoriaDTO;
import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CategoriaEmpresaRepository extends JpaRepository<CategoriaEmpresa, Long> {

  @Query("SELECT new br.dev.rodrigocury.uaibugouapi.dto.CategoriaDTO(CE) FROM CategoriaEmpresa CE " +
      "ORDER BY CE.categoriaNome ASC ")
  Set<CategoriaDTO> findAllDto();
}

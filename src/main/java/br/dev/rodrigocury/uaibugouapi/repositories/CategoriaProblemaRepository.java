package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaProblema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoriaProblemaRepository extends JpaRepository<CategoriaProblema, Long>{
}
package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEmpresaRepository extends JpaRepository<CategoriaEmpresa, Long> {
}
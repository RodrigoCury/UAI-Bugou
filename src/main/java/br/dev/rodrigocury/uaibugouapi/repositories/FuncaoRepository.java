package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

  @Query("select fn from Funcao fn where fn.empresa.empresaId = :empresaId")
  Page<Funcao> findByEmpresaId(@Param("empresaId") Long empresaId, Pageable pageable);

  Optional<Funcao> findFuncaoByFuncaoIdAndEmpresa_EmpresaId(Long id, Long empresaId);
}

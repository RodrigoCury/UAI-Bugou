package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long> {

  Optional<Funcionario> findByFuncionarioEmail(String email);

  @Query("SELECT f from Funcionario f " +
      "inner join f.empresa e where e.empresaId = :id")
  Page<Funcionario> findByEmpresaId(@Param("id") Long empresaId, Pageable pageable);

  @Query("SELECT f from Funcionario f " +
      "inner join f.empresa e where e.empresaId = :empresa_id and f.funcionarioId = :funcionario_id")
  Optional<Funcionario> findByFuncionarioIdAndEmpresaId(
      @Param("funcionario_id") Long funcionarioId,
      @Param("empresa_id") Long empresaId
  );

}
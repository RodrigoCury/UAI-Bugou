package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

  Optional<Funcionario> findByFuncionarioEmail(String email);

  @Query("SELECT f from Funcionario f " +
      "inner join fetch f.empresa e where e.empresaId = :id")
  List<Funcionario> findByEmpresaId(@Param("id") Long empresaId);

  @Query("SELECT f from Funcionario f " +
      "inner join fetch f.empresa e where e.empresaId = :empresa_id and f.funcionarioId = :funcionario_id")
  Optional<Funcionario> findByFuncionarioIdAndEmpresaId(
      @Param("funcionario_id") Long funcionarioId,
      @Param("empresa_id") Long empresaId
  );

}
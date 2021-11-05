package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

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

  @Query("SELECT f FROM Funcionario f " +
      "INNER JOIN f.empresa e " +
      "where e.empresaId = :empresaId " +
      "and f.funcao.funcaoId = :funcaoId")
  Page<Funcionario> findByEmpresaAndFuncaoId(@Param("empresaId") Long empresaId, @Param("funcaoId") Long funcaoId, Pageable pageable);

  @Query("SELECT f FROM Funcionario f " +
      "INNER JOIN f.empresa e " +
      "where e.empresaId = :empresaId " +
      "and f.funcao.privilegiosDeAcesso = :privilegios")
  Page<Funcionario> findByEmpresaAndPrivilegios(@Param("empresaId") Long empresaId, @Param("privilegios") PrivilegiosDeAcesso p, Pageable pageable);
}
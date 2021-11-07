package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TimeRepository extends PagingAndSortingRepository<Time, Long> {
  @Query("SELECT T FROM Time T " +
      "WHERE T.empresa.empresaId = :empresaId")
  Page<Time> findByEmpresaId(@Param("empresaId") Long empresaId, Pageable pageable);

  @Override
  @Query("SELECT T FROM Time T " +
      "JOIN FETCH T.funcionarios f " +
      "WHERE T.timeId = :id")
  Optional<Time> findById(@Param("id") Long id);

  Optional<Time> findByTimeIdAndEmpresa_EmpresaId(Long timeId, Long empresaId);
}

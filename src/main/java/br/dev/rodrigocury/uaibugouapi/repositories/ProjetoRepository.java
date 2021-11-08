package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProjetoRepository extends PagingAndSortingRepository<Projeto, Long> {

  @Query("SELECT distinct new br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto(P) FROM Projeto P " +
      "INNER JOIN P.empresa E " +
      "LEFT JOIN P.times T " +
      "LEFT JOIN P.problemas PR " +
      "WHERE E.empresaId = :empresaId")
  Page<ProjetoDto> findAllByEmpresa_EmpresaId(Long empresaId, Pageable pageable);

  @Query("SELECT new br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto(P) FROM Projeto P " +
      "INNER JOIN P.empresa E " +
      "LEFT JOIN P.times T " +
      "LEFT JOIN P.problemas PR " +
      "WHERE E.empresaId = :empresaId AND P.projetoId = :projetoId")
  Optional<ProjetoDto> findByEmpresa_EmpresaIdAndProjetoIdDto(Long empresaId, Long projetoId);

  Optional<Projeto> findByEmpresa_EmpresaIdAndProjetoId(Long empresaId, Long projetoId);

  @Query("SELECT distinct new br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto(P) FROM Projeto P " +
      "INNER JOIN P.funcionarioResponsavel F " +
      "LEFT JOIN P.times T " +
      "LEFT JOIN P.problemas PR " +
      "WHERE F.funcionarioId = :funcionarioId")
  Page<ProjetoDto> findAllByFuncionarioResponsavelId(Long funcionarioId, Pageable pageable);

  @Query("SELECT distinct new br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto(P) FROM Projeto P " +
      "LEFT JOIN P.times T " +
      "LEFT JOIN P.problemas PR " +
      "WHERE T.timeId = :timeId")
  Page<ProjetoDto> findAllByTimes_TimeId(Long timeId, Pageable pageable);

  @Query("SELECT new br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto(P) FROM Projeto P " +
      "INNER JOIN P.funcionarioResponsavel F " +
      "LEFT JOIN P.times T " +
      "LEFT JOIN P.problemas PR " +
      "WHERE F.funcionarioId = :funcionarioId OR T.timeId = :timeId AND P.projetoId = :projetoId")
  Optional<ProjetoDto> findByEmpresa_EmpresaIdAndProjetoIdOrFuncionarioResponsavelId(Long funcionarioId, Long timeId, Long projetoId);

  @Query("SELECT distinct new br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto(P) FROM Projeto P " +
      "LEFT JOIN P.times T " +
      "LEFT JOIN P.problemas PR " +
      "WHERE T.timeId = :timeId AND P.projetoId = :projetoId")
  Optional<ProjetoDto> findByTimeIdAndProjetoId(Long timeId, Long projetoId);
}

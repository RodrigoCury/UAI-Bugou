package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

  @Query("select e from Empresa e " +
      "left join fetch e.funcionarios f " +
      "left join fetch e.projetos p " +
      "left join fetch e.times t " +
      "left join fetch e.funcoes fn " +
      "where e.empresaId = :id")
  Optional<Empresa> findByIdLazy(@Param("id") Long id);

  @Transactional
  @Modifying
  @Query("update Funcionario f set f.ativo = false where f.empresa.empresaId = :empresaId")
  void desabilitaFuncionarios(@Param("empresaId") Long empresaid);
}
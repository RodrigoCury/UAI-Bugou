package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
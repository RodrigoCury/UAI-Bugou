package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.users.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
}
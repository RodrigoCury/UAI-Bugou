package br.dev.rodrigocury.uaibugouapi.repositories;

import br.dev.rodrigocury.uaibugouapi.models.empresa.Problema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemaRepository extends JpaRepository<Problema, Long> {
}
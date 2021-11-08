package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.dto.CategoriaDTO;
import br.dev.rodrigocury.uaibugouapi.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/categorias")
@EnableScheduling
public class CategoriasController {

  private final CategoriaService categoriaService;

  @Autowired
  public CategoriasController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @GetMapping("/empresa")
  @Cacheable(value = "categoriasEmpresa")
  public ResponseEntity<Set<CategoriaDTO>> listaCategoriasEmpresa(){
    Set<CategoriaDTO> dtos = categoriaService.listaCategoriasEmpresa();

    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/problema")
  @Cacheable(value = "categoriasProblemas")
  public ResponseEntity<Set<CategoriaDTO>> listaCategoriasProblema(){
    Set<CategoriaDTO> dtos = categoriaService.listaCategoriasProblema();

    return ResponseEntity.ok(dtos);
  }

  @CacheEvict(value = {"categoriasEmpresa, categoriasProblemas"})
  @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.DAYS)
  public void reportCacheEvict() {
    System.out.println("Limpando Cache empresas " + new Date().toString());
  }
}

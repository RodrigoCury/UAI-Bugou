package br.dev.rodrigocury.uaibugouapi.services;

import br.dev.rodrigocury.uaibugouapi.dto.CategoriaDTO;
import br.dev.rodrigocury.uaibugouapi.repositories.CategoriaEmpresaRepository;
import br.dev.rodrigocury.uaibugouapi.repositories.CategoriaProblemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoriaService {

  private final CategoriaEmpresaRepository categoriaEmpresaRepository;
  private final CategoriaProblemaRepository categoriaProblemaRepository;

  @Autowired
  public CategoriaService(CategoriaEmpresaRepository categoriaEmpresaRepository, CategoriaProblemaRepository categoriaProblemaRepository) {
    this.categoriaEmpresaRepository = categoriaEmpresaRepository;
    this.categoriaProblemaRepository = categoriaProblemaRepository;
  }

  public Set<CategoriaDTO> listaCategoriasEmpresa() {
    return categoriaEmpresaRepository.findAllDto();
  }

  public Set<CategoriaDTO> listaCategoriasProblema() {
    return categoriaProblemaRepository.findAllDto();
  }
}

package br.dev.rodrigocury.uaibugouapi.services;

import br.dev.rodrigocury.uaibugouapi.dto.NovaEmpresaDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovaEmpresaForm;
import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.CategoriaEmpresaRepository;
import br.dev.rodrigocury.uaibugouapi.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class EmpresaService {

  // Repositórios
  private final EmpresaRepository empresaRepository;
  private final CategoriaEmpresaRepository categoriaEmpresaRepository;

  //Services
  private final FuncionarioService funcionarioService;

  @Autowired
  public EmpresaService(EmpresaRepository empresaRepository, CategoriaEmpresaRepository categoriaEmpresaRepository, FuncionarioService funcionarioService) {
    this.empresaRepository = empresaRepository;
    this.funcionarioService = funcionarioService;
    this.categoriaEmpresaRepository = categoriaEmpresaRepository;
  }

  @Transactional
  public NovaEmpresaDto criaEmpresa(NovaEmpresaForm form){
    // Encontra Categoria Empresa no DB
    Optional<CategoriaEmpresa> catEmpresa = categoriaEmpresaRepository.findById(form.getCategoriaEmpresaId());

    // Checa se Categoria existe no DB
    if (catEmpresa.isEmpty()){
      throw new EntityNotFoundException("ID Categoria Empresa não encontrado no DB");
    }

    // Cria empresa
    Empresa empresa = new Empresa(form.getEmpresaNome(), catEmpresa.get());
    empresaRepository.save(empresa);

    // Cria Administrador
    Funcionario funcionario = funcionarioService.criaAdministrador(empresa, form);

    return new NovaEmpresaDto(empresa, funcionario);
  }
}

package br.dev.rodrigocury.uaibugouapi.services;

import br.dev.rodrigocury.uaibugouapi.dto.FuncaoDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovaFuncaoForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class FuncaoService {

  private final FuncaoRepository funcaoRepository;
  private final FuncionarioService funcionarioService;

  @Autowired
  public FuncaoService(FuncaoRepository funcaoRepository, FuncionarioService funcionarioService) {
    this.funcaoRepository = funcaoRepository;
    this.funcionarioService = funcionarioService;
  }


  @Transactional
  public FuncaoDto criaFuncao(Empresa empresa, NovaFuncaoForm form) {
    Funcao funcaoNova = new Funcao();
    funcaoNova.setEmpresa(empresa);
    funcaoNova.setFuncaoNome(form.getFuncaoNome());
    funcaoNova.setPrivilegiosDeAcesso(form.getPrivilegiosDeAcesso());

    funcaoRepository.save(funcaoNova);

    return new FuncaoDto(funcaoNova);
  }

  public Page<FuncaoDto> listaFuncoes(Empresa empresa, Pageable pageable) {
    Page<Funcao> funcoes = funcaoRepository.findByEmpresaId(empresa.getEmpresaId(), pageable);

    return funcoes.map(FuncaoDto::new);
  }

  public FuncaoDto retornaFuncao(Long id, Empresa empresa) {
    Optional<Funcao> funcaoOptional = funcaoRepository.findById(id);

    Funcao funcao = checkFuncao(empresa, funcaoOptional);

    return new FuncaoDto(funcao);
  }

  @Transactional
  public FuncaoDto alteraFuncao(Long id, Empresa empresa, NovaFuncaoForm form) {
    Optional<Funcao> funcaoOptional = funcaoRepository.findById(id);
    Funcao funcao = checkFuncao(empresa, funcaoOptional);

    funcao.setFuncaoNome(form.getFuncaoNome());
    funcao.setPrivilegiosDeAcesso(form.getPrivilegiosDeAcesso());

    return new FuncaoDto(funcao);
  }

  private Funcao checkFuncao(Empresa empresa, Optional<Funcao> funcaoOptional) {
    if(funcaoOptional.isEmpty()){
      throw new EntityNotFoundException("Funcao Não encontrada no BD");
    } else if (!funcaoOptional.get().getEmpresa().getEmpresaId().equals(empresa.getEmpresaId())){
      throw new AccessDeniedException("Não é permitido");
    }

    return funcaoOptional.get();
  }
  @Transactional
  public void deletaFuncao(Long id, boolean forceDelete, Empresa empresa) {

    Optional<Funcao> funcaoOptional = funcaoRepository.findFuncaoByFuncaoIdAndEmpresa_EmpresaId(id, empresa.getEmpresaId());

    if (funcaoOptional.isEmpty()){
      throw new EntityNotFoundException("Funcao não Encontrada");
    }

    Funcao funcao = funcaoOptional.get();
    Set<Funcionario> funcionarios = funcionarioService.findAllByFuncao_FuncaoId(funcao.getId());

    if(funcionarios.isEmpty()){
      funcaoRepository.delete(funcao);
    } else if (forceDelete) {
      funcionarioService.apagaSetDeFuncionario(funcionarios);
      funcaoRepository.delete(funcao);
    } else {
      throw new IllegalArgumentException("Função contém funcionários, para remove-los adicione forceDelete=true nos Query Params");
    }
  }
}



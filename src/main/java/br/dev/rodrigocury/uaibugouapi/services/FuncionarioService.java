package br.dev.rodrigocury.uaibugouapi.services;

import br.dev.rodrigocury.uaibugouapi.dto.FuncionarioDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovaEmpresaForm;
import br.dev.rodrigocury.uaibugouapi.forms.NovoFuncionarioForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncaoRepository;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncionarioRepository;
import br.dev.rodrigocury.uaibugouapi.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class FuncionarioService {

  // Repositórios
  private final FuncaoRepository funcaoRepository;
  private final FuncionarioRepository funcionarioRepository;

  @Autowired
  public FuncionarioService(FuncaoRepository funcaoRepository, FuncionarioRepository funcionarioRepository) {
    this.funcaoRepository = funcaoRepository;
    this.funcionarioRepository = funcionarioRepository;
  }

  @Transactional
  public Funcionario criaAdministrador(Empresa empresa, NovaEmpresaForm form) {
    // Cria funcao de Administrador para a empresa
    Funcao admFuncao = new Funcao(empresa, PrivilegiosDeAcesso.ADMINISTRADOR, "Administrador");
    funcaoRepository.save(admFuncao);

    // Criptografa a Senha;
    String senhaHash = PasswordEncoder.encodePassword(form.getSenha());

    Funcionario adm = new Funcionario(
        empresa,
        admFuncao,
        form.getFuncionarioNome(),
        form.getFuncionarioSobrenome(),
        form.getFuncionarioEmail(),
        senhaHash);

    funcionarioRepository.save(adm);


    return adm;
  }

  @Transactional
  public Funcionario criaFuncionario(Funcionario logado, NovoFuncionarioForm form) {
    Optional<Funcao> funcaoById = funcaoRepository.findById(form.getFuncaoId());


    if (funcaoById.isEmpty()){
      throw new EntityNotFoundException("Funcao não encontrada no DB");
    }
    if (!logado.getEmpresa().getEmpresaId().equals(funcaoById.get().getEmpresa().getEmpresaId())){
      throw new IllegalArgumentException("Função Fornecida não pertence a empresa do funcionário");
    }

    Funcionario funcionario = new Funcionario(
        logado.getEmpresa(),
        funcaoById.get(),
        form.getFuncionarioNome(),
        form.getFuncionarioSobrenome(),
        form.getFuncionarioEmail(),
        PasswordEncoder.encodePassword(form.getSenha())
    );

    funcionarioRepository.save(funcionario);

    return funcionario;
  }

  @Transactional
  public Page<FuncionarioDto> encontraFuncionariosDaEmpresa(
      Empresa empresa,
      Pageable pageable) {
    Page<Funcionario> funcionarios = funcionarioRepository.findByEmpresaId(empresa.getEmpresaId(), pageable);
    return funcionarios.map(FuncionarioDto::toFuncionarioDto);
  }

  @Transactional
  public Funcionario encontraFuncionarioPorId(Funcionario autenticado, Long id){
    Long empresaId = autenticado.getEmpresa().getEmpresaId();

    Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

    if (funcionario.isEmpty()){
      throw new EntityNotFoundException("Funcionario não Encontrado");
    }

    if(!funcionario.get().getEmpresa().getEmpresaId().equals(empresaId)){
      throw new AccessDeniedException("Você não tem accesso a esse funcionario");
    }

    return funcionario.get();
  }

  @Transactional
  public Funcionario alternarAtivo(Funcionario autenticado, Long id) {
    Funcionario funcionario = this.encontraFuncionarioPorId(autenticado, id);

    funcionario.setAtivo(!funcionario.isAtivo());

    return funcionario;
  }
}

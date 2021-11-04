package br.dev.rodrigocury.uaibugouapi.services;

import br.dev.rodrigocury.uaibugouapi.forms.NovaEmpresaForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcao;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncaoRepository;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncionarioRepository;
import br.dev.rodrigocury.uaibugouapi.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}

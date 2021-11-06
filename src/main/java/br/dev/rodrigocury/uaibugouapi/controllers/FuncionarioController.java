package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.dto.FuncionarioDto;
import br.dev.rodrigocury.uaibugouapi.dto.NovoFuncionarioDto;
import br.dev.rodrigocury.uaibugouapi.forms.AlteraFuncionarioForm;
import br.dev.rodrigocury.uaibugouapi.forms.NovoFuncionarioForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.services.FuncionarioService;
import br.dev.rodrigocury.uaibugouapi.utils.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

  private final FuncionarioService funcionarioService;

  @Autowired
  public FuncionarioController(FuncionarioService funcionarioService) {
    this.funcionarioService = funcionarioService;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<NovoFuncionarioDto> criaFuncionario(Authentication auth, @RequestBody @Valid NovoFuncionarioForm form,  UriComponentsBuilder builder){
    Funcionario autenticado = (Funcionario) auth.getPrincipal();

    Funcionario funcionario = funcionarioService.criaFuncionario(autenticado, form);

    NovoFuncionarioDto dto = new NovoFuncionarioDto(
        funcionario.getFuncionarioId(),
        funcionario.getFuncionarioNome(),
        funcionario.getFuncionarioSobrenome(),
        funcionario.getFuncionarioEmail()
    );

    URI uri =builder.path("/funcionario/{id}").buildAndExpand(dto.getFuncionarioId()).toUri();

    return ResponseEntity.created(uri).body(dto);
  }

  @GetMapping
  @Transactional
  public ResponseEntity<Page<FuncionarioDto>> listaFuncionarioDaEmpresa(
      Authentication auth,
      @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable pageable,
      @RequestParam(required = false) Long funcaoId,
      @RequestParam(required = false) PrivilegiosDeAcesso privilegio
  ){
    Funcionario autenticado = AuthenticationHelper.getAutenticado(auth);
    Empresa empresa = autenticado.getEmpresa();

    Page<FuncionarioDto> funcionarios = funcionarioService.encontraFuncionariosDaEmpresa(empresa, pageable, funcaoId, privilegio);

    return ResponseEntity.ok(funcionarios);
  }

  @Transactional
  @GetMapping("/{id}")
  public ResponseEntity<FuncionarioDto> encontraFuncionarioDaEmpresa(Authentication auth, @PathVariable("id") Long id){
    Funcionario logado = AuthenticationHelper.getAutenticado(auth);

    if (!logado.getFuncionarioId().equals(id) && !logado.getFuncao().getPrivilegiosDeAcesso().equals(PrivilegiosDeAcesso.ADMINISTRADOR)){
      throw new AccessDeniedException("Você não tem permissao para acessar esses dados");
    }

    Empresa empresa = logado.getEmpresa();

    Funcionario funcionario = funcionarioService.encontraFuncionarioPorId(empresa, id);

    return ResponseEntity.ok(FuncionarioDto.toFuncionarioDto(funcionario));
  }

  @PutMapping("/{id}/ativo")
  @Transactional
  public ResponseEntity<FuncionarioDto> desativarFuncionario(Authentication auth, @PathVariable("id") Long id){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    Funcionario funcionario = funcionarioService.alternarAtivo(empresa, id);

    return ResponseEntity.ok().body(FuncionarioDto.toFuncionarioDto(funcionario));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<FuncionarioDto> alterarDadosDoFuncionario(@PathVariable("id") Long id, @RequestBody @Valid AlteraFuncionarioForm form, Authentication auth){
    Funcionario logado = AuthenticationHelper.getAutenticado(auth);

    if (!logado.getFuncionarioId().equals(id) && !logado.getFuncao().getPrivilegiosDeAcesso().equals(PrivilegiosDeAcesso.ADMINISTRADOR)){
      throw new AccessDeniedException("Você não tem permissao para acessar esses dados");
    }

    Funcionario funcionario = funcionarioService.alterarDadosFuncionario(id, form, logado);

    return ResponseEntity.ok(FuncionarioDto.toFuncionarioDto(funcionario));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> deletarFuncionario(Authentication auth, @PathVariable("id") Long id){
    funcionarioService.apagaFuncionario(id, AuthenticationHelper.getEmpresaDoAutenticado(auth));

    return ResponseEntity.noContent().build();
  }
}

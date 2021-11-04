package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.dto.FuncionarioDto;
import br.dev.rodrigocury.uaibugouapi.dto.NovoFuncionarioDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovoFuncionarioForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.services.FuncionarioService;
import br.dev.rodrigocury.uaibugouapi.utils.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
  public ResponseEntity<List<FuncionarioDto>> listaFuncionarioDaEmpresa(Authentication auth){
    Funcionario autenticado = AuthenticationHelper.getAutenticado(auth);
    Empresa empresa = autenticado.getEmpresa();

    List<Funcionario> funcionarios = funcionarioService.encontraFuncionariosDaEmpresa(empresa);
    List<FuncionarioDto> funcionarioDtos = funcionarios.stream()
        .map(FuncionarioDto::toFuncionarioDto)
        .toList();

    return ResponseEntity.ok(funcionarioDtos);
  }

  @Transactional
  @GetMapping("/{id}")
  public ResponseEntity<FuncionarioDto> encontraFuncionarioDaEmpresa(Authentication auth, @PathVariable("id") Long id){
    Funcionario autenticado = AuthenticationHelper.getAutenticado(auth);
    Empresa empresa = autenticado.getEmpresa();

    Funcionario funcionario = funcionarioService.encontraFuncionarioPorId(autenticado, id);

    return ResponseEntity.ok(FuncionarioDto.toFuncionarioDto(funcionario));
  }

  @PutMapping("/{id}/ativo")
  @Transactional
  public ResponseEntity<FuncionarioDto> desativarFuncionario(Authentication auth, @PathVariable("id") Long id){
    Funcionario autenticado = AuthenticationHelper.getAutenticado(auth);

    Funcionario funcionario = funcionarioService.alternarAtivo(autenticado, id);

    return ResponseEntity.ok(FuncionarioDto.toFuncionarioDto(funcionario));
  }
}
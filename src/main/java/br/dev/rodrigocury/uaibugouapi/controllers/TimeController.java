package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.controllers.customExceptions.IllegalEntityChange;
import br.dev.rodrigocury.uaibugouapi.dto.TimeDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovoTimeForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.services.TimeService;
import br.dev.rodrigocury.uaibugouapi.utils.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("time")
public class TimeController {

  private final TimeService timeService;

  @Autowired
  public TimeController(TimeService timeService) {
    this.timeService = timeService;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TimeDto> criaTime(Authentication auth, @RequestBody @Valid NovoTimeForm form, UriComponentsBuilder builder){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    TimeDto dto = timeService.criaTime(empresa, form);

    URI uri = builder.path("/time/{id}").buildAndExpand(dto.getTimeId()).toUri();

    return ResponseEntity.created(uri).body(dto);
  }

  @GetMapping
  @Transactional
  public ResponseEntity<Page<TimeDto>> listaTimes(
      Authentication auth,
      @PageableDefault(sort = "timeId") Pageable pageable
      ) {
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    Page<TimeDto> dtos = timeService.listaTimes(empresa, pageable);

    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<TimeDto> timeInfo(Authentication auth, @PathVariable("id") Long id){
    Funcionario logado = AuthenticationHelper.getAutenticado(auth);

    PrivilegiosDeAcesso privilegioDoLogado = logado.getFuncao().getPrivilegiosDeAcesso();

    if (!List.of(PrivilegiosDeAcesso.ADMINISTRADOR, PrivilegiosDeAcesso.GERENTE)
            .contains(privilegioDoLogado) &&
        !logado.getTime().getTimeId().equals(id)){
      throw new AccessDeniedException("Você não tem autorização de acesso a esses dados");
    }

    TimeDto dto = timeService.encontraUmTime(id);

    return ResponseEntity.ok(dto);
  }

  @PatchMapping("/adicionar/{timeId}/{funcionarioId}")
  @Transactional
  public ResponseEntity<?> adicionarFuncionario(
      Authentication auth,
      @PathVariable("timeId") Long timeId,
      @PathVariable("funcionarioId") Long funcionarioId
  ){
    Empresa empresaDoLogado = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    timeService.adicionaFuncionario(funcionarioId, timeId, empresaDoLogado);

    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/remove/{funcionarioId}")
  public ResponseEntity<?> removerFuncionario(
      Authentication auth,
      @PathVariable("funcionarioId") Long funcionarioId
  ){
    Empresa empresaDoLogado = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    timeService.removeFuncionario(funcionarioId, empresaDoLogado);

    return ResponseEntity.noContent().build();
  }

  // TODO DELETAR | ADICIONAR PROJETO | REMOVER PROJETO
}

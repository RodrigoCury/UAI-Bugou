package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovoProjetoForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.services.ProjetoService;
import static br.dev.rodrigocury.uaibugouapi.utils.AuthenticationHelper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

  private final ProjetoService projetoService;

  @Autowired
  public ProjetoController(ProjetoService projetoService) {
    this.projetoService = projetoService;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<ProjetoDto> criaProjeto(Authentication auth, @RequestBody @Valid NovoProjetoForm form, UriComponentsBuilder builder){
    Empresa empresa = getEmpresaDoAutenticado(auth);

    ProjetoDto dto = projetoService.criaProjeto(empresa, form);

    URI uri = builder.path("/projeto/{id}").buildAndExpand(dto.getProjetoId()).toUri();

    return ResponseEntity.created(uri).body(dto);
  }

  @GetMapping
  @Transactional
  public ResponseEntity<Page<ProjetoDto>> listaProjetos(
      Authentication auth,
      @PageableDefault(sort = "projetoId")Pageable pageable
  ){
    Funcionario funcionario = getAutenticado(auth);

    Page<ProjetoDto> dtos = projetoService.listaProjeto(funcionario, pageable);

    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<ProjetoDto> getInfoProjeto(Authentication auth, @PathVariable("id") Long id){
    Empresa empresa = getEmpresaDoAutenticado(auth);
    Funcionario funcionario = getAutenticado(auth);

    ProjetoDto dto;

    if (funcionario.getFuncao().getPrivilegiosDeAcesso().equals(PrivilegiosDeAcesso.ADMINISTRADOR)){
      dto = projetoService.retornaProjeto(empresa, id);
    } else if (funcionario.getFuncao().getPrivilegiosDeAcesso().equals(PrivilegiosDeAcesso.GERENTE)){
      dto = projetoService.retornaProjetoGerente(funcionario, id);
    } else {
      dto = projetoService.retornaProjetoFuncionario(funcionario, id);
    }

    return ResponseEntity.ok(dto);
  }

  @PutMapping("{id}")
  @Transactional
  public ResponseEntity<ProjetoDto> alteraInfoProjeto(
      Authentication auth,
      @PathVariable("id") Long id,
      @RequestBody @Valid NovoProjetoForm form){
    Empresa empresa = getEmpresaDoAutenticado(auth);

    ProjetoDto dto = projetoService.alteraDadosProjeto(form, id, empresa);

    return ResponseEntity.ok(dto);
  }

}

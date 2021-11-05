package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.dto.EmpresaAlteradaDto;
import br.dev.rodrigocury.uaibugouapi.dto.EmpresaDto;
import br.dev.rodrigocury.uaibugouapi.dto.NovaEmpresaDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovaEmpresaForm;
import br.dev.rodrigocury.uaibugouapi.forms.AlteraEmpresaForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.services.EmpresaService;
import br.dev.rodrigocury.uaibugouapi.utils.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

  private final EmpresaService empresaService;

  @Autowired
  public EmpresaController(EmpresaService empresaService){
    this.empresaService = empresaService;
  }

  @PostMapping
  public ResponseEntity<NovaEmpresaDto> criaEmpresaEAdm(@Valid @RequestBody NovaEmpresaForm form){
    NovaEmpresaDto novaEmpresaDto = empresaService.criaEmpresa(form);
    return ResponseEntity.ok(novaEmpresaDto);
  }

  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity<EmpresaDto> getEmpresaData(@PathVariable("id") Long id, Authentication authentication){
    Empresa empresaDoLogin = AuthenticationHelper.getEmpresaDoAutenticado(authentication);

    if(!empresaDoLogin.getEmpresaId().equals(id)){
      throw new AccessDeniedException("Você não tem permissão para acessar esses dados");
    }

    Empresa empresa = empresaService.getEmpresa(id);

    return ResponseEntity.ok(new EmpresaDto(empresa));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<EmpresaAlteradaDto> alterarDadosEmpresa(@RequestBody @Valid AlteraEmpresaForm form, Authentication auth){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    System.out.println(form);

    EmpresaAlteradaDto dto = empresaService.alteraDadosDaEmpresa(empresa, form);

    return ResponseEntity.ok(dto);
  }

  @DeleteMapping
  @Transactional
  public ResponseEntity<?> apagaEmpresa(Authentication auth){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    empresaService.desabilitaFuncionarios(empresa);

    return ResponseEntity.ok().build();
  }

}

package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.dto.NovaEmpresaDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovaEmpresaForm;
import br.dev.rodrigocury.uaibugouapi.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

}

package br.dev.rodrigocury.uaibugouapi.controllers;

import br.dev.rodrigocury.uaibugouapi.dto.FuncaoDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovaFuncaoForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.services.FuncaoService;
import br.dev.rodrigocury.uaibugouapi.utils.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("funcao")
public class FuncaoController {

  private final FuncaoService funcaoService;

  @Autowired
  public FuncaoController(FuncaoService funcaoService) {
    this.funcaoService = funcaoService;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<FuncaoDto> criaFuncao(Authentication auth, @RequestBody @Valid NovaFuncaoForm form){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    FuncaoDto dto = funcaoService.criaFuncao(empresa, form);

    return ResponseEntity.ok().body(dto);
  }

  @GetMapping
  public ResponseEntity<Page<FuncaoDto>> listaFuncoes(
      Authentication auth,
      @PageableDefault(sort = "privilegiosDeAcesso") Pageable pageable
      ){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);
    Page<FuncaoDto> set = funcaoService.listaFuncoes(empresa, pageable);

    return ResponseEntity.ok(set);
  }

  @GetMapping("{id}")
  public ResponseEntity<FuncaoDto> retornaFuncao(@PathVariable("id") Long id, Authentication auth){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    FuncaoDto dto = funcaoService.retornaFuncao(id, empresa);

    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/privilegios")
  public ResponseEntity<PrivilegiosDeAcesso[]> retornaListaComPrivilegiosDeAcesso(){
    return ResponseEntity.ok(PrivilegiosDeAcesso.values());
  }

  @PutMapping("{id}")
  @Transactional
  public ResponseEntity<FuncaoDto> alteraFuncao(Authentication auth, @RequestBody @Valid NovaFuncaoForm form, @PathVariable("id") Long id){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);

    FuncaoDto dto = funcaoService.alteraFuncao(id, empresa, form);

    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("{id}")
  @Transactional
  public ResponseEntity<?> deletaFuncao(
      @PathVariable Long id,
      @RequestParam(defaultValue = "false") boolean forceDelete,
      Authentication auth
      ){
    Empresa empresa = AuthenticationHelper.getEmpresaDoAutenticado(auth);
    funcaoService.deletaFuncao(id, forceDelete, empresa);

    return ResponseEntity.ok().build();
  }
}

package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.categorias.CategoriaEmpresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Projeto;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;

import java.util.HashSet;
import java.util.Set;

public class EmpresaDto {
  private final Long empresaId;

  private final String empresaNome;

  private final CategoriaEmpresaDTO categoriaEmpresa;

  private final Set<FuncionarioDto> funcionarios;

  private final Set<Projeto> projetos;

  private final Set<Time> times;

  private final Set<FuncaoDto> funcoes;

  public EmpresaDto(Empresa empresa){
    this.empresaId = empresa.getEmpresaId();
    this.empresaNome = empresa.getEmpresaNome();
    this.categoriaEmpresa = new CategoriaEmpresaDTO(empresa.getCategoriaEmpresa());
    this.funcionarios = new HashSet<>();
    this.projetos = empresa.getProjetos();
    this.times = empresa.getTimes();
    this.funcoes = new HashSet<>();

    this.funcoes.addAll(empresa.getFuncoes().stream().map(FuncaoDto::new).toList());
    this.funcionarios.addAll(empresa.getFuncionarios().stream().map(FuncionarioDto::toFuncionarioDto).toList());
  }

  public Long getEmpresaId() {
    return empresaId;
  }

  public String getEmpresaNome() {
    return empresaNome;
  }

  public CategoriaEmpresaDTO getCategoriaEmpresa() {
    return categoriaEmpresa;
  }

  public Set<FuncionarioDto> getFuncionarios() {
    return funcionarios;
  }

  public Set<Projeto> getProjetos() {
    return projetos;
  }

  public Set<Time> getTimes() {
    return times;
  }

  public Set<FuncaoDto> getFuncoes() {
    return funcoes;
  }

}

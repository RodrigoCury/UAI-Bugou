package br.dev.rodrigocury.uaibugouapi.services;

import br.dev.rodrigocury.uaibugouapi.controllers.customExceptions.IllegalEntityChange;
import br.dev.rodrigocury.uaibugouapi.dto.ProjetoDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovoProjetoForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Projeto;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncionarioRepository;
import br.dev.rodrigocury.uaibugouapi.repositories.ProjetoRepository;
import br.dev.rodrigocury.uaibugouapi.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjetoService {

  private final ProjetoRepository projetoRepository;
  private final FuncionarioRepository funcionarioRepository;
  private final TimeRepository timeRepository;

  @Autowired
  public ProjetoService(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository, TimeRepository timeRepository) {
    this.projetoRepository = projetoRepository;
    this.funcionarioRepository = funcionarioRepository;
    this.timeRepository = timeRepository;
  }

  @Transactional
  public ProjetoDto criaProjeto(Empresa empresa, NovoProjetoForm form) {
    Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByFuncionarioIdAndEmpresaId(form.getFuncionarioResponsavelId(), empresa.getEmpresaId());
    Set<Time> times = timeRepository.findAllByEmpresa_EmpresaIdAndListOfIds(empresa.getEmpresaId(), form.getTimesIds());

    if (funcionarioOptional.isEmpty()){
      throw new EntityNotFoundException("Funcionario nao Encontrado");
    }

    Funcionario funcionarioResponsavel = funcionarioOptional.get();
    PrivilegiosDeAcesso privilegioDoResponsavel = funcionarioResponsavel.getFuncao().getPrivilegiosDeAcesso();

    if (privilegioDoResponsavel.equals(PrivilegiosDeAcesso.FUNCIONARIO)){
      throw new IllegalEntityChange("Funcionario precisa de cargo de gerente ou administrador " +
          "para ser funcionario responsavel por um projeto");
    }

    Projeto projeto = new Projeto(empresa, funcionarioResponsavel, form.getProjetoNome());

    projetoRepository.save(projeto);

    for (Time time : times) {
      time.addProjeto(projeto);
    }

    projeto.setTimes(times);

    return new ProjetoDto(projeto);
  }

  public Page<ProjetoDto> listaProjeto(Funcionario funcionario, Pageable pageable) {
    Long empresaId = funcionario.getEmpresa().getEmpresaId();
    Long funcionarioId = funcionario.getFuncionarioId();
    Long timeId = funcionario.getTime().getTimeId();

    Page<ProjetoDto> dtos;

    if (funcionario.getFuncao().getPrivilegiosDeAcesso().equals(PrivilegiosDeAcesso.ADMINISTRADOR)){
      dtos = projetoRepository.findAllByEmpresa_EmpresaId(empresaId, pageable);
    } else if (funcionario.getFuncao().getPrivilegiosDeAcesso().equals(PrivilegiosDeAcesso.GERENTE)) {
      dtos = projetoRepository.findAllByFuncionarioResponsavelId(funcionarioId, pageable);
    } else {
      dtos = projetoRepository.findAllByTimes_TimeId(timeId, pageable);
    }
    return dtos;
  }

  public ProjetoDto retornaProjetoGerente(Funcionario funcionario, Long id) {
    Optional<ProjetoDto> dtoOptional = projetoRepository.findByEmpresa_EmpresaIdAndProjetoIdOrFuncionarioResponsavelId(
        funcionario.getFuncionarioId(),
        funcionario.getTime().getTimeId(),
        id
    );
    if (dtoOptional.isEmpty()){
      throw new EntityNotFoundException("Projeto com Id não foi encontrado ou você não tem acesso a esses dados");
    }
    return dtoOptional.get();
  }

  public ProjetoDto retornaProjetoFuncionario(Funcionario funcionario, Long id) {
    Optional<ProjetoDto> dtoOptional = projetoRepository.findByTimeIdAndProjetoId(
        funcionario.getTime().getTimeId(),
        id
    );

    if (dtoOptional.isEmpty()){
      throw new EntityNotFoundException("Projeto com Id não foi encontrado ou você não tem acesso a esses dados");
    }
    return dtoOptional.get();
  }

  public ProjetoDto retornaProjeto(Empresa empresa, Long id) {
    Optional<ProjetoDto> dtoOptional = projetoRepository.findByEmpresa_EmpresaIdAndProjetoIdDto(empresa.getEmpresaId(), id);

    if (dtoOptional.isEmpty()) {
      throw new EntityNotFoundException("Projeto com Id não foi encontrado");
    }

    return dtoOptional.get();

  }


  @Transactional
  public ProjetoDto alteraDadosProjeto(NovoProjetoForm form, Long id, Empresa empresa) {
    Optional<Projeto> projetoOpt = projetoRepository.findByEmpresa_EmpresaIdAndProjetoId(empresa.getEmpresaId(), id);
    Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByFuncionarioIdAndEmpresaId(form.getFuncionarioResponsavelId(), empresa.getEmpresaId());
    Set<Time> times = timeRepository.findAllByEmpresa_EmpresaIdAndListOfIds(empresa.getEmpresaId(), form.getTimesIds());

    if (projetoOpt.isEmpty()) {
      throw new EntityNotFoundException("Projeto com Id não foi encontrado");
    }
    if (funcionarioOpt.isEmpty()){
      throw new EntityNotFoundException("Funcionario com Id não foi encontrado");
    }

    Projeto projeto = projetoOpt.get();
    Funcionario funcionarioResponsavel = funcionarioOpt.get();
    PrivilegiosDeAcesso privilegioDoResponsavel = funcionarioResponsavel.getFuncao().getPrivilegiosDeAcesso();

    if (privilegioDoResponsavel.equals(PrivilegiosDeAcesso.FUNCIONARIO)){
      throw new IllegalEntityChange("Funcionario precisa de cargo de gerente ou administrador " +
          "para ser funcionario responsavel por um projeto");
    }

    projeto.setProjetoNome(form.getProjetoNome());
    projeto.setFuncionarioResponsavel(funcionarioResponsavel);
    projeto.setTimes(times);

    return new ProjetoDto(projeto);
  }
}

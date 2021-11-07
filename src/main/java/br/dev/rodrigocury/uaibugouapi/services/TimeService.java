package br.dev.rodrigocury.uaibugouapi.services;

import br.dev.rodrigocury.uaibugouapi.controllers.customExceptions.IllegalEntityChange;
import br.dev.rodrigocury.uaibugouapi.dto.TimeDto;
import br.dev.rodrigocury.uaibugouapi.forms.NovoTimeForm;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;
import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncionarioRepository;
import br.dev.rodrigocury.uaibugouapi.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class TimeService {

  private final FuncionarioRepository funcionarioRepository;
  private final TimeRepository timeRepository;

  @Autowired
  public TimeService(FuncionarioRepository funcionarioRepository, TimeRepository timeRepository) {
    this.funcionarioRepository = funcionarioRepository;
    this.timeRepository = timeRepository;
  }

  @Transactional
  public TimeDto criaTime(Empresa empresa, NovoTimeForm form) {
    Time time = new Time(form.getTimeNome(), empresa);

    if (form.getFuncionariosIds() != null){
      Set<Funcionario> funcionariosDoForm = funcionarioRepository.findWithListOfIds(form.getFuncionariosIds());
      funcionariosDoForm.forEach(f -> f.setTime(time));
      time.setFuncionarios(funcionariosDoForm);
    }

    timeRepository.save(time);

    return new TimeDto(time);
  }

  @Transactional
  public Page<TimeDto> listaTimes(Empresa empresa, Pageable pageable) {

    Page<Time> times = timeRepository.findByEmpresaId(empresa.getEmpresaId(), pageable);

    return times.map(TimeDto::new);
  }

  public TimeDto encontraUmTime(Long id) {
    Optional<Time> byId = timeRepository.findById(id);

    if (byId.isEmpty()) throw new EntityNotFoundException("Não existe Time com o ID recebido");

    return new TimeDto(byId.get());
  }

  @Transactional
  public void adicionaFuncionario(Long funcionarioId, Long timeId, Empresa empresa) {
    Time time = encontraTime(timeId, empresa);
    Funcionario funcionario = encontraFuncionario(funcionarioId, empresa);

    if (funcionario.getTime() != null && !funcionario.getTime().getTimeId().equals(timeId)){
      throw new IllegalEntityChange("Funcionario já pertence a outro time");
    } else if (funcionario.getTime() != null && funcionario.getTime().getTimeId().equals(timeId)){
      throw new IllegalEntityChange("Funcionario já pertence a este time");
    }

    funcionario.setTime(time);
  }

  public void removeFuncionario(Long funcionarioId, Empresa empresaDoLogado) {
    Funcionario funcionario = encontraFuncionario(funcionarioId, empresaDoLogado);

    if(funcionario.getTime() == null){
      throw new IllegalEntityChange("Funcionario não possui um time, impossivel remove-lo");
    }

    funcionario.setTime(null);
    funcionarioRepository.save(funcionario);
  }

  private Time encontraTime(Long id, Empresa empresa){
    Optional<Time> timeOpt = timeRepository.findByTimeIdAndEmpresa_EmpresaId(id, empresa.getEmpresaId());

    if (timeOpt.isEmpty()){
      throw new EntityNotFoundException("Time Nao Encontrado");
    }

    return timeOpt.get();
  }

  private Funcionario encontraFuncionario(Long id, Empresa empresa){
    Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByFuncionarioIdAndEmpresaId(id, empresa.getEmpresaId());
    if (funcionarioOpt.isEmpty()){
      throw new EntityNotFoundException("Funcionario Nao Encontrado");
    }
    return funcionarioOpt.get();
  }
}

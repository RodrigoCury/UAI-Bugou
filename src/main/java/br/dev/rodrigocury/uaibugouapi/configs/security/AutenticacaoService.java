package br.dev.rodrigocury.uaibugouapi.configs.security;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

  private final FuncionarioRepository funcionarioRepository;

  @Autowired
  public AutenticacaoService(FuncionarioRepository funcionarioRepository) {
    this.funcionarioRepository = funcionarioRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Funcionario> usuario = funcionarioRepository.findByFuncionarioEmail(email);

    if (usuario.isEmpty()) throw new UsernameNotFoundException("Dados Inválidos");

    return usuario.get();
  }
}
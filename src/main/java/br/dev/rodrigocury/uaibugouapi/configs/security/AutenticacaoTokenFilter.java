package br.dev.rodrigocury.uaibugouapi.configs.security;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import br.dev.rodrigocury.uaibugouapi.repositories.FuncionarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final FuncionarioRepository funcionarioRepository;

    public AutenticacaoTokenFilter(TokenService tokenService, FuncionarioRepository funcionarioRepository) {
      this.tokenService = tokenService;
      this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      String token = recuperarToken(request);

      boolean valido = tokenService.isTokenValido(token);

      if(valido){
        autenticarCliente(token);
      }

      filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
      Long id = tokenService.getIdUsuario(token);

      Optional<Funcionario> usuarioFound = funcionarioRepository.findById(id);
      if (usuarioFound.isEmpty()) {
       throw new EntityNotFoundException("Usuário não encontrado");
      }
      Funcionario usuario = usuarioFound.get();

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
      String token = request.getHeader("Authorization");
      if(token == null || !token.startsWith("Bearer ")){
        return null;
      }

      return token.substring(7, token.length());
    }


}



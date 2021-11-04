package br.dev.rodrigocury.uaibugouapi.configs.security;

import br.dev.rodrigocury.uaibugouapi.models.funcionario.Funcionario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

  @Value("${uaibugou.jwt.expiration}")
  private String expiration;

  @Value("${uaibugou.jwt.secret}")
  private String secret;

  public String gerarToken(Authentication authentication){
    Funcionario logado = (Funcionario) authentication.getPrincipal();
    Date agora = new Date();
    Date dataExpiracao = new Date(agora.getTime() + Long.parseLong(expiration));
    return Jwts.builder()
        .setIssuer("Fórum da Alura")
        .setSubject(logado.getFuncionarioId().toString())
        .setIssuedAt(agora)
        .setExpiration(dataExpiracao)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean isTokenValido(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (Exception e){
      return false;
    }
  }

  public Long getIdUsuario(String token) {
    Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return Long.parseLong(body.getSubject());
  }
}

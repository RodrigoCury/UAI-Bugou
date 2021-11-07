package br.dev.rodrigocury.uaibugouapi.configs.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class RouteAccessConfigs {
  public static void configuraAcessoRotasFuncionarios(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers(HttpMethod.GET, "/funcionario").hasRole("ADMINISTRADOR")
        .mvcMatchers(HttpMethod.POST, "/funcionario").hasRole("ADMINISTRADOR")
        .mvcMatchers(HttpMethod.GET, "/funcionario/{id}").hasRole("FUNCIONARIO") // O Controller checa se é adm para acessar dados de outros funcionarios
        .mvcMatchers(HttpMethod.PUT, "/funcionario/{id}/ativo").hasRole("ADMINISTRADOR")
        .mvcMatchers(HttpMethod.PUT, "/funcionario/{id}").hasRole("FUNCIONARIO") // O Controller checa se é adm para acessar dados de outros funcionarios
        .mvcMatchers(HttpMethod.DELETE, "/funcionario/**").hasRole("ADMINISTRADOR");
  }

  public static void configuraAcessoRotasEmpresa(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers(HttpMethod.GET, "/empresa").hasRole("ADMINISTRADOR")
        .mvcMatchers(HttpMethod.PUT, "/empresa").hasRole("ADMINISTRADOR")
        .mvcMatchers(HttpMethod.DELETE, "/empresa").hasRole("ADMINISTRADOR")
        .mvcMatchers(HttpMethod.POST, "/empresa").permitAll();
  }

  public static void configuraAcessoRotasFuncao(HttpSecurity http) throws Exception{
    http.authorizeRequests()
        .mvcMatchers("/funcao").hasRole("ADMINISTRADOR")
        .mvcMatchers("/funcao/**").hasRole("ADMINISTRADOR")
    ;
  }

  public static void configuraAcessoAsRotasAbertas(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers(HttpMethod.POST, "/auth").permitAll()
        .anyRequest().authenticated();
  }
  public static void configuraAcessoAsRotasH2(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers("/h2-console/**").permitAll();

  }
}

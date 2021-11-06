package br.dev.rodrigocury.uaibugouapi.configs.security;

import br.dev.rodrigocury.uaibugouapi.repositories.FuncionarioRepository;
import static br.dev.rodrigocury.uaibugouapi.configs.security.RouteAccessConfigs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class DevSecurityConfigs extends WebSecurityConfigurerAdapter {

  private final AutenticacaoService autenticacaoService;
  private final TokenService tokenService;
  private final FuncionarioRepository funcionarioRepository;

  @Autowired
  public DevSecurityConfigs(AutenticacaoService autenticacaoService, TokenService tokenService, FuncionarioRepository funcionarioRepository) {
    this.autenticacaoService = autenticacaoService;
    this.tokenService = tokenService;
    this.funcionarioRepository = funcionarioRepository;
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    configuraHierarquiaDeAcesso(http);
    configuraAcessoRotasFuncionarios(http);
    configuraAcessoRotasEmpresa(http);
    configuraAcessoRotasFuncao(http);
    configuraAcessoAsRotasH2(http);
    configuraAcessoAsRotasAbertas(http);
    configuraAutenticacao(http);
  }

  private void configuraHierarquiaDeAcesso(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .expressionHandler(webExpressionHandler());
  }

  private void configuraAutenticacao(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, funcionarioRepository), UsernamePasswordAuthenticationFilter.class);

    http.headers().frameOptions().disable();
  }

  @Bean
  public RoleHierarchyImpl roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMINISTRADOR > ROLE_GERENTE ROLE_GERENTE > ROLE_FUNCIONARIO");
    return roleHierarchy;
  }

  private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
    DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
    defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
    return defaultWebSecurityExpressionHandler;
  }
}

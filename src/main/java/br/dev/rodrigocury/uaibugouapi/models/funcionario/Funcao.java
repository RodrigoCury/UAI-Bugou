package br.dev.rodrigocury.uaibugouapi.models.funcionario;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Empresa;
import br.dev.rodrigocury.uaibugouapi.models.enums.PrivilegiosDeAcesso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table(name = "funcao")
@Entity
public class Funcao implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long funcaoId;

  @ManyToOne
  @JoinColumn(name = "empresa_id")
  @JsonManagedReference
  private Empresa empresa;

  @Enumerated(value = EnumType.STRING)
  @JsonIgnore
  private PrivilegiosDeAcesso privilegiosDeAcesso;

  private String funcaoNome;

  public Funcao() {
  }

  public Funcao(Empresa empresa, PrivilegiosDeAcesso privilegiosDeAcesso, String funcaoNome) {
    this.empresa = empresa;
    this.privilegiosDeAcesso = privilegiosDeAcesso;
    this.funcaoNome = funcaoNome;
  }


  public PrivilegiosDeAcesso getPrivilegiosDeAcesso() {
    return privilegiosDeAcesso;
  }

  public void setPrivilegiosDeAcesso(PrivilegiosDeAcesso privilegiosDeAcesso) {
    this.privilegiosDeAcesso = privilegiosDeAcesso;
  }

  public String getFuncaoNome() {
    return funcaoNome;
  }

  public void setFuncaoNome(String funcaoNome) {
    this.funcaoNome = funcaoNome;
  }

  public Long getId() {
    return funcaoId;
  }

  public void setId(Long id) {
    this.funcaoId = id;
  }

  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }

  @Override
  @JsonIgnore
  public String getAuthority() {
    return String.format("ROLE_%s" ,privilegiosDeAcesso.name());
  }
}
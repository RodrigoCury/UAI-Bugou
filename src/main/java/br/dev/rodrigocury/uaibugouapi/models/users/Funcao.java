package br.dev.rodrigocury.uaibugouapi.models.users;

import br.dev.rodrigocury.uaibugouapi.models.enums.Privileges;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Table(name = "funcao")
@Entity
public class Funcao implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "funcao_id", nullable = false)
  private Long funcaoId;

  @Enumerated(EnumType.STRING)
  @Column(name = "privilegio_de_acesso", nullable = false)
  private Privileges privilegioDeAcesso;

  @ManyToMany(cascade = CascadeType.DETACH)
  @JoinTable(name = "funcao_funcionario",
    joinColumns = @JoinColumn(name = "funcao_id"),
    inverseJoinColumns = @JoinColumn(name = "funcionario_id")
  )
  private List<Funcionario> funcionarios;

  private String funcaoNome;

  public Funcao() {
  }

  @Override
  public String getAuthority() {
    return this.privilegioDeAcesso.toString();
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }

  public Long getFuncaoId() {
    return funcaoId;
  }

  public void setFuncaoId(Long funcaoId) {
    this.funcaoId = funcaoId;
  }

  public String getFuncaoNome() {
    return funcaoNome;
  }

  public void setFuncaoNome(String funcaoNome) {
    this.funcaoNome = funcaoNome;
  }

  public Privileges getPrivilegioDeAcesso() {
    return privilegioDeAcesso;
  }

  public void setPrivilegioDeAcesso(Privileges privilegioDeAcesso) {
    this.privilegioDeAcesso = privilegioDeAcesso;
  }
}
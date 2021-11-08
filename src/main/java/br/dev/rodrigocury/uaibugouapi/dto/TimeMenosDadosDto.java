package br.dev.rodrigocury.uaibugouapi.dto;

import br.dev.rodrigocury.uaibugouapi.models.entidadesdaempresa.Time;

import java.util.HashSet;
import java.util.Set;

public class TimeMenosDadosDto {

  private final Long timeId;
  private final String timeNome;

  public TimeMenosDadosDto(Time time) {
    this.timeId = time.getTimeId();
    this.timeNome = time.getTimeNome();
  }

  public Long getTimeId() {
    return timeId;
  }

  public String getTimeNome() {
    return timeNome;
  }
}

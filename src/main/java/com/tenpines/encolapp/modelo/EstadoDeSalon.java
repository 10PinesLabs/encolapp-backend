package com.tenpines.encolapp.modelo;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Date: 27/01/18 - 15:25
 */
public class EstadoDeSalon {

  private Set<Speaker> presentes;
  private Set<Speaker> cola;

  public static EstadoDeSalon create(Set<Speaker> presentes, Set<Speaker> speakers) {
    EstadoDeSalon estadoDeSalon = new EstadoDeSalon();
    estadoDeSalon.presentes = new LinkedHashSet<>(presentes);
    estadoDeSalon.cola = new LinkedHashSet<>(speakers);
    return estadoDeSalon;
  }

  public Set<Speaker> getPresentes() {
    return presentes;
  }

  public void setPresentes(Set<Speaker> presentes) {
    this.presentes = presentes;
  }

  public Set<Speaker> getCola() {
    return cola;
  }

  public void setCola(Set<Speaker> cola) {
    this.cola = cola;
  }
}

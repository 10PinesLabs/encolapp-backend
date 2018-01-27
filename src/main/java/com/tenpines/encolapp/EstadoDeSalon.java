package com.tenpines.encolapp;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Date: 27/01/18 - 15:25
 */
public class EstadoDeSalon {
  private Set<Speaker> presentes;
  private LinkedHashSet<Speaker> cola;

  public static EstadoDeSalon create(Set<Speaker> presentes, Set<Speaker> speakers) {
    EstadoDeSalon estadoDeSalon = new EstadoDeSalon();
    estadoDeSalon.presentes = new LinkedHashSet<>(presentes);
    estadoDeSalon.cola = new LinkedHashSet<>(speakers);
    return estadoDeSalon;
  }
}

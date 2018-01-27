package com.tenpines.encolapp;

import ar.com.kfgodel.nary.api.Nary;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Date: 27/01/18 - 13:16
 */
public class Salon {

  private Set<Speaker> presentes = Nary.of(
    Speaker.create("Jorge"),
    Speaker.create("Dario"),
    Speaker.create("Gise")
  ).collect(Collectors.toCollection(LinkedHashSet::new));

  private Set<Speaker> speakers = Nary.of(
    Speaker.create("Jorge"),
    Speaker.create("Dario"),
    Speaker.create("Gise")
  ).collect(Collectors.toCollection(LinkedHashSet::new));

  public static Salon create() {
    Salon salon = new Salon();
    return salon;
  }

  public Set<Speaker> obtenerPresentes() {
    return presentes;
  }

  public void ingresar(Speaker speaker) {
    presentes.add(speaker);
  }

  public void salir(Speaker speaker) {
    presentes.remove(speaker);
    speakers.remove(speaker);
  }

  public Set<Speaker> obtenerCola() {
    return speakers;
  }

  public void encolar(Speaker speaker) {
    speakers.add(speaker);
  }

  public void desencolar(Speaker speaker) {
    speakers.remove(speaker);
  }
}

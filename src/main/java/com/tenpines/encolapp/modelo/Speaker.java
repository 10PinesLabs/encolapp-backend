package com.tenpines.encolapp.modelo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: 27/01/18 - 11:22
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Speaker {

  private String nombre;
  private Set<Speaker> pidieronRedondeo;

  public Speaker(){
    pidieronRedondeo = new HashSet<>();
  }

  public Speaker(String nombre){
    this();
    this.setNombre(nombre);
  }

  public static Speaker create(String nombre) {
    return new Speaker(nombre);
  }

  public int getPedidosRedondeo() {
    return pidieronRedondeo.size();
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Speaker)) return false;

    Speaker speaker = (Speaker) o;

    return nombre.equals(speaker.nombre);
  }

  @Override
  public int hashCode() {
    return nombre.hashCode();
  }

  public void terminar() {
    this.pidieronRedondeo = new HashSet<>();
  }

  public void redondear(Speaker ortiba) {
    this.pidieronRedondeo.add(ortiba);
  }
}

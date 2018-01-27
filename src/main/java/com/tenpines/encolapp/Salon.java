package com.tenpines.encolapp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Date: 27/01/18 - 13:16
 */
public class Salon {

  private Set<Speaker> presentes;

  private Set<Speaker> speakers;

  public static Salon create() {
    Salon salon = new Salon();
    salon.speakers = new CopyOnWriteArraySet<>();
    salon.presentes = new CopyOnWriteArraySet<>();
    return salon;
  }

  public Flux<Speaker> obtenerPresentes() {
    return Flux.fromIterable(presentes);
  }

  public void ingresar(Speaker speaker) {
    presentes.add(speaker);
  }

  public void salir(Speaker speaker) {
    presentes.remove(speaker);
    speakers.remove(speaker);
  }

  public Flux<Speaker> obtenerCola() {
    return Flux.fromIterable(speakers);
  }

  public void encolar(Speaker speaker) {
    speakers.add(speaker);
  }

  public void desencolar(Speaker speaker) {
    speakers.remove(speaker);
  }

  public Mono<EstadoDeSalon> obtenerEstado() {
    return Mono.just(EstadoDeSalon.create(presentes, speakers));
  }
}

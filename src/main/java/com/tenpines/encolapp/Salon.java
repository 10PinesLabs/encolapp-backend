package com.tenpines.encolapp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/**
 * Date: 27/01/18 - 13:16
 */
public class Salon implements Consumer<FluxSink<EstadoDeSalon>> {

  private Set<Speaker> presentes;

  private Set<Speaker> speakers;
  private Flux<EstadoDeSalon> novedades;
  private FluxSink<EstadoDeSalon> sink;

  public static Salon create() {
    Salon salon = new Salon();
    salon.speakers = new CopyOnWriteArraySet<>();
    salon.presentes = new CopyOnWriteArraySet<>();
    salon.novedades = Flux.create(salon);
    return salon;
  }

  public Flux<Speaker> obtenerPresentes() {
    return Flux.fromIterable(presentes);
  }

  public void ingresar(Speaker speaker) {
    presentes.add(speaker);
    actualizarNovedades();
  }

  public void salir(Speaker speaker) {
    presentes.remove(speaker);
    speakers.remove(speaker);
    actualizarNovedades();
  }

  public void encolar(Speaker speaker) {
    speakers.add(speaker);
    actualizarNovedades();
  }

  public void desencolar(Speaker speaker) {
    speakers.remove(speaker);
    actualizarNovedades();
  }

  public Flux<Speaker> obtenerCola() {
    return Flux.fromIterable(speakers);
  }

  private void actualizarNovedades() {
    sink.next(estadoActual());
  }

  public Flux<EstadoDeSalon> cambiosDeEstado() {
    return novedades;
  }

  public EstadoDeSalon estadoActual() {
    return EstadoDeSalon.create(presentes, speakers);
  }

  @Override
  public void accept(FluxSink<EstadoDeSalon> tFluxSink) {
    this.sink = tFluxSink;
  }
}

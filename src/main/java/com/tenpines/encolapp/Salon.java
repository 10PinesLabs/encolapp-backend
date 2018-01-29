package com.tenpines.encolapp;

import ar.com.kfgodel.nary.api.optionals.Optional;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;
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
  private Optional<FluxSink<EstadoDeSalon>> sink;

  private EventBus eventBus;

  public static Salon create(EventBus eventBus) {
    Salon salon = new Salon();
    salon.speakers = new CopyOnWriteArraySet<>();
    salon.presentes = new CopyOnWriteArraySet<>();
    salon.novedades = Flux.create(salon);
    salon.sink = Optional.empty();
    salon.eventBus = eventBus;
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
    EstadoDeSalon nuevoEstado = estadoActual();
    eventBus.publish("roots.salon.cambio", Json.encode(nuevoEstado));
    sink.ifPresent(elSinko -> elSinko.next(nuevoEstado));
  }

  public Flux<EstadoDeSalon> cambiosDeEstado() {
    return novedades;
  }

  public EstadoDeSalon estadoActual() {
    return EstadoDeSalon.create(presentes, speakers);
  }

  @Override
  public void accept(FluxSink<EstadoDeSalon> tFluxSink) {
    this.sink = Optional.of(tFluxSink);
  }
}

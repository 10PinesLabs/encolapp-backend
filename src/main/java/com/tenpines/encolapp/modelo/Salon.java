package com.tenpines.encolapp.modelo;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Date: 27/01/18 - 13:16
 */
public class Salon {

  private Set<Speaker> presentes;

  private Set<Speaker> speakers;
  private EventBus eventBus;

  public static Salon create(EventBus eventBus) {
    Salon salon = new Salon();
    salon.speakers = new CopyOnWriteArraySet<>();
    salon.presentes = new CopyOnWriteArraySet<>();
    salon.eventBus = eventBus;
    return salon;
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

  private void actualizarNovedades() {
    EstadoDeSalon nuevoEstado = estadoActual();
    eventBus.publish("roots.salon.cambio", Json.encode(nuevoEstado));
  }

  private EstadoDeSalon estadoActual() {
    return EstadoDeSalon.create(presentes, speakers);
  }

}

package com.tenpines.encolapp.websockets;

import com.tenpines.encolapp.modelo.Pedido;
import com.tenpines.encolapp.modelo.Sala;
import com.tenpines.encolapp.modelo.Speaker;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 28/01/18 - 17:09
 */
public class HandlerDeMensajes {
  public static Logger LOG = LoggerFactory.getLogger(HandlerDeMensajes.class);

  private Sala sala;

  public static HandlerDeMensajes create(Sala sala) {
    HandlerDeMensajes handler = new HandlerDeMensajes();
    handler.sala = sala;
    return handler;
  }

  public void registrarEn(EventBus eventBus) {
    eventBus.consumer(Mensajes.ENTRAR_EN_SALA, this::onSpeakerEntrando);
    eventBus.consumer(Mensajes.SALIR_DE_SALA, this::onSpeakerSaliendo);
    eventBus.consumer(Mensajes.ENCOLAR, this::onSpeakerEncolado);
    eventBus.consumer(Mensajes.DESENCOLAR, this::onSpeakerDesencolado);
    eventBus.consumer(Mensajes.REDONDEAR, this::onSpeakerRedondee);
  }

  private void onSpeakerEntrando(Message<String> message) {
    Speaker speaker = parsearSpeaker("Speaker para entrar: {}", message);
    sala.ingresar(speaker);
  }

  private void onSpeakerSaliendo(Message<String> message) {
    Speaker speaker = parsearSpeaker("Speaker para salir: {}", message);
    sala.salir(speaker);
  }

  private void onSpeakerEncolado(Message<String> message) {
    Speaker speaker = parsearSpeaker("Speaker para encolar: {}", message);
    sala.encolar(speaker);
  }

  private void onSpeakerDesencolado(Message<String> message) {
    Speaker speaker = parsearSpeaker("Speaker para des-encolar: {}", message);
    sala.desencolar(speaker);
  }

  private void onSpeakerRedondee(Message<String> message) {
    Pedido pedido = parsear(Pedido.class,"Speaker para que redondee: {}", message);
    sala.redondeo(pedido);
  }


  private Speaker parsearSpeaker(String mensaje, Message<String> message) {
    return this.parsear(Speaker.class, mensaje, message);
  }

  private <T> T parsear(Class<T> clazz, String mensaje, Message<String> message) {
    String body = message.body();
    LOG.info(mensaje, body);
    return Json.decodeValue(body, clazz);
  }


}

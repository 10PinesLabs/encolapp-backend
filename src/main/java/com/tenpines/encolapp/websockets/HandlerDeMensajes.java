package com.tenpines.encolapp.websockets;

import com.tenpines.encolapp.modelo.Salon;
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

  private Salon salon;

  public static HandlerDeMensajes create(Salon salon) {
    HandlerDeMensajes handler = new HandlerDeMensajes();
    handler.salon = salon;
    return handler;
  }

  public void registrarEn(EventBus eventBus) {
    eventBus.consumer("roots.salon.entrar", this::onSpeakerEntrando);
    eventBus.consumer("roots.salon.salir", this::onSpeakerSaliendo);
    eventBus.consumer("roots.salon.encolar", this::onSpeakerEncolado);
    eventBus.consumer("roots.salon.desencolar", this::onSpeakerDesencolado);
  }

  private void onSpeakerEntrando(Message<String> message) {
    Speaker speaker = parsearConMensaje("Speaker para entrar: {}", message);
    salon.ingresar(speaker);
  }

  private void onSpeakerSaliendo(Message<String> message) {
    Speaker speaker = parsearConMensaje("Speaker para salir: {}", message);
    salon.salir(speaker);
  }

  private void onSpeakerEncolado(Message<String> message) {
    Speaker speaker = parsearConMensaje("Speaker para encolar: {}", message);
    salon.encolar(speaker);
  }

  private void onSpeakerDesencolado(Message<String> message) {
    Speaker speaker = parsearConMensaje("Speaker para des-encolar: {}", message);
    salon.desencolar(speaker);
  }

  private Speaker parsearConMensaje(String mensaje, Message<String> message) {
    String body = message.body();
    LOG.info(mensaje, body);
    return Json.decodeValue(body, Speaker.class);
  }


}

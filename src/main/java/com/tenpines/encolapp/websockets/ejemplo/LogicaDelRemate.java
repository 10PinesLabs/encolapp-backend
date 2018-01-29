package com.tenpines.encolapp.websockets.ejemplo;

import ar.com.kfgodel.nary.api.optionals.Optional;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;

import java.math.BigDecimal;

/**
 * Date: 28/01/18 - 16:57
 */
public class LogicaDelRemate {

  private RepoDeAuctions repository;
  private EventBus eventBus;

  public static LogicaDelRemate create(EventBus eventBus) {
    LogicaDelRemate logicaDelRemate = new LogicaDelRemate();
    logicaDelRemate.eventBus = eventBus;
    logicaDelRemate.repository = RepoDeAuctions.create();
    return logicaDelRemate;
  }

  public void ofertar(Auction nuevaOferta) {
    String auctionId = nuevaOferta.getId();
    Optional<Auction> ultimaOferta = this.repository.buscarPorId(auctionId);
    ultimaOferta
      .ifAbsent(() -> aceptarOferta(nuevaOferta))
      .filterNary(nuevaOferta::superaA)
      .ifPresent((anterior) -> aceptarOferta(nuevaOferta));
  }

  private void aceptarOferta(Auction bid) {
    this.repository.guardar(bid);
    eventBus.publish("auction." + bid.getId() + ".bid", Json.encode(bid));
    notificarPrecioDe(bid.getId(), bid.getPrice());
  }

  public void consultarUltimaOferta(String idDeRemate) {
    Optional<Auction> remate = this.repository.buscarPorId(idDeRemate);
    BigDecimal precioActual = remate.mapNary(Auction::getPrice)
      .orElse(BigDecimal.ZERO);
    notificarPrecioDe(idDeRemate, precioActual);
  }

  private void notificarPrecioDe(String idDeRemate, BigDecimal precioActual) {
    eventBus.publish("auction." + idDeRemate + ".price", Json.encode(precioActual));
  }
}

package com.tenpines.encolapp.websockets.ejemplo;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 28/01/18 - 17:09
 */
public class AuctionMessageHandler {
  public static Logger LOG = LoggerFactory.getLogger(AuctionMessageHandler.class);

  private LogicaDelRemate logica;

  public static AuctionMessageHandler create(LogicaDelRemate logica) {
    AuctionMessageHandler handler = new AuctionMessageHandler();
    handler.logica = logica;
    return handler;
  }

  public void registrarEn(EventBus eventBus) {
    eventBus.consumer("auction.price", this::onConsultaDeRemate);
    eventBus.consumer("auction.bid", this::onOfertaDeRemate);
  }

  private void onOfertaDeRemate(Message<String> message) {
    Auction bid = Json.decodeValue(message.body(), Auction.class);
    LOG.info("Bid recibido: {}", bid);
    this.logica.ofertar(bid);
  }

  private void onConsultaDeRemate(Message<String> message) {
    String auctionId = message.body();
    LOG.info("Remate consultado: {}", auctionId);
    this.logica.consultarUltimaOferta(auctionId);
  }

}

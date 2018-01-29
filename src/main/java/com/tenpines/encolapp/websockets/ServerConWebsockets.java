package com.tenpines.encolapp.websockets;

import com.tenpines.encolapp.websockets.ejemplo.AuctionMessageHandler;
import com.tenpines.encolapp.websockets.ejemplo.LogicaDelRemate;
import io.vertx.core.Vertx;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 28/01/18 - 18:36
 */
public class ServerConWebsockets {
  public static Logger LOG = LoggerFactory.getLogger(ServerConWebsockets.class);

  private Vertx vertx;

  public static ServerConWebsockets create() {
    ServerConWebsockets ejemplo = new ServerConWebsockets();
    ejemplo.vertx = Vertx.vertx();
    return ejemplo;
  }

  public void iniciar() {
    registrarHandlerDeMensajesEnElBus();
    iniciarServerHttpConWebSockets();
  }

  private void registrarHandlerDeMensajesEnElBus() {
    LogicaDelRemate logicaDelRemate = LogicaDelRemate.create(vertx.eventBus());

    AuctionMessageHandler messageHandler = AuctionMessageHandler.create(logicaDelRemate);
    messageHandler.registrarEn(vertx.eventBus());
  }

  private Router crearRouterParaWebsockets() {
    Router router = Router.router(vertx);
    router.route("/eventbus/*").handler(eventBusHandler());
    router.route().failureHandler(errorHandler());
    router.route().handler(staticHandler());
    return router;
  }

  private SockJSHandler eventBusHandler() {
    BridgeOptions options = new BridgeOptions()
      .addOutboundPermitted(new PermittedOptions().setAddressRegex(".*"))
      .addInboundPermitted(new PermittedOptions().setAddressRegex(".*"));
    return SockJSHandler.create(vertx)
      .bridge(options, event -> {
        if (event.type() == BridgeEventType.SOCKET_CREATED) {
          LOG.info("A socket was created");
        }
        event.complete(true);
      });
  }

  private ErrorHandler errorHandler() {
    return ErrorHandler.create(true);
  }

  private StaticHandler staticHandler() {
    return StaticHandler.create()
      .setCachingEnabled(false);
  }

  private void iniciarServerHttpConWebSockets() {
    Router router = crearRouterParaWebsockets();
    vertx.createHttpServer()
      .requestHandler(router::accept)
      .listen(8080);
  }

}

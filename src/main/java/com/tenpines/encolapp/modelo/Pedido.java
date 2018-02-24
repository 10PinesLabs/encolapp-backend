package com.tenpines.encolapp.modelo;

/*
 * No se me ocurrió un mejor nombre, básicamente quiero modelar el hecho de que
 * un speaker quiere hacer alguna accion con otro... por ejemplo... el hecho de
 * decirle a alguien que redondee... básicamente involucra 2 speakers, el que
 * pide el redondeo y a quien se lo pide
 */
public class Pedido {

  private Speaker origen;
  private Speaker destinatario;

  public Speaker getOrigen() {
    return origen;
  }

  public void setOrigen(Speaker origen) {
    this.origen = origen;
  }

  public Speaker getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(Speaker destinatario) {
    this.destinatario = destinatario;
  }
}

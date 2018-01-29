package com.tenpines.encolapp.websockets.ejemplo;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;

public class Auction {

  private String id;
  public static final String id_FIELD = "id";

  private BigDecimal price;
  public static final String price_FIELD = "price";


  public String getId() {
    return id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add(id_FIELD, id)
      .add(price_FIELD, price)
      .toString();
  }

  public static Auction create(String auctionId) {
    return create(auctionId, BigDecimal.ZERO);
  }


  public static Auction create(String auctionId, BigDecimal price) {
    Auction auction = new Auction();
    auction.id = auctionId;
    auction.price = price;
    return auction;
  }

  public boolean superaA(Auction ultimaOferta) {
    return this.getPrice().compareTo(ultimaOferta.getPrice()) > 0;
  }
}

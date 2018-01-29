package com.tenpines.encolapp.websockets.ejemplo;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.optionals.Optional;

import java.util.ArrayList;
import java.util.List;

public class RepoDeAuctions {

  private List<Auction> remates;

  public static RepoDeAuctions create() {
    RepoDeAuctions repo = new RepoDeAuctions();
    repo.remates = new ArrayList<>();
    return repo;
  }

  public Optional<Auction> buscarPorId(String auctionId) {
    return Nary.create(remates)
      .filterNary(remate -> remate.getId().equals(auctionId));
  }

  public void guardar(Auction auction) {
    buscarPorId(auction.getId())
      .ifAbsent(() -> remates.add(auction))
      .ifPresent(existente -> existente.setPrice(auction.getPrice()));
  }

}

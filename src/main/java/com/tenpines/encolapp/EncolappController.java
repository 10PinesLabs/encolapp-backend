package com.tenpines.encolapp;

import ar.com.kfgodel.nary.api.Nary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Date: 27/01/18 - 11:08
 */
@RestController
public class EncolappController {

  private Set<Speaker> speakers = Nary.of(
    Speaker.create("Jorge"),
    Speaker.create("Dario"),
    Speaker.create("Gise")
  ).collect(Collectors.toCollection(LinkedHashSet::new));

  private Set<Speaker> presentes = Nary.of(
    Speaker.create("Jorge"),
    Speaker.create("Dario"),
    Speaker.create("Gise")
  ).collect(Collectors.toCollection(LinkedHashSet::new));

  @GetMapping("/presentes")
  public Set<Speaker> presentes() {
    return speakers;
  }

  @PostMapping("/entrar")
  public void entrar(@RequestParam String nombreDeSpeaker) {
    presentes.add(Speaker.create(nombreDeSpeaker));
  }

  @PostMapping("/salir")
  public void salir(@RequestParam String nombreDeSpeaker) {
    presentes.remove(Speaker.create(nombreDeSpeaker));
    desencolarse(nombreDeSpeaker);
  }

  @GetMapping("/cola")
  public Set<Speaker> cola() {
    return speakers;
  }

  @PostMapping("/encolarse")
  public void encolarse(@RequestParam String nombreDeSpeaker) {
    speakers.add(Speaker.create(nombreDeSpeaker));
  }

  @PostMapping("/desencolarse")
  public void desencolarse(@RequestParam String nombreDeSpeaker) {
    speakers.remove(Speaker.create(nombreDeSpeaker));
  }
}

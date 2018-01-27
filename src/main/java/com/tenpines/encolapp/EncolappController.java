package com.tenpines.encolapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Date: 27/01/18 - 11:08
 */
@RestController
public class EncolappController {

  @Autowired
  private Salon salon;

  @GetMapping("/presentes")
  public Set<Speaker> presentes() {
    return salon.obtenerPresentes();
  }

  @PostMapping("/entrar")
  public void entrar(@RequestParam String nombreDeSpeaker) {
    salon.ingresar(obtenerSpeaker(nombreDeSpeaker));
  }

  @PostMapping("/salir")
  public void salir(@RequestParam String nombreDeSpeaker) {
    salon.salir(obtenerSpeaker(nombreDeSpeaker));
  }

  private Speaker obtenerSpeaker(@RequestParam String nombreDeSpeaker) {
    return Speaker.create(nombreDeSpeaker);
  }

  @GetMapping("/cola")
  public Set<Speaker> cola() {
    return salon.obtenerCola();
  }

  @PostMapping("/encolarse")
  public void encolarse(@RequestParam String nombreDeSpeaker) {
    salon.encolar(obtenerSpeaker(nombreDeSpeaker));
  }

  @PostMapping("/desencolarse")
  public void desencolarse(@RequestParam String nombreDeSpeaker) {
    salon.desencolar(obtenerSpeaker(nombreDeSpeaker));
  }
}

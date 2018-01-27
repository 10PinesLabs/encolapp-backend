package com.tenpines.encolapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Date: 27/01/18 - 11:08
 */
@RestController
public class EncolappController {

  @Autowired
  private Salon salon;

  @GetMapping("/estado_actual")
  public Flux<EstadoDeSalon> estadoActual() {
    return salon.obtenerEstado();
  }

  @GetMapping("/presentes")
  public Flux<Speaker> presentes() {
    return salon.obtenerPresentes();
  }

  @PostMapping("/entrar")
  public void entrar(@RequestBody Speaker speaker) {
    salon.ingresar(speaker);
  }

  @PostMapping("/salir")
  public void salir(@RequestBody Speaker speaker) {
    salon.salir(speaker);
  }

  @GetMapping("/cola")
  public Flux<Speaker> cola() {
    return salon.obtenerCola();
  }

  @PostMapping("/encolarse")
  public void encolarse(@RequestBody Speaker speaker) {
    salon.encolar(speaker);
  }

  @PostMapping("/desencolarse")
  public void desencolarse(@RequestBody Speaker speaker) {
    salon.desencolar(speaker);
  }
}

package com.tenpines.encolapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Date: 27/01/18 - 13:17
 */
@Configuration
public class ConfiguracionDeSpring {

  @Bean
  public Salon salon() {
    return Salon.create();
  }
}

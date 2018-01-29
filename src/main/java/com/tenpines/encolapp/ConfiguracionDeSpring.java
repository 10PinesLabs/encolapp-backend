package com.tenpines.encolapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;

/**
 * Date: 27/01/18 - 13:17
 */
@Configuration
public class ConfiguracionDeSpring {

  @Bean
  public Salon salon() {
    return Salon.create(null);
  }


  @Bean
  public WebFluxConfigurer corsConfigurer() {
    return new WebFluxConfigurerComposite() {

      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
          .addMapping("/**")
          .allowedOrigins("*")
          .allowedMethods("*");
      }
    };
  }
}

package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	@Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
  return builder.routes()
    .route("aplicacao", r -> r.path("/aplicacao/**")
      .filters(f -> f.stripPrefix(1))
      .uri("lb://TF"))
    .route("asscache", r -> r.path("/asscache/**")
      .filters(f -> f.stripPrefix(1))
      .uri("lb://ASSCACHE"))
	    .build();
    
  }

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}

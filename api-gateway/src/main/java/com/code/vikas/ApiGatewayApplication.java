package com.code.vikas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r -> r.path("/products/**")
						.uri("lb://product-service"))
				.route(r -> r.path("/carts/**")
						.uri("lb://cart-service"))
				.route(r -> r.path("/orders/**")
						.uri("lb://order-service"))
				.build();
	}

}

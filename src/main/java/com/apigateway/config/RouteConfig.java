package com.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/product/**")
						.filters(f -> f.hystrix(c -> c.setName("hystrix").setFallbackUri("forward:/fallback/product")))
						.uri("lb://PRODUCT-SERVICE/")
						.id("product-service"))

				.route(r -> r.path("/user/**")
						.filters(f -> f.hystrix(c -> c.setName("hystrix").setFallbackUri("forward:/fallback/user")))
						.uri("lb://USER-SERVICE/")
						.id("user-service"))

				.route(r -> r.path("/auth/**")
						.filters(f -> f.hystrix(c -> c.setName("hystrix").setFallbackUri("forward:/fallback/auth")))
						.uri("lb://AUTH-SERVICE/")
						.id("auyh-service"))
				.build();
	}
}

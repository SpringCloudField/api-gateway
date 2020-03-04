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
				.route(r -> r.path("/api/v1/product/**")
						.filters(f -> f.rewritePath("/product/(?.*)", "/${remains}")
								.addRequestHeader("X-first-Header", "first-service-header")
								.hystrix(c -> c.setName("hystrix").setFallbackUri("forward:/fallback/product")))
						.uri("lb://PRODUCT-SERVICE/")
						.id("product-service"))

				.route(r -> r.path("/api/v1/user/**")
						.filters(f -> f.rewritePath("/user/(?.*)", "/${remains}")
								.hystrix(c -> c.setName("hystrix").setFallbackUri("forward:/fallback/user")))
						.uri("lb://USER-SERVICE/")
						.id("user-service"))
				.build();
	}
}

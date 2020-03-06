package com.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class HystrixController {

	@GetMapping("/product")
	public String productServiceFallback() {
		return "This is a fallback for product service. Service down.";
	}

	@GetMapping("/user")
	public String userServiceFallback() {
		return "This is a fallback for user service. Service down.";
	}

	@GetMapping("/auth")
	public String authServiceFallback() {
		return "This is a fallback for auth service. Service down.";
	}
}

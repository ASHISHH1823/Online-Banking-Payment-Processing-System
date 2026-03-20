package com.ashish.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.config>{
	public static class config{}

	@Override
	public GatewayFilter apply(config config) {
		
		return ;
	}

}

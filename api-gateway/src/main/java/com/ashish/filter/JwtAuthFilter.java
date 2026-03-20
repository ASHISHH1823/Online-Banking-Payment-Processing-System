package com.ashish.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.ashish.exceptionHandler.AccessDeniedException;
import com.ashish.exceptionHandler.UnauthorizedException;
import com.ashish.service.JwtService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.config>{
	private final JwtService jwtService;
	
	public static class config{}

	@Override
	public GatewayFilter apply(config config) {
		
		return (exchange,chain)->{
			String path = exchange.getRequest().getURI().getPath();
			// Allow auth APIs
			if (path.startsWith("/auth")) {
				return chain.filter(exchange);
			}
			// Check header
			String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
			if(authHeader==null || authHeader.startsWith("Bearer ")) {
				throw new UnauthorizedException("UnauthorizedException");
			}
			String token = authHeader.substring(7);
			Claims claims=jwtService.validateToken(token);
			String role=jwtService.extractRole(claims);
			if(path.contains("/activate") || path.contains("/block")) {
				if(!"ROLE_ADMIN".equals(role)) {
					throw new AccessDeniedException("Access Denied: ADMIN only");
				}
			}
			return chain.filter(exchange);
			
		};
	}

}

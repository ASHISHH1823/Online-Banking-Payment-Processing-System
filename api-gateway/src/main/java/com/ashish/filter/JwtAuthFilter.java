package com.ashish.filter;


import java.util.List;

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

public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    private final JwtService jwtService;
    
    public static class Config{}
    
    public JwtAuthFilter(JwtService jwtService) {
		super(Config.class);
		this.jwtService=jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            String path = exchange.getRequest().getURI().getPath();

            if (path.startsWith("/auth")) {
                return chain.filter(exchange);
            }

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            	exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
               // throw new UnauthorizedException("Missing or Invalid Authorization Header");
            	return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);
try {
	 Claims claims = jwtService.validateToken(token);
     String role = jwtService.extractRole(claims);
     String username = claims.getSubject();
     
     exchange = exchange.mutate()
             .request(exchange.getRequest().mutate()
             		.header(HttpHeaders.AUTHORIZATION, authHeader)
                     .header("User", username)
                     .header("Role", role)
                     .build())
             .build();
     List<String> adminPaths =List.of("/active","/block");
     if (adminPaths.stream().anyMatch(path::contains)) {
			if (!"ROLE_ADMIN".equals(role)) {
				//throw new AccessDeniedException("ADMIN only");
				 exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.FORBIDDEN);
				    return exchange.getResponse().setComplete();
			}
		}

     return chain.filter(exchange);
} catch (Exception e) {
	 e.printStackTrace(); 
     exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
     return exchange.getResponse().setComplete();
}
           
        };
    }
}
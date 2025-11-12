package com.cts.utility;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter{

	private final JwtUtil jwtUtil;
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		ServerHttpRequest request=exchange.getRequest();
		String authHeader=request.getHeaders().getFirst("Authorization");
		if(authHeader!=null&&authHeader.startsWith("Bearer ")) {
			String token=authHeader.substring(7);
			if(jwtUtil.isTokenValid(token)) {
				String userName=jwtUtil.extractUserName(token);
				String role=jwtUtil.extractRole(token);
				List<GrantedAuthority>authorities=List.of(new SimpleGrantedAuthority("ROLE_"+role));
				
				UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userName,null,authorities);
				
				return chain.filter(exchange)
						.contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
			}
		}
		return chain.filter(exchange);
	}
}

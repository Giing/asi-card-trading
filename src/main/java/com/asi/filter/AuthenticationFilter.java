package com.asi.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.impl.TextCodec;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
@Order(1)
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private List<String> excludeUrls = Arrays.asList("/api/user/login", "/api/user/register", "/api/sales", "/h2-console", "/api/rooms", "/api/rooms/create", "/api/rooms/join/0", "/index.html");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String authToken = request.getHeader("Authorization");
		// LOG.info("Starting a transaction for req : {}", authToken);
		if(authToken != null && !authToken.isEmpty() && isTokenValid(authToken.replace("Bearer ", ""))) {			
			filterChain.doFilter(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
		}
		
	}
	
	private final static Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
	  throws ServletException {
	    String path = request.getRequestURI();
	    return excludeUrls.contains(path) || path.matches(".*(css|jpg|png|gif|js|html|ico|woff2|map|jsp|do)");
	}
	
	private boolean isTokenValid(String token) {
		try {
			Jwts.parser()
				.setSigningKey(TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
				.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}

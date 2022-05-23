package com.asi.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private List<String> excludeUrls = Arrays.asList("/test");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String token = request.getHeader("Authorization");
		LOG.info("User token: ", token);
		filterChain.doFilter(request, response);
		LOG.info("Committing Transaction for req :{}", req.getRequestURI());
		
	}
	
	private final static Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
	  throws ServletException {
	    String path = request.getRequestURI();
	    return excludeUrls.contains(path) || path.matches(".*(css|jpg|png|gif|js|html)");
	}
	
}

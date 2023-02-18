package io.javabrains.springbootsecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


@Component
public class CustomHeaderAuthFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

        //if header is missing , send un-athorized error back
//        String authHeader = request.getHeader("X-HEADER");
//        if (authHeader.isEmpty()) {
//            response.setStatus(
//                HttpServletResponse.SC_UNAUTHORIZED);
//        } else {
//            chain.doFilter(servletRequest, servletResponse);
//        }
		
		System.out.println("Before UsernamePasswordAuthenticationFilter");
		chain.doFilter(servletRequest, servletResponse);
    }


	}


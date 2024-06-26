package org.hopenghou.SpringBootApi.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    @Value("${api.key}")
    private String apiKey;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // NOTE: preflight request with OPTIONS method will not have X-API-KEY header 
        if (request.getMethod().equals("OPTIONS")) {
            // Allow access to the endpoint
            filterChain.doFilter(request, response); 
            return;
        }

        String providedApiKey = request.getHeader("X-API-KEY");

        if (apiKey.equals(providedApiKey)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API key");
        }
    }
}
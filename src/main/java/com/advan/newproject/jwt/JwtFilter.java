package com.advan.newproject.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtFilter extends GenericFilterBean {

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        // Get authorization from Http request
        final String authHeader = request.getHeader("authorization");

        // If the Http request is OPTIONS then just return the status code 200
        // which is HttpServletResponse.SC_OK in this code
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {

            // Check the authorization, check if the token is started by "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                throw new ServletException("Missing or invalid Authorization header");
            }

            // Then get the JWT token from authorization
            final String token = authHeader.substring(7);

            try {
                JwtUtil.verify(token);
            } catch (final JWTVerificationException e) {
                System.out.println(e.getMessage());
                ((HttpServletResponse) res).sendError(401,e.getMessage());
                return;
//                throw new ServletException(e.getMessage());
            }

            chain.doFilter(req, res);
        }
    }
}
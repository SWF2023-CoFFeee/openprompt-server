package com.openpromt.coffeee.swf2023.openpromtserver.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
if(request.getHeader("Authorization") != null && !request.getRequestURI().equals("/api/v2/user/register") && !request.getRequestURI().equals("/api/v2/user/login")){
            String accessToken = "";
            String header = request.getHeader("Authorization");
            System.out.println("header : " + header);
            String[] temp = accessToken.split(" ");
            accessToken = temp[1];
            if(accessToken == null || accessToken.length() == 0)
                response.setStatus(403);
            if(jwtProvider.validateToken(accessToken)){
                Authentication authentication = jwtProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

package com.openpromt.coffeee.swf2023.openpromtserver.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(401);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("인증되지 않은 사용자입니다.");
        response.setHeader("error", "authentication Error");
    }
}
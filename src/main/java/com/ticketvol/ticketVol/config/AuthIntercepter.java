package com.ticketvol.ticketVol.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String utilisateur = (String) request.getSession().getAttribute("utilisateur");
        if (utilisateur == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}

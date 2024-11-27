package com.metacoding.autoblog._core.filter;

import com.metacoding.autoblog.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            response.sendRedirect("/login-form");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}

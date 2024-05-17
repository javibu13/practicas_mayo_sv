package org.example.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        String loginURI = req.getContextPath() + "/login.jsp";

        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean staticResource = req.getRequestURI().startsWith(req.getContextPath() + "/static/");

        if (loggedIn || loginRequest || staticResource) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(loginURI);
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException { }

    @Override
    public void destroy() { }
}

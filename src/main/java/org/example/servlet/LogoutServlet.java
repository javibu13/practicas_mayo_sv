package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/Videoclub");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error loading login view", e);
        }
    }
}
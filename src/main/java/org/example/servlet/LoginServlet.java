package org.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.dao.Database;
import org.example.dao.UsersDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.setAttribute("headTitle", "Login");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error loading login view", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);
        System.out.println(password);
        
        try {
            boolean emailExists = Database.getInstance().withExtension(UsersDao.class, dao -> {
                return dao.emailExists(email);
            });

            if (emailExists) {
                // Implement the login logic here
                // Database.getInstance().withExtension(UsersDao.class, dao -> {
                //     dao.addUser(firstName, lastName, email, phoneNumber, password);
                //     return null;
                // });
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("success");
            } else {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("Account not registered");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Registration failed");
        }
    }
}
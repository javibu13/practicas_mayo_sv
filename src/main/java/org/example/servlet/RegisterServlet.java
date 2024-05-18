package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.dao.Database;
import org.example.dao.UsersDao;

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        try {
            boolean emailExists = Database.getInstance().withExtension(UsersDao.class, dao -> {
                return dao.emailExists(email);
            });

            if (emailExists) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("Email already in use");
            } else {
                Database.getInstance().withExtension(UsersDao.class, dao -> {
                    dao.addUser(firstName, lastName, email, phoneNumber, password);
                    return null;
                });
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("success");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Registration failed");
        }
    }
}


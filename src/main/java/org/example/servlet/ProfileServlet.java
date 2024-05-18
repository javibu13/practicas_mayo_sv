package org.example.servlet;

import org.example.dao.Database;
import org.example.dao.UsersDao;
import org.example.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");

        if (idUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            User user = Database.getInstance().withExtension(UsersDao.class, dao -> dao.getUserById(idUser));
            request.setAttribute("user", user);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving user details", e);
        }
    }
}

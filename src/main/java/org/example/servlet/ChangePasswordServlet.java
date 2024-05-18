package org.example.servlet;

import org.example.dao.Database;
import org.example.dao.UsersDao;
import org.example.util.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession(false);
        Integer idUser = (Integer) session.getAttribute("idUser");

        if (idUser == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            Database.getInstance().useHandle(handle -> {
                UsersDao dao = handle.attach(UsersDao.class);
                String storedPassword = dao.getPasswordByUserId(idUser);

                if (currentPassword.equals(storedPassword)) {
                    dao.updatePassword(idUser, newPassword);
                } else{
                    throw new Exception("The current password is incorrect.");
                }

            });
            response.sendRedirect("profile.jsp?success=" + URLEncoder.encode("Password updated successfully", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("profile.jsp?error=" + URLEncoder.encode("Error changing the password: " + e.getMessage(), "UTF-8"));
        }
    }
}


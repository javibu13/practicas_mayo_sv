package org.example.servlet;

import org.example.dao.Database;
import org.example.dao.LoansDao;
import org.example.domain.Loan;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listLoans")
public class ListLoansServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");

        if (idUser == null) {
            response.sendRedirect("login");
            return;
        }

        Jdbi jdbi;
        try {
            jdbi = Database.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Loan> loans = jdbi.withHandle(handle -> {
            LoansDao loansDao = handle.attach(LoansDao.class);
            return loansDao.findLoansByidUser(idUser);
        });

        request.setAttribute("loans", loans);
        request.getRequestDispatcher("loans.jsp").forward(request, response);
    }
}

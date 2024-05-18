package org.example.servlet;

import org.example.dao.Database;
import org.example.dao.LoansDao;
import org.example.dao.MoviesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/loanMovie")
public class LoanMovie extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("idUser") == null) {
            // Redirect to login if the user is not logged in
            response.sendRedirect("/login?message=" + URLEncoder.encode("Please, sign in to loan a movie.", StandardCharsets.UTF_8));
            return;
        }
        if (request.getParameter("idLoan") != null) {
            // Modifying loan using idLoan
            try {
            Database.getInstance().inTransaction(handle -> {
                int idLoan = Integer.parseInt(request.getParameter("idLoan"));
                LoansDao loansDao = handle.attach(LoansDao.class);
                java.sql.Date returnDate = new java.sql.Date(System.currentTimeMillis());
                loansDao.returnLoanById(idLoan, returnDate);
                return null;
                });
            response.sendRedirect("listLoans");
            } catch (Exception e) {
                response.sendRedirect("error?message=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
            }
        } else {
            // Modifying loan using idUser and idMovie
            int idMovie = Integer.parseInt(request.getParameter("idMovie"));
            int idUser = Integer.parseInt(request.getSession().getAttribute("idUser").toString());
            String action = request.getParameter("action");

            try {
                Database.getInstance().inTransaction(handle -> {
                    LoansDao loansDao = handle.attach(LoansDao.class);
                    MoviesDao moviesDao = handle.attach(MoviesDao.class);

                    if ("rent".equals(action)) {
                        int getActualStock = moviesDao.getActualStock(idMovie);
                        if (getActualStock == 0) {
                            throw new IllegalStateException("Can't process the loan, there is no stock available");
                        }

                        java.sql.Date startDate = new java.sql.Date(System.currentTimeMillis());
                        java.sql.Date expectedDate = new java.sql.Date(System.currentTimeMillis() + 604800000); // 7 days
                        loansDao.addLoan(idMovie, idUser, startDate, expectedDate);
                    } else if ("return".equals(action)) {
                        java.sql.Date returnDate = new java.sql.Date(System.currentTimeMillis());
                        loansDao.returnLoan(idMovie, idUser, returnDate);
                    }

                    return null;
                });
                response.sendRedirect("listMovies");
            } catch (Exception e) {
                response.sendRedirect("error?message=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
            }
        }

    }
}

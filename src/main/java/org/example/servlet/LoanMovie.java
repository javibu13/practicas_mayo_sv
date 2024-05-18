package org.example.servlet;

import oracle.jdbc.proxy.annotation.Post;
import org.example.dao.Database;
import org.example.dao.LoansDao;
import org.example.dao.MoviesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/loanMovie")
public class LoanMovie extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        if (request.getSession().getAttribute("idUser") == null){
           // si no hay login
           response.sendRedirect("/login"+ URLEncoder.encode("Please, sign in to loan a movie.", StandardCharsets.UTF_8));
           return;
        }
        int idMovie = Integer.parseInt(request.getParameter("idMovie"));
        int idUser = Integer.parseInt(request.getSession().getAttribute("idUser").toString());
        java.sql.Date startDate = new java.sql.Date(System.currentTimeMillis());
        java.sql.Date expectedDate = new java.sql.Date(System.currentTimeMillis()+604800000);
        String status = "Start Loan";

        try{
        Database.getInstance().inTransaction(handle -> {
            LoansDao loansDao = handle.attach(LoansDao.class);
            MoviesDao moviesDao = handle.attach(MoviesDao.class);

            int getActualStock = moviesDao.getActualStock(idMovie);
            if (getActualStock == 0) {
                throw new IllegalStateException("Can't proccess the loan, there is no stock available");
            }

                loansDao.addLoan(idMovie,idUser,startDate,expectedDate);
                return null;
            });
        response.sendRedirect("listMovies");
        }catch (Exception e){

            response.sendRedirect("error" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
        }
    }
}





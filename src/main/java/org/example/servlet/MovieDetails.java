package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.example.dao.Database;
import org.example.dao.LoansDao;
import org.example.dao.MoviesDao;
import org.example.domain.Movie;

@WebServlet("/movieDetails")
public class MovieDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movieId = request.getParameter("id");
        Integer userId = (Integer) request.getSession().getAttribute("idUser");
        if (movieId != null) {
            try {
                Movie movie = Database.getInstance().withExtension(MoviesDao.class, dao -> dao.getMovie(Integer.parseInt(movieId)));
                int movieAvailable = Database.getInstance().withExtension(MoviesDao.class, dao -> dao.getActualStock(Integer.parseInt(movieId)));
                if (userId != null) {
                    int activeLoans = Database.getInstance().withExtension(LoansDao.class, dao -> dao.countActiveLoansByUser(Integer.parseInt(movieId), userId));
                    movie.setRentedByUser(activeLoans > 0);
                }
                request.setAttribute("movie", movie);
                String stringStaticPath = request.getContextPath() + "/static/";
                request.setAttribute("staticPath", stringStaticPath);
                request.setAttribute("availableStock", movieAvailable);
                request.getRequestDispatcher("/movieDetail.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error retrieving movie details", e);
            }
        } else {
            response.sendRedirect("/listMovies");
        }
    }
}

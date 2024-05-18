package org.example.servlet;

import org.example.dao.Database;
import org.example.dao.MoviesDao;
import org.example.domain.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listMovies")
public class ListMovies  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try{
            List<Movie> movies = Database.getInstance().withExtension(MoviesDao.class, MoviesDao::getAllMovies);
            request.setAttribute("movies", movies);
            String stringStaticPath = request.getContextPath() + "/static/";
            request.setAttribute("staticPath", stringStaticPath);
            request.getRequestDispatcher("/listMovies.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
            throw new ServletException("Error retrieving movies", e);
        }
        }
    }

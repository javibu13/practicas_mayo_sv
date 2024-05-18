package org.example.dao;

import org.example.dao.MoviesMapper;
import org.example.domain.Movie;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;

public interface MoviesDao {
    @SqlQuery("SELECT * FROM MOVIE")
    @UseRowMapper(MoviesMapper.class)
    List<Movie> getAllMovies();

    @SqlQuery("SELECT * FROM MOVIE WHERE idMovie LIKE CONCAT('%',:searchTerm,'%') " +
            "OR title LIKE CONCAT('%',:searchTerm,'%') OR director LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(MoviesMapper.class)
    List<Movie> getMovie(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT * FROM MOVIES WHERE idMovie = ?")
    @UseRowMapper(MoviesMapper.class)
    Movie getMovie(int idMovie);

    @SqlUpdate ("INSERT INTO MOVIES (idMovie, title, director, synopsis, trailer,quantity,path) VALUES (?, ?, ?, ?, ?, ?,?)")
    int addMovie(int idMovie, String title, String director, String synopsis, String trailer, int quantity,String path);

    @SqlUpdate("UPDATE MOVIES SET idMovie = ?, title = ?, director = ?, synopsis = ?, trailer = ?,quantity = ?, path = ? WHERE idMovie = ?")
    int updateMovie(int idMovie, String title, String director, String synopsis, String trailer, int quantity,String path);

    @SqlUpdate("DELETE FROM MOVIES WHERE idMovie = ?")
    int removeMovie(int idMovie);

    @SqlUpdate("SELECT " +
            "    (SELECT quantity FROM MOVIES WHERE idMovie = :idMovie) - " +
            "    (SELECT COUNT(idLoan) FROM LOANS WHERE idMovie = :idMovie AND returnDate IS NULL) AS available_quantity" +
            "FROM DUAL")
    int getActualStock(@Bind("idMovie") int idMovie);

    @SqlUpdate("UPDATE movies SET quantity = quantity + 1 WHERE idMovie = :idMovie")
    void increaseMoviesQuantity(@Bind("idMovie") int idMovie);

}

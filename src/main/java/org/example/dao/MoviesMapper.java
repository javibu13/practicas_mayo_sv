package org.example.dao;


import org.example.domain.Movie;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoviesMapper implements RowMapper<Movie>{

    @Override
    public Movie map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Movie(rs.getInt("idMovie"),
                rs.getString("title"),
                rs.getString("director"),
                rs.getString("synopsis"),
                rs.getString("trailer"),
                rs.getInt("quantity"),
                rs.getString("path")

        );
    }
}

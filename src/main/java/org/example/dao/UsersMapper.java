package org.example.dao;
import org.example.domain.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<User>{
    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new User(rs.getInt("idUser"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("phoneNumber"),
                rs.getString("password")
        );
    }
}

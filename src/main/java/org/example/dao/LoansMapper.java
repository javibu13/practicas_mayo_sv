package org.example.dao;

import org.example.dao.LoansDao;
import org.example.domain.Loan;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoansMapper implements RowMapper<Loan> {

    @Override
    public Loan map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Loan(rs.getInt("idLoan"),
                rs.getInt("idUser"),
                rs.getInt("idMovie"),
                rs.getDate("startDate"),
                rs.getDate("expectedDate"),
                rs.getDate("returnDate")
        );
    }

}

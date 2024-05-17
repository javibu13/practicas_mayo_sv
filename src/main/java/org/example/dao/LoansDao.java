package org.example.dao;

import org.example.domain.Loan;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.Date;
import java.util.List;

public interface LoansDao {

    @SqlQuery("SELECT * FROM LOANS")
    @UseRowMapper(LoansMapper.class)
    List<Loan> getAllLoans();

    @SqlQuery("SELECT * FROM LOANS WHERE idLoan LIKE CONCAT('%',:searchTerm,'%') " +
            "OR idBook LIKE CONCAT('%',:searchTerm,'%') OR idUser LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(LoansMapper.class)
    List<Loan> getLoans(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT * FROM LOANS WHERE idLoan = ?")
    @UseRowMapper(LoansMapper.class)
    Loan getLoan(int idLoan);

    @SqlUpdate ("INSERT INTO LOANS (idLoan, idBook, idUser, startDate,expectedDate, returnDate) VALUES (?, ?, ?, ?, ?, ?)")
    int addLoan(int idLoan, int idBook, int idUser, Date loanDate,Date expectedDate, Date returnDate);

    @SqlUpdate("UPDATE LOANS SET idLoan = ?, idBook = ?, idUser = ?, startDate = ?, expectedDate = ?, returnDate = ? WHERE idLoan = ?")
    int updateLoan(int idLoan, int idBook, int idUser, Date loanDate, Date expectedDate, Date returnDate);

    @SqlUpdate("DELETE FROM LOANS WHERE idLoan = ?")
    int removeLoan(int idLoan);

}

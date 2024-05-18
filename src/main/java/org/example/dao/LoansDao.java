package org.example.dao;

import org.example.domain.Loan;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
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
            "OR idMovie LIKE CONCAT('%',:searchTerm,'%') OR idUser LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(LoansMapper.class)
    List<Loan> getLoans(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT * FROM LOANS WHERE idLoan = ?")
    @UseRowMapper(LoansMapper.class)
    Loan getLoan(int idLoan);

    @SqlUpdate ("INSERT INTO LOANS (idLoan, idMovie, idUser, startDate,expectedDate, returnDate) VALUES (?, ?, ?, ?, ?, ?)")
    int addLoan(int idLoan, int idMovie, int idUser, Date startDate, Date expectedDate, Date returnDate);

    @SqlUpdate("UPDATE LOANS SET idLoan = ?, idMovie = ?, idUser = ?, startDate = ?, expectedDate = ?, returnDate = ? WHERE idLoan = ?")
    int updateLoan(int idLoan, int idMovie, int idUser, Date startDate, Date expectedDate, Date returnDate);

    @SqlUpdate("DELETE FROM LOANS WHERE idLoan = ?")

    int removeLoan(int idLoan);
    @SqlQuery("SELECT l.*, m.title as movieTitle FROM loans l JOIN movies m ON l.idMovie = m.idMovie WHERE l.Iduser = :idUser")
    @RegisterRowMapper(LoansMapper.class)
    List<Loan> findLoansByidUser(@Bind("idUser") int idUser);

    @SqlUpdate("UPDATE loans SET returnDate = :returnDate WHERE idLoan = :idLoan AND returnDate IS NULL")
    boolean updateReturnDate(@Bind("idLoan") int idLoan, @Bind("returnDate") java.sql.Date returnDate);


}

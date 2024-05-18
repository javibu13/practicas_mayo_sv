package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private int idLoan;
    private int idMovie;
    private int idUser;
    private Date startDate;
    private Date expectedDate;
    private Date returnDate;
    private String movieTitle;
}
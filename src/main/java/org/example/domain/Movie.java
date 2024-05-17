package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private int idMovie;
    private String tittle;
    private String director;
    private String Trailer;
    private int quantity;
    private String Photo;

}

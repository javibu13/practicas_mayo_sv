package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private int idMovie;
    private String title;
    private String director;
    private String synopsis;
    private String trailer;
    private int quantity;
    private String path;
    private boolean isRentedByUser;

    public boolean isRentedByUser() {
        return isRentedByUser;
    }

    public void setRentedByUser(boolean rentedByUser) {
        isRentedByUser = rentedByUser;
    }
}

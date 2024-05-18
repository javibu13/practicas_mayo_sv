package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int idUser; //
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}

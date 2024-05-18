package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Método para hashear la contraseña antes de almacenarla
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    // Método para verificar la contraseña
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
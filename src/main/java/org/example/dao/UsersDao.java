package org.example.dao;


import org.example.domain.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;
public interface UsersDao {

    @SqlQuery("SELECT * FROM USERS")
    @UseRowMapper(UsersMapper.class)
    List<User> getAllUsers();

    @SqlQuery("SELECT * FROM USERS WHERE idUser LIKE CONCAT('%',:searchTerm,'%') " +
            "OR firstName LIKE CONCAT('%',:searchTerm,'%') OR lastName LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(UsersMapper.class)
    List<User> getUsers(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT idUser, firstName, lastName, email, phoneNumber, password FROM users WHERE idUser = :idUser")
    User getUserById(@Bind("idUser") int idUser);
    @SqlQuery("SELECT * FROM USERS WHERE idUser = ?")
    @UseRowMapper(UsersMapper.class)
    User getUser(int idUser);

    @SqlQuery("SELECT * FROM USERS WHERE email = :email")
    @UseRowMapper(UsersMapper.class)
    User getUserByEmail(@Bind("email") String email);

    @SqlUpdate ("INSERT INTO USERS (firstName, lastName, email, phoneNumber, password) VALUES (?, ?, ?, ?, ?)")
    void addUser(String firstName, String lastName, String email, String phoneNumber, String password);

    @SqlUpdate("UPDATE USERS SET idUser = ?, firstName = ?, lastName = ?, email = ?, phoneNumber = ?, password = ? WHERE idUser = ?")
    void updateUser(int idUser, String firstName, String lastName, String email, String phoneNumber, String password);

    @SqlUpdate("DELETE FROM USERS WHERE idUser = ?")
    void removeUser(int idUser);

    @SqlQuery("SELECT password FROM users WHERE idUser = :idUser")
    String getPasswordByUserId(@Bind("idUser") int idUser);

    @SqlUpdate("UPDATE users SET password = :password WHERE idUser = :idUser")
    void updatePassword(@Bind("idUser") int idUser, @Bind("password") String password);
    
    @SqlQuery("SELECT COUNT(*) FROM users WHERE email = :email")
    boolean emailExists(@Bind("email") String email);
  }

package org.example.dao;

import org.example.domain.User;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(UsersMapper.class)
public interface UsersDao {

    @SqlQuery("SELECT * FROM USERS")
    List<User> getAllUsers();

    @SqlQuery("SELECT * FROM USERS WHERE idUser LIKE CONCAT('%',:searchTerm,'%') " +
            "OR firstName LIKE CONCAT('%',:searchTerm,'%') OR lastName LIKE CONCAT('%',:searchTerm,'%')")
    List<User> getUsers(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT idUser, firstName, lastName, email, phoneNumber, password FROM users WHERE idUser = :idUser")
    User getUserById(@Bind("idUser") int idUser);

    @SqlQuery("SELECT * FROM USERS WHERE idUser = :idUser")
    User getUser(@Bind("idUser") int idUser);

    @SqlQuery("SELECT * FROM USERS WHERE email = :email")
    User getUserByEmail(@Bind("email") String email);

    @SqlUpdate("INSERT INTO USERS (firstName, lastName, email, phoneNumber, password) VALUES (:firstName, :lastName, :email, :phoneNumber, :password)")
    void addUser(@Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("email") String email, @Bind("phoneNumber") String phoneNumber, @Bind("password") String password);

    @SqlUpdate("UPDATE USERS SET firstName = :firstName, lastName = :lastName, email = :email, phoneNumber = :phoneNumber, password = :password WHERE idUser = :idUser")
    void updateUser(@Bind("idUser") int idUser, @Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("email") String email, @Bind("phoneNumber") String phoneNumber, @Bind("password") String password);

    @SqlUpdate("DELETE FROM USERS WHERE idUser = :idUser")
    void removeUser(@Bind("idUser") int idUser);

    @SqlQuery("SELECT password FROM users WHERE idUser = :idUser")
    String getPasswordByUserId(@Bind("idUser") int idUser);

    @SqlUpdate("UPDATE users SET password = :password WHERE idUser = :idUser")
    void updatePassword(@Bind("idUser") int idUser, @Bind("password") String password);

    @SqlQuery("SELECT COUNT(*) FROM users WHERE email = :email")
    boolean emailExists(@Bind("email") String email);
}

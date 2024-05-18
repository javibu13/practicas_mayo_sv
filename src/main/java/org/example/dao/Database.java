package org.example.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import static org.example.util.Constants.*;

public class Database {
    public static Jdbi jdbi = null;

    public static Jdbi getInstance() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        if (jdbi == null) {
            jdbi = Jdbi.create(CONNECTION_STRING, USERNAME, PASSWORD);
            jdbi.installPlugin(new SqlObjectPlugin());
        }
        return jdbi;
    }
}
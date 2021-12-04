package ch.teko.prg3.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static final String DB_NAME = "kontakt.sqlite";
    // Pfad zur DB
    public static final String PATH = "/Users/glausm/Desktop/";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + PATH + DB_NAME;

    private Connection connection;
    private Statement statement;

    public boolean open() {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}


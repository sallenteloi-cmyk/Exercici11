package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBD {

    // URL de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/tpv_botiga";

    // Usuario MySQL
    private static final String USER = "root";

    // Contraseña MySQL
    private static final String PASSWORD = "marc93biel2";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);

    }
}
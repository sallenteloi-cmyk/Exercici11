package dao;

import utils.ConnexioBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {

    // Comprova si existeix un client a la base de dades a partir del seu DNI
    public boolean existeixClient(String dni) {

        // Consulta SQL per buscar un client concret
        String sql = "SELECT dni FROM clients WHERE dni = ?";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Substituïm el primer interrogant pel DNI introduït
            ps.setString(1, dni);

            // Executem la consulta SELECT
            ResultSet rs = ps.executeQuery();

            // Si hi ha algun resultat, vol dir que el client existeix
            return rs.next();

        } catch (SQLException e) {

            System.out.println("Error comprovant el client");

            e.printStackTrace();

            return false;
        }
    }
}
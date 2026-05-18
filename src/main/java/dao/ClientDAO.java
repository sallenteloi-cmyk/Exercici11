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

    // Mostra el resum de vendes d'un client
    public void consultarVendesPerClient(String dniClient) {

        String sql = "SELECT c.dni, c.nom, COUNT(t.id) AS nombre_tiquets, " +
                "IFNULL(SUM(t.total_final), 0) AS total_despesa " +
                "FROM clients c " +
                "LEFT JOIN tiquets t ON c.dni = t.dni_client " +
                "WHERE c.dni = ? " +
                "GROUP BY c.dni, c.nom";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dniClient);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n===== CONSULTA VENDES PER CLIENT =====");
                System.out.println("DNI: " + rs.getString("dni"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Nombre de tiquets: " + rs.getInt("nombre_tiquets"));
                System.out.println("Total despesa: " + rs.getDouble("total_despesa") + " €");

            } else {

                System.out.println("Aquest client no existeix.");
            }

        } catch (SQLException e) {

            System.out.println("Error consultant vendes per client");
            e.printStackTrace();
        }
    }

}
package dao;

import utils.ConnexioBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TiquetDAO {

    // Crear un nou tiquet
    public int crearTiquet(String dniClient) {

        String sql = "INSERT INTO tiquets " +
                "(data_compra, dni_client, total_base, total_iva, total_final) " +
                "VALUES (CURDATE(), ?, 0, 0, 0)";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dniClient);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println("Error creant el tiquet");
            e.printStackTrace();
        }

        return -1;
    }

    // Actualitzar els totals del tiquet
    public void actualitzarTotalsTiquet(int idTiquet, double totalBase, double totalIva, double totalFinal) {

        String sql = "UPDATE tiquets SET total_base = ?, total_iva = ?, total_final = ? WHERE id = ?";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, totalBase);
            ps.setDouble(2, totalIva);
            ps.setDouble(3, totalFinal);
            ps.setInt(4, idTiquet);

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error actualitzant totals del tiquet");
            e.printStackTrace();
        }
    }
}
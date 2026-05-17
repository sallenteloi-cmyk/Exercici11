package dao;

import utils.ConnexioBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LineaFacturaDAO {

    // Inserir una línia de factura amb els preus calculats
    public boolean inserirLiniaFactura(int idTiquet, int idArticle, int quantitat,
                                       double preuBase, int iva, double preuFinal) {

        String sql = "INSERT INTO linies_factura " +
                "(id_tiquet, id_article, quantitat, preu_base, iva, preu_final) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTiquet);
            ps.setInt(2, idArticle);
            ps.setInt(3, quantitat);
            ps.setDouble(4, preuBase);
            ps.setInt(5, iva);
            ps.setDouble(6, preuFinal);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error inserint línia factura");
            e.printStackTrace();

            return false;
        }
    }
}
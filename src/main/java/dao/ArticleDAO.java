package dao;

import utils.ConnexioBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleDAO {

    // Comprova si existeix un article a la base de dades
    public boolean existeixArticle(int idArticle) {

        String sql = "SELECT id FROM articles WHERE id = ?";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idArticle);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println("Error comprovant l'article");
            e.printStackTrace();

            return false;
        }
    }

    // Obté l'stock actual d'un article
    public int obtenirStockArticle(int idArticle) {

        String sql = "SELECT stock FROM articles WHERE id = ?";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idArticle);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("stock");
            }

        } catch (SQLException e) {

            System.out.println("Error obtenint l'stock de l'article");
            e.printStackTrace();
        }

        return -1;
    }

    // Insereix un article nou o actualitza un article existent
    public void inserirOActualitzarArticle(int id, String nom, String familia,
                                           Integer tallaColl, Integer ampladaPit,
                                           Integer tallaCintura, Integer llargadaCamal,
                                           double preuBase, int iva, int stock) {

        String sql = "INSERT INTO articles " +
                "(id, nom, familia, talla_coll, amplada_pit, talla_cintura, llargada_camal, preu_base, iva, stock) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "nom = VALUES(nom), " +
                "familia = VALUES(familia), " +
                "talla_coll = VALUES(talla_coll), " +
                "amplada_pit = VALUES(amplada_pit), " +
                "talla_cintura = VALUES(talla_cintura), " +
                "llargada_camal = VALUES(llargada_camal), " +
                "preu_base = VALUES(preu_base), " +
                "iva = VALUES(iva), " +
                "stock = VALUES(stock)";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, nom);
            ps.setString(3, familia);
            ps.setObject(4, tallaColl);
            ps.setObject(5, ampladaPit);
            ps.setObject(6, tallaCintura);
            ps.setObject(7, llargadaCamal);
            ps.setDouble(8, preuBase);
            ps.setInt(9, iva);
            ps.setInt(10, stock);

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error inserint o actualitzant article");
            e.printStackTrace();
        }
    }

    // Actualitzar stock després d'una venda
    public void actualitzarStock(int idArticle, int quantitat) {

        String sql = "UPDATE articles SET stock = stock - ? WHERE id = ?";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantitat);
            ps.setInt(2, idArticle);

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error actualitzant stock");

            e.printStackTrace();
        }
    }

    // Obté el preu base d'un article
    public double obtenirPreuBaseArticle(int idArticle) {

        String sql = "SELECT preu_base FROM articles WHERE id = ?";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idArticle);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("preu_base");
            }

        } catch (SQLException e) {

            System.out.println("Error obtenint el preu base de l'article");
            e.printStackTrace();
        }

        return -1;
    }

    // Obté l'IVA d'un article
    public int obtenirIvaArticle(int idArticle) {

        String sql = "SELECT iva FROM articles WHERE id = ?";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idArticle);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("iva");
            }

        } catch (SQLException e) {

            System.out.println("Error obtenint l'IVA de l'article");
            e.printStackTrace();
        }

        return -1;
    }

    // Mostra el resum de vendes d'un article
    public void consultarVendesPerArticle(int idArticle) {

        String sql = "SELECT a.id, a.nom, IFNULL(SUM(l.quantitat), 0) AS quantitat_venuda " +
                "FROM articles a " +
                "LEFT JOIN linies_factura l ON a.id = l.id_article " +
                "WHERE a.id = ? " +
                "GROUP BY a.id, a.nom";

        try (Connection conn = ConnexioBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idArticle);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n===== CONSULTA VENDES PER ARTICLE =====");
                System.out.println("ID article: " + rs.getInt("id"));
                System.out.println("Nom article: " + rs.getString("nom"));
                System.out.println("Quantitat venuda: " + rs.getInt("quantitat_venuda"));

            } else {

                System.out.println("Aquest article no existeix.");
            }

        } catch (SQLException e) {

            System.out.println("Error consultant vendes per article");
            e.printStackTrace();
        }
    }
}
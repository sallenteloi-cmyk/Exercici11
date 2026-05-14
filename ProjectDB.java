import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProjectDB {


    private static final String URL = "jdbc:mysql://localhost:3306/tpv_botiga";
    private static final String USER = "root";
    private static final String PASSWORD = "roures2005";
    public static Connection conn;

    Scanner e = new Scanner(System.in);
    // CONNEXIÓ BBDD
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void conexBBDD() {

        try (Connection conn = getConnection()) {

            System.out.println("Connexió correcta a la BBDD");

        } catch (Exception e) {

            System.out.println("Error de connexió");
            e.printStackTrace();
        }
    }
    static int id;
    public static void modificar1(String nom, int id) throws SQLException {

        String sql = "UPDATE usuarios SET nom=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setInt(2, id);
            ps.executeUpdate();
    }
    public static void modificar2(String familia, int id) throws SQLException {
        String sql = "UPDATE usuarios SET familia=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, familia);
        ps.setInt(2, id);
        ps.executeUpdate();

    }
    public static void modificar3(int preu,int id) throws SQLException {
        String sql = "UPDATE usuarios SET preu_base=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, preu);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    public static void modificar4(int stock,int id) throws SQLException {
        String sql = "UPDATE usuarios SET stock=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, stock);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    public static void modificar5(int talla,int id) throws SQLException {
        String sql = "UPDATE usuarios SET talla_coll=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, talla);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    public static void modificar6(int pit, int id) throws SQLException {
        String sql = "UPDATE usuarios SET amplada_pit=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pit);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    public static void modificar7(int cintura, int id) throws SQLException {
        String sql = "UPDATE usuarios SET amplada_pit=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, cintura);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    public static void modificar8(int camal, int id) throws SQLException {
        String sql = "UPDATE usuarios SET llargada_camal=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, camal);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
}

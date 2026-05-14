import service.JsonService;
import service.MenuService;
import utils.ConnexioBD;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try {

            // Crear connexió amb la base de dades
            Connection conn = ConnexioBD.getConnection();

            // Mostrar missatge de connexió correcta
            System.out.println("Connexió correcta a la BBDD");

            // Crear objecte JsonService
            JsonService jsonService = new JsonService();

            // Llegir el fitxer JSON
            jsonService.llegirJson();

            // Crear objecte MenuService
            MenuService menuService = new MenuService();

            // Executar menú principal
            menuService.principal();

        } catch (SQLException e) {

            // Mostrar error de connexió
            System.out.println("Error de connexió");

            e.printStackTrace();
        }
    }
}
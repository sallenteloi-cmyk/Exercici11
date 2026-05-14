import java.sql.Connection;
import java.sql.*;
import java.io.FileReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TreballFinal {



    Scanner e = new Scanner(System.in);

    public static void main(String[] args) {
        TreballFinal p = new TreballFinal();
        p.principal();
    }

    public void principal() {

        ProjectDB.conexBBDD();
        ProjectJSON.conexJson();

        int opcio;

        do {

            menu();
            opcio = e.nextInt();

            switch (opcio) {

                case 1:
                    menuArticle();
                    gestionarArticles();
                    break;

                case 2:
                    menuClients();
                    gestionarClients();
                    break;

                case 3:
                    System.out.println("Gestió de Vendes-Tiquets");
                    break;

                case 4:
                    System.out.println("Gestió de línies de factura");
                    break;

                case 5:
                    System.out.println("Sortint del programa...");
                    break;

                default:
                    System.out.println("Opció no vàlida");
            }

        } while (opcio != 5);
    }


    // LLEGIR JSON


    public void menu() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Articles");
        System.out.println("2. Clients");
        System.out.println("3. Vendes-Tiquets");
        System.out.println("4. Vendes-Línies de factura");
        System.out.println("5. Sortir");
    }

    public void menuArticle() {
        System.out.println("\n===== MENU ARTICLES =====");
        System.out.println("1. Donar d'alta");
        System.out.println("2. Modificar");
        System.out.println("3. Esborrar");
        System.out.println("4. Tornar");
    }

    public void menuClients() {
        System.out.println("\n===== MENU CLIENTS =====");
        System.out.println("1. Donar d'alta");
        System.out.println("2. Modificar");
        System.out.println("3. Esborrar");
        System.out.println("4. Tornar");
    }

    public void gestionarArticles() {

        int opcion = e.nextInt();

        switch (opcion) {

            case 1 -> System.out.println("Alta article");
            case 2 -> System.out.println("Modificar article");
            case 3 -> System.out.println("Esborrar article");
            case 4 -> System.out.println("Tornant...");
            default -> System.out.println("Opció no vàlida");
        }
    }

    public void gestionarClients() {

        int opcion = e.nextInt();

        switch (opcion) {

            case 1 -> System.out.println("Alta client");
            case 2 -> System.out.println("Modificar client");
            case 3 -> System.out.println("Esborrar client");
            case 4 -> System.out.println("Tornant...");
            default -> System.out.println("Opció no vàlida");
        }
    }
    public void modificarart() throws SQLException {

        int opcion = e.nextInt();

        System.out.println("1.Modificar nom");
        System.out.println("2.Modificar familia");
        System.out.println("3.Modificar preu base");
        System.out.println("4.Modificar stock");
        System.out.println("5.Modificar talla del coll");
        System.out.println("6.Modificar amplada de pit");
        System.out.println("7.Modificar talla de cintura");
        System.out.println("8.Modificar llargada del camal");
        int id;
        switch (opcion) {

            case 1:
                System.out.println("Quin nom vols posar?");
                String nom = e.next();
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar1(nom,id);
                break;
            case 2:
                System.out.println("Quin familia vols posar?");
                System.out.println("1.camisa");
                System.out.println("2.Pantalo");
                opcion = e.nextInt();
                String familia ="";
                if(opcion == 1){
                    familia = "camisa";
                }
                else if(opcion == 2){
                    familia = "pantaló";
                }
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar2(familia,id);
                break;
            case 3:
                System.out.println("Quin Preu te?");
                int preu = e.nextInt();
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar3(preu,id);
                break;
            case 4:
                System.out.println("Quin stock?");
                int stock = e.nextInt();
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar4(stock,id);
                break;
            case 5:
                System.out.println("Quin talla del coll?");
                int talla = e.nextInt();
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar6(talla,id);
                break;
            case 6:
                System.out.println("Quina amplada de pit?");
                int pit = e.nextInt();
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar6(pit,id);
                break;
            case 7:
                System.out.println("Quina talla de la cintura?");
                int cintura = e.nextInt();
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar7(cintura,id);
                break;
            case 8:
                System.out.println("Quin llargada del camal?");
                int llargada = e.nextInt();
                System.out.println("Quin id es la de l'article que vols modificar?");
                id = e.nextInt();
                ProjectDB.modificar8(llargada,id);
        }

    }
}

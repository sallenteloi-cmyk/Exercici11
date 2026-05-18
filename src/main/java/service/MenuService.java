package service;

import java.util.Scanner;
import dao.ClientDAO;
import dao.ArticleDAO;

public class MenuService {

    // Scanner per llegir dades per teclat
    Scanner e = new Scanner(System.in);

    private TiquetService tiquetService = new TiquetService();
    private ClientDAO clientDAO = new ClientDAO();
    private ArticleDAO articleDAO = new ArticleDAO();

    // Mètode principal del menú
    public void principal() {

        int opcio;

        do {
            // Mostrar menú principal
            menu();

            // Llegir opció introduïda
            opcio = e.nextInt();

            switch (opcio) {

                case 1:

                    // Mostrar menú articles
                    menuArticle();

                    // Gestionar articles
                    gestionarArticles();

                    break;

                case 2:

                    // Mostrar menú clients
                    menuClients();

                    // Gestionar clients
                    gestionarClients();

                    break;

                case 3:

                    tiquetService.realitzarVenda();

                    break;

                case 4:

                    System.out.println("Gestió de línies de factura");

                    break;

                case 5:

                    consultarVendesPerClient();

                    break;
                case 6:
                    consultarVendesPerArticle();
                    break;

                case 7:
                    System.out.println("Sortint del programa...");
                    break;

                default:

                    System.out.println("Opció no vàlida");
            }

        } while (opcio != 7);
    }

    // Mostrar menú principal
    public void menu() {

        System.out.println("\n===== MENU PRINCIPAL =====");

        System.out.println("1. Articles");
        System.out.println("2. Clients");
        System.out.println("3. Vendes-Tiquets");
        System.out.println("4. Vendes-Línies de factura");
        System.out.println("5. Consulta vendes per client");
        System.out.println("6. Consulta vendes per article");
        System.out.println("7. Sortir");
    }

    // Mostrar menú articles
    public void menuArticle() {

        System.out.println("\n===== MENU ARTICLES =====");

        System.out.println("1. Donar d'alta");
        System.out.println("2. Modificar");
        System.out.println("3. Esborrar");
        System.out.println("4. Tornar");
    }

    // Mostrar menú clients
    public void menuClients() {

        System.out.println("\n===== MENU CLIENTS =====");

        System.out.println("1. Donar d'alta");
        System.out.println("2. Modificar");
        System.out.println("3. Esborrar");
        System.out.println("4. Tornar");
    }

    // Gestionar opcions dels articles
    public void gestionarArticles() {

        // Llegir opció
        int opcion = e.nextInt();

        switch (opcion) {

            case 1 -> System.out.println("Alta article");
            case 2 -> System.out.println("Modificar article");
            case 3 -> System.out.println("Esborrar article");
            case 4 -> System.out.println("Tornant...");
            default -> System.out.println("Opció no vàlida");
        }
    }

    // Gestionar opcions dels clients
    public void gestionarClients() {

        // Llegir opció
        int opcion = e.nextInt();

        switch (opcion) {

            case 1 -> System.out.println("Alta client");
            case 2 -> System.out.println("Modificar client");
            case 3 -> System.out.println("Esborrar client");
            case 4 -> System.out.println("Tornant...");
            default -> System.out.println("Opció no vàlida");
        }
    }

    public void consultarVendesPerClient() {

        e.nextLine();

        System.out.print("Introdueix el DNI del client: ");
        String dniClient = e.nextLine();

        clientDAO.consultarVendesPerClient(dniClient);
    }

    public void consultarVendesPerArticle() {

        System.out.print("Introdueix l'ID de l'article: ");
        int idArticle = e.nextInt();

        articleDAO.consultarVendesPerArticle(idArticle);
    }
}
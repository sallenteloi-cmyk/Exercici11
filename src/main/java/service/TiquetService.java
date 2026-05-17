package service;

import dao.ClientDAO;
import dao.ArticleDAO;
import dao.LineaFacturaDAO;
import dao.TiquetDAO;

import java.util.Scanner;

public class TiquetService {

    private Scanner e = new Scanner(System.in);

    private ClientDAO clientDAO = new ClientDAO();
    private ArticleDAO articleDAO = new ArticleDAO();
    private TiquetDAO tiquetDAO = new TiquetDAO();
    private LineaFacturaDAO lineaFacturaDAO = new LineaFacturaDAO();

    public void realitzarVenda() {

        System.out.println("\n===== GESTIÓ DE VENDES - TIQUETS =====");

        System.out.print("Introdueix el DNI del client: ");
        String dniClient = e.nextLine();

        if (clientDAO.existeixClient(dniClient)) {
            System.out.println("Client trobat: " + dniClient);
        } else {
            System.out.println("Client no trobat. S'utilitzarà el client genèric 000");
            dniClient = "000";
        }

        System.out.println("Venda associada al client: " + dniClient);

        int idTiquet = tiquetDAO.crearTiquet(dniClient);

        if (idTiquet == -1) {
            System.out.println("No s'ha pogut crear el tiquet.");
            return;
        }

        System.out.println("Tiquet creat amb ID: " + idTiquet);

        int idArticle;

        double totalBaseTiquet = 0;
        double totalIvaTiquet = 0;
        double totalFinalTiquet = 0;

        do {

            System.out.print("Introdueix ID article (0 per finalitzar el tiquet): ");
            idArticle = e.nextInt();

            if (idArticle != 0) {

                if (articleDAO.existeixArticle(idArticle)) {

                    System.out.print("Introdueix quantitat: ");
                    int quantitat = e.nextInt();

                    int stockActual = articleDAO.obtenirStockArticle(idArticle);

                    if (stockActual >= quantitat) {

                        double preuBaseUnitari = articleDAO.obtenirPreuBaseArticle(idArticle);
                        int iva = articleDAO.obtenirIvaArticle(idArticle);

                        double preuBaseTotal = preuBaseUnitari * quantitat;
                        double preuFinal = preuBaseTotal + (preuBaseTotal * iva / 100);
                        double importIva = preuFinal - preuBaseTotal;

                        boolean liniaInserida = lineaFacturaDAO.inserirLiniaFactura(
                                idTiquet,
                                idArticle,
                                quantitat,
                                preuBaseTotal,
                                iva,
                                preuFinal
                        );

                        if (liniaInserida) {

                            articleDAO.actualitzarStock(idArticle, quantitat);

                            totalBaseTiquet = totalBaseTiquet + preuBaseTotal;
                            totalIvaTiquet = totalIvaTiquet + importIva;
                            totalFinalTiquet = totalFinalTiquet + preuFinal;

                            System.out.println("Article correcte. Stock disponible.");
                            System.out.println("Línia afegida al tiquet.");

                        } else {

                            System.out.println("No s'ha pogut afegir la línia al tiquet.");
                        }

                    } else {

                        System.out.println("No hi ha stock suficient.");
                        System.out.println("Stock actual: " + stockActual);
                    }

                } else {

                    System.out.println("Aquest article no existeix.");
                }
            }

        } while (idArticle != 0);

        tiquetDAO.actualitzarTotalsTiquet(idTiquet, totalBaseTiquet, totalIvaTiquet, totalFinalTiquet);

        System.out.println("\n==============================");
        System.out.println("         TIQUET FINAL         ");
        System.out.println("==============================");

        System.out.println("ID Tiquet: " + idTiquet);

        System.out.println("Client: " + dniClient);

        System.out.println("------------------------------");

        System.out.println("Total base: " + totalBaseTiquet + " €");

        System.out.println("IVA total: " + totalIvaTiquet + " €");

        System.out.println("TOTAL FINAL: " + totalFinalTiquet + " €");

        System.out.println("==============================");

        System.out.println("Gràcies per la seva compra");

    }
}
package service;

import dao.ArticleDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonService {

    private ArticleDAO articleDAO = new ArticleDAO();

    // Mètode per llegir el fitxer JSON i carregar-lo a la base de dades
    public void llegirJson() {

        int afegits = 0;
        int actualitzats = 0;
        int camises = 0;
        int pantalons = 0;

        try {

            // Crear el parser JSON
            JSONParser parser = new JSONParser();

            // Llegir el fitxer JSON
            Object obj = parser.parse(new FileReader("src/BDPR/articles.json"));

            // Convertir el contingut en un array JSON
            JSONArray articles = (JSONArray) obj;

            // Recórrer tots els articles del JSON
            for (int i = 0; i < articles.size(); i++) {

                JSONObject article = (JSONObject) articles.get(i);

                int id = ((Long) article.get("id")).intValue();
                String nom = (String) article.get("nom");
                String familia = (String) article.get("familia");
                double preuBase = ((Number) article.get("preu_base")).doubleValue();
                int iva = ((Long) article.get("iva")).intValue();
                int stock = ((Long) article.get("stock")).intValue();

                Integer tallaColl = null;
                Integer ampladaPit = null;
                Integer tallaCintura = null;
                Integer llargadaCamal = null;

                if (familia.equalsIgnoreCase("camisa")) {

                    camises++;

                    tallaColl = ((Long) article.get("talla_coll")).intValue();
                    ampladaPit = ((Long) article.get("amplada_pit")).intValue();

                } else if (familia.equalsIgnoreCase("pantaló")) {

                    pantalons++;

                    tallaCintura = ((Long) article.get("talla_cintura")).intValue();
                    llargadaCamal = ((Long) article.get("llargada_camal")).intValue();
                }

                boolean existeix = articleDAO.existeixArticle(id);

                articleDAO.inserirOActualitzarArticle(
                        id, nom, familia,
                        tallaColl, ampladaPit,
                        tallaCintura, llargadaCamal,
                        preuBase, iva, stock
                );

                if (existeix) {
                    actualitzats++;
                } else {
                    afegits++;
                }
            }

            System.out.println("Articles tipus camisa carregats: " + camises);
            System.out.println("Articles tipus pantaló carregats: " + pantalons);
            System.out.println("Articles afegits: " + afegits);
            System.out.println("Articles actualitzats: " + actualitzats);

        } catch (Exception e) {

            System.out.println("Error llegint o important el JSON");
            e.printStackTrace();
        }
    }
}
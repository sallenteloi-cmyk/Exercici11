package service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonService {

    // Mètode per llegir el fitxer JSON
    public void llegirJson() {

        try {

            // Crear el parser JSON
            JSONParser parser = new JSONParser();

            // Llegir el fitxer JSON
            Object obj = parser.parse(new FileReader("src/BDPR/articles.json"));

            // Convertir el contingut en un array JSON
            JSONArray articles = (JSONArray) obj;

            // Recórrer tots els articles del JSON
            for (int i = 0; i < articles.size(); i++) {

                // Convertir cada article en JSONObject
                JSONObject article = (JSONObject) articles.get(i);

                // Mostrar informació per pantalla
                System.out.println("------------------------");
                System.out.println("ID: " + article.get("id"));
                System.out.println("Nom: " + article.get("nom"));
                System.out.println("Familia: " + article.get("familia"));
                System.out.println("Preu base: " + article.get("preu_base"));
                System.out.println("Stock: " + article.get("stock"));

            }

        } catch (Exception e) {

            // Mostrar error si falla la lectura del JSON
            e.printStackTrace();

        }

    }

}
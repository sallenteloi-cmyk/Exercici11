import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ProjectJSON {
    public static void conexJson() {

        try {

            JSONParser parser = new JSONParser();

            Object obj = parser.parse(
                    new FileReader("src/PE11_articles.json"));

            JSONArray articles = (JSONArray) obj;

            for (Object articleObj : articles) {

                JSONObject article = (JSONObject) articleObj;

                System.out.println("------------------------");
                System.out.println("ID: " + article.get("id"));
                System.out.println("Nom: " + article.get("nom"));
                System.out.println("Familia: " + article.get("familia"));
                System.out.println("Preu base: " + article.get("preu_base"));
                System.out.println("Stock: " + article.get("stock"));
            }

        } catch (Exception ex) {
            System.out.println("Error llegint el JSON");
            ex.printStackTrace();
        }
    }
}

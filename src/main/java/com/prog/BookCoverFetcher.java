package com.prog;

import java.io.*;
import java.net.*;
import java.util.Properties;

import com.google.gson.*;

public class BookCoverFetcher {

    public static String fetchCoverUrl(String title) {
        try {
            Properties props = new Properties();
            props.load(BookCoverFetcher.class.getResourceAsStream("/config.properties"));
            String apiKey = props.getProperty("google.books.api.key");
            String query = URLEncoder.encode(title, "UTF-8");
            String urlStr = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=1&key=" + apiKey;
            URI uri = new URI(urlStr);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray items = json.getAsJsonArray("items");
            if (items != null && items.size() > 0) {
                JsonObject volumeInfo = items.get(0).getAsJsonObject().getAsJsonObject("volumeInfo");
                JsonObject imageLinks = volumeInfo.getAsJsonObject("imageLinks");
                if (imageLinks != null) {
                    return imageLinks.get("thumbnail").getAsString();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar portada: " + e.getMessage());
        }
        return null;
    }
}
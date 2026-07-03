package com.prog.data;

import java.io.*;
import java.net.*;
import java.util.Properties;

import com.google.gson.*;

/**
 * Fetches book data from the Google Books API.
 */
public class BookCoverFetcher {

    /**
     * Fetches the cover URL of a book by its title.
     * 
     * @param title Title of the book to search
     * @return URL of the book cover, or null if not found
     */
    public static String fetchCoverUrl(String title) {
        try {
            JsonObject volumeInfo = fetchVolumeInfo(title);
            if (volumeInfo != null) {
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

    /**
     * Fetches the author of a book by its title.
     * 
     * @param title Title of the book to search
     * @return Name of the first author, or null if not found
     */
    public static String fetchAuthor(String title) {
        try {
            JsonObject volumeInfo = fetchVolumeInfo(title);
            if (volumeInfo != null) {
                JsonArray authors = volumeInfo.getAsJsonArray("authors");
                if (authors != null && authors.size() > 0) {
                    return authors.get(0).getAsString();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar autor: " + e.getMessage());
        }
        return null;
    }

    private static JsonObject fetchVolumeInfo(String title) throws Exception {
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
            return items.get(0).getAsJsonObject().getAsJsonObject("volumeInfo");
        }
        return null;
    }
}
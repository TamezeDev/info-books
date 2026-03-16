package org.zeki.infobooks.controller.app;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zeki.infobooks.model.Book;
import org.zeki.infobooks.model.Villain;
import org.zeki.infobooks.util.TransitionHelper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiController {
    private final String URL_BASE = "https://stephen-king-api.onrender.com/api/book";
    private String feedbackMessage;

    private String getData(String path, VBox feedbackBox) {
        // GET DATA FROM API
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();
        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al acceder a la api: " + e.getMessage());
            feedbackMessage = "Error al obtener datos";
            TransitionHelper.feedBackTransition(feedbackBox, feedbackMessage);
        }
        return response.body().toString();
    }

    public Book getSingleBook(VBox feedbackBox, String id) {
        // GET DATA
        String body = getData((URL_BASE + "/" + id), feedbackBox);
        if (body == null || body.isEmpty()) {
            feedbackMessage = "Datos vacíos o dañados";
            TransitionHelper.feedBackTransition(feedbackBox, feedbackMessage);
            return null;
        }
        JSONObject bodyObject = new JSONObject(body);
        JSONObject jsonObject = null;
        try{
            jsonObject = bodyObject.getJSONObject("data");
        } catch (JSONException e) {
            feedbackMessage = "Datos introducidos no válidos";
            TransitionHelper.feedBackTransition(feedbackBox, feedbackMessage);
            return null;
        }
        // CREATE BOOK
        return AppController.getInstance().getLibraryController().createBook(jsonObject);
    }

    public void getAllBooks(VBox feedbackBox) {
        // GET DATA
        String body = getData(URL_BASE + "s", feedbackBox);
        if (body == null || body.isEmpty()) {
            feedbackMessage = "Datos vacíos o dañados";
            TransitionHelper.feedBackTransition(feedbackBox, feedbackMessage);
            return;
        }
        // CREATE ROOT OBJECT
        JSONObject bodyObject = new JSONObject(body);
        JSONArray dataArray = bodyObject.getJSONArray("data");
        //CREATE EACH BOOK FROM JSON ARRAY
        Book book = null;
        for (int i = 0; i < dataArray.length(); i++) {

            JSONObject bookObject = dataArray.getJSONObject(i);
            book = AppController.getInstance().getLibraryController().createBook(bookObject);
            // ADD BOOK TO LIBRARY LIST
            AppController.getInstance().getLibraryController().getLibrary().getLibraryBooks().add(book);
        }
    }

}



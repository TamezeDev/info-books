package org.zeki.infobooks.controller.api;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zeki.infobooks.model.Book;
import org.zeki.infobooks.model.Library;
import org.zeki.infobooks.model.Villain;
import org.zeki.infobooks.util.TransitionHelper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiController {
    private final String URL_BASE = "https://stephen-king-api.onrender.com/api/books";
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
            Label label = (Label) feedbackBox.getChildren().getFirst();
            feedbackMessage = "Error al obtener datos";
            TransitionHelper.feedBackTransition(feedbackBox, feedbackMessage);
        }
        return response.body().toString();
    }

    public void getAllBooks(VBox feedbackBox) {
        // GET DATA
        String body = getData(URL_BASE, feedbackBox);
        if (body == null || body.isEmpty()) {
            feedbackMessage = "Datos vacíos o dañados";
            TransitionHelper.feedBackTransition(feedbackBox, feedbackMessage);
            return;
        }
        // CREATE ROOT OBJECT
        JSONObject bodyObject = new JSONObject(body);
        JSONArray dataArray = bodyObject.getJSONArray("data");
        //CREATE EACH BOOK FROM JSON ARRAY
        for (int i = 0; i < dataArray.length(); i++) {

            JSONObject bookObject = dataArray.getJSONObject(i);
            Book book = new Book();
            book.setId(bookObject.getLong("id"));
            book.setYear(bookObject.getLong("Year"));
            book.setPublisher(bookObject.getString("Publisher"));
            book.setIsbn(bookObject.getString("ISBN"));
            book.setPages(bookObject.getLong("Pages"));

            // CREATE VILLAINS LIST
            if (bodyObject.has("villains")) {
                JSONArray villainsArray = bodyObject.getJSONArray("villains");
                for (int j = 0; j < villainsArray.length(); j++) {
                    // ADD EACH VILLAIN
                    JSONObject villainObject = villainsArray.getJSONObject(j);
                    Villain villain = new Villain();
                    villain.setName(villainObject.getString("name"));
                    book.getVillains().add(villain);
                }
            }
            // ADD BOOK TO LIBRARY LIST
            Library.getInstance().getLibraryBooks().add(book);
        }

    }
}

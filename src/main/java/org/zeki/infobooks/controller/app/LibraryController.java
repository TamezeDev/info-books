package org.zeki.infobooks.controller.app;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zeki.infobooks.model.Book;
import org.zeki.infobooks.model.Library;
import org.zeki.infobooks.model.Villain;

import java.util.List;

@Getter
@Setter
public class LibraryController {

    private Library library;

    public LibraryController() {
        library = new Library();
    }

    public boolean checkIfBookInFavourites(long idBook) {
        List<Book> books = library.getFavouriteBooks();
        return books.stream().allMatch(book -> book.getId() == idBook);
    }

    public Book getSelectedBook(long idBook) {
        List<Book> books = library.getLibraryBooks();
        return books.stream().filter(book -> book.getId() == idBook).findFirst().get();
    }

    public Book createBook(JSONObject bookObject) {
        Book book = new Book();
        book.setId(bookObject.getLong("id"));
        book.setYear(bookObject.getLong("Year"));
        book.setTitle(bookObject.getString("Title"));
        book.setPublisher(bookObject.getString("Publisher"));
        book.setIsbn(bookObject.getString("ISBN"));
        book.setPages(bookObject.getLong("Pages"));

        // CREATE VILLAINS LIST
        if (bookObject.has("villains")) {
            JSONArray villainsArray = bookObject.getJSONArray("villains");
            for (int j = 0; j < villainsArray.length(); j++) {
                // ADD EACH VILLAIN
                JSONObject villainObject = villainsArray.getJSONObject(j);
                Villain villain = new Villain();
                villain.setName(villainObject.getString("name"));
                book.getVillains().add(villain);
            }
        }
        return book;
    }

}

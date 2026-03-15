package org.zeki.infobooks.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Library {

    private List<Book> libraryBooks;
    private List<Book> favouriteBooks;

    private static Library instance;

    private Library() {
        libraryBooks = new ArrayList<>();
        favouriteBooks = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

}

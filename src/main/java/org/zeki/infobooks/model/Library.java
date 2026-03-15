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

    public Library() {
        libraryBooks = new ArrayList<>();
        favouriteBooks = new ArrayList<>();
    }

}

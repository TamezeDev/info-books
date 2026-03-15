package org.zeki.infobooks.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Book {
    private long id;
    private long year;
    private String title;
    private String publisher;
    private String isbn;
    private long pages;
    private List<Villain> villains;
}

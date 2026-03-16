package org.zeki.infobooks.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Book implements Serializable {

    private final long SERIAL_VERSION_UID = 1L;

    private long id;
    private long year;
    private String title;
    private String publisher;
    private String isbn;
    private long pages;
    private List<Villain> villains;

    public Book() {
        villains = new ArrayList<>();
    }
}

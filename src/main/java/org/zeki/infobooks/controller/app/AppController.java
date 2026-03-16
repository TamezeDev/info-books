package org.zeki.infobooks.controller.app;

import lombok.Getter;
import lombok.Setter;
import org.zeki.infobooks.model.Book;

@Getter
@Setter
public class AppController {

    private static AppController instance;

    private LibraryController libraryController;

    private Book currentBook;
    private boolean showOnlyInfo;
    private boolean isFavouriteSelected;
    private boolean startedApp;

    private AppController() {
        libraryController = new LibraryController();
    }

    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }
}

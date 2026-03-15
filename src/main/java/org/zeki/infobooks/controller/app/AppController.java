package org.zeki.infobooks.controller.app;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppController {

    private static AppController instance;

    private LibraryController libraryController;
    private boolean showOnlyInfo;
    private boolean isFavouriteSelected;
    private long idSelected;

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

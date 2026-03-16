package org.zeki.infobooks.controller.app;

import javafx.scene.layout.VBox;
import org.zeki.infobooks.model.Book;
import org.zeki.infobooks.util.PathHelper;
import org.zeki.infobooks.util.TransitionHelper;

import java.io.*;
import java.util.List;

public class FileController {

    private File file;
    private List<Book> favBooks;
    private String message;

    PathHelper pathHelper = new PathHelper();

    public FileController() {
        file = new File(pathHelper.getFAVOURITE_FILE());
        favBooks = AppController.getInstance().getLibraryController().getLibrary().getFavouriteBooks();
    }

    public void importFavourites(VBox feedbackBox) {
        // CHECK IF EXISTS FILE
        if (!file.exists()) {
            try {
                file.createNewFile();
                message = "No hay datos que importar";
                TransitionHelper.feedBackTransition(feedbackBox, message);
                return;
            } catch (IOException e) {
                System.err.println("ERROR: " + e.getMessage());
                message = "Error creando fichero de datos";
                TransitionHelper.feedBackTransition(feedbackBox, message);
                return;
            }
        }
        // READ FILE AND LOAD IN LIST
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Book book = (Book) ois.readObject();
                // IF BOOK IS PRESENT IN CURRENT LIST NOT DUPLICATE
                if (AppController.getInstance().getLibraryController().checkIfBookInFavourites(book.getId())) {
                    continue;
                }
                AppController.getInstance().getLibraryController().getLibrary().getFavouriteBooks().add(book);
            }
        } catch (EOFException e) {
            message = "Datos importados con éxito";
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
            message = "Error creando fichero de datos";
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            message = "Error leyendo fichero de favoritos";

        }
        TransitionHelper.feedBackTransition(feedbackBox, message);
    }

    public void exportFavourites(VBox feedbackBox) {
        // IF LIST IS EMPTY NOT SAVE
        if (favBooks.isEmpty()) {
            message = "Tu lista de favoritos está vacía";
            TransitionHelper.feedBackTransition(feedbackBox, message);
            return;
        }
        // SAVE TO FILE
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, false)))) {
            for (Book favBook : favBooks) {
                oos.writeObject(favBook);
            }
            message = "Favoritos exportados con éxito";
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            message = "Fallo al exportar favoritos";
        }
        TransitionHelper.feedBackTransition(feedbackBox, message);
    }


}



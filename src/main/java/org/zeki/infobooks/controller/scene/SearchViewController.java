package org.zeki.infobooks.controller.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.zeki.infobooks.controller.app.ApiController;
import org.zeki.infobooks.controller.app.AppController;
import org.zeki.infobooks.model.Book;
import org.zeki.infobooks.model.Villain;
import org.zeki.infobooks.util.PathHelper;
import org.zeki.infobooks.util.SceneHelper;
import org.zeki.infobooks.util.TransitionHelper;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {

    @FXML
    private Button deleteBookBtn;

    @FXML
    private VBox feedbackBox;

    @FXML
    private Button goBackBtn;

    @FXML
    private Label idLabel;

    @FXML
    private Label isbnLabel;

    @FXML
    private VBox infoBookBox;

    @FXML
    private Label pagesLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private Button saveBookBtn;

    @FXML
    private TextField searcherField;

    @FXML
    private Label titleLabel;

    @FXML
    private VBox villainsBox;

    @FXML
    private Label yearLabel;

    @FXML
    private HBox searchBox;

    @FXML
    private ImageView searchBtn;

    PathHelper pathHelper = new PathHelper();
    ApiController apiController = new ApiController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkUserChoice();
        initListeners();
    }

    private void initListeners() {
        // RETURN TO MAIN MENU
        goBackBtn.setOnAction(event -> SceneHelper.changeScene(feedbackBox, checkOnlyView()));
        // SEARCH BY ID
        searchBtn.setOnMouseClicked(event -> {
            showInfoBook();
        });
        // SAVE BOOK IN FAVOURITE LIST
        saveBookBtn.setOnAction(action -> {
            AppController.getInstance().getLibraryController().addBookToFavourite(feedbackBox);
            activateButtons();
        });
        // DELETE BOOK FROM FAVOURITE LIST
        deleteBookBtn.setOnAction(_ -> {
            AppController.getInstance().getLibraryController().deleteBookFromFavourites(feedbackBox);
            activateButtons();
        });
    }

    private void checkUserChoice() {
        // CHECK IF USER CHOSE ID FROM CATALOG
        if (AppController.getInstance().isShowOnlyInfo()) {
            searchBox.setVisible(false);
            goBackBtn.setText("Volver Atrás");
            // SET SELECTED BOOK
            Book selectedBook = AppController.getInstance().getLibraryController().getSelectedBook(AppController.getInstance().getCurrentBook().getId());
            if (selectedBook == null) {
                infoBookBox.setVisible(false);
                String message = "Error al cargar datos del libro";
                TransitionHelper.feedBackTransition(feedbackBox, message);
                return;
            }
            setDataBook(selectedBook);
        } else {
            infoBookBox.setVisible(false);
        }
    }

    private void showInfoBook() {
        // HIDE INFO BOX BEFORE SEARCH
        String idSelected = searcherField.getText();
        Book book = apiController.getSingleBook(feedbackBox, idSelected);
        if (book != null) {
            AppController.getInstance().setCurrentBook(book);
            setDataBook(book);
            infoBookBox.setVisible(true);
        }
    }

    private void setDataBook(Book selectedBook) {
        // SET CURRENT BOOK
        AppController.getInstance().setCurrentBook(selectedBook);
        villainsBox.getChildren().clear();
        activateButtons();
        // SET ALL INFO
        idLabel.setText("Id: " + selectedBook.getId());
        titleLabel.setText("Título: " + selectedBook.getTitle());
        pagesLabel.setText("Páginas: " + selectedBook.getPages());
        publisherLabel.setText("Editorial: " + selectedBook.getPublisher());
        isbnLabel.setText("ISBN: " + selectedBook.getIsbn());
        yearLabel.setText("Año: " + selectedBook.getYear());

        List<Villain> villains = selectedBook.getVillains();

        villains.forEach(villain -> {
            Label label = new Label(villain.getName());
            label.getStyleClass().add("labelTypeA");
            villainsBox.getChildren().add(label);
        });
    }

    private void activateButtons() {
        // IF IT HAS THIS ID IN FAVOURITES ACTIVATE DELETE BUTTON
        Book currentBook = AppController.getInstance().getCurrentBook();
        if (currentBook == null) {
            deleteBookBtn.setDisable(true);
            saveBookBtn.setDisable(false);
            return;
        }
        if (AppController.getInstance().getLibraryController().checkIfBookInFavourites(AppController.getInstance().getCurrentBook().getId())) {
            deleteBookBtn.setDisable(false);
            saveBookBtn.setDisable(true);
        } else {
            deleteBookBtn.setDisable(true);
            saveBookBtn.setDisable(false);
        }
    }

    private String checkOnlyView() {
        // CHECK SCENE WHEN GO BACK
        if (AppController.getInstance().isShowOnlyInfo()) {
            return pathHelper.getCATALOG_SCENE();
        } else {
            return pathHelper.getMAIN_SCENE();
        }
    }

}

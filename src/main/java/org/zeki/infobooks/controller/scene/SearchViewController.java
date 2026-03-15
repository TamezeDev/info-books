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
    private VBox dataBookBox;

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
        visibleDeleteButton();
        initListeners();
    }

    private void initListeners() {
        // RETURN TO MAIN MENU
        goBackBtn.setOnAction(event -> SceneHelper.changeScene(feedbackBox, checkOnlyView()));
        // SEARCH BY ID
        searchBtn.setOnMouseClicked(event -> showInfoBook());
    }

    private void checkUserChoice() {
        // CHECK IF USER CHOSE ID FROM CATALOG
        if (AppController.getInstance().isShowOnlyInfo()) {
            deleteBookBtn.setVisible(true);
            searchBox.setVisible(false);
            goBackBtn.setText("Volver Atrás");
            // SET SELECTED BOOK
            Book selectedBook = AppController.getInstance().getLibraryController().getSelectedBook(AppController.getInstance().getIdSelected());
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
        String idSelected = searcherField.getText();
        Book book = apiController.getSingleBook(feedbackBox, idSelected);
        if (book != null) {
            setDataBook(book);
            infoBookBox.setVisible(true);
        }
    }

    private void setDataBook(Book selectedBook) {
        idLabel.setText(String.valueOf(selectedBook.getId()));
        titleLabel.setText(selectedBook.getTitle());
        pagesLabel.setText(String.valueOf(selectedBook.getPages()));
        publisherLabel.setText(selectedBook.getPublisher());
        isbnLabel.setText(String.valueOf(selectedBook.getIsbn()));
        yearLabel.setText(String.valueOf(selectedBook.getYear()));

        List<Villain> villains = selectedBook.getVillains();

        villains.forEach(villain -> {
            Label label = new Label(villain.getName());
            label.getStyleClass().add("labelTypeA");
            villainsBox.getChildren().add(label);
        });
    }

    private void visibleDeleteButton() {
        // IF IT HAS THIS ID IN FAVOURITES ACTIVATE DELETE BUTTON
        if (AppController.getInstance().getLibraryController().checkIfBookInFavourites(AppController.getInstance().getIdSelected())) {
            deleteBookBtn.setVisible(true);
        }
    }

    private String checkOnlyView() {
        if (AppController.getInstance().isShowOnlyInfo()) {
            return pathHelper.getCATALOG_SCENE();
        } else {
            return pathHelper.getMAIN_SCENE();
        }
    }

}

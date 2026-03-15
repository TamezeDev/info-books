package org.zeki.infobooks.controller.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.zeki.infobooks.controller.app.AppController;
import org.zeki.infobooks.model.Book;
import org.zeki.infobooks.util.PathHelper;
import org.zeki.infobooks.util.SceneHelper;
import org.zeki.infobooks.util.TransitionHelper;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CatalogViewController implements Initializable {

    @FXML
    private FlowPane containerBookPane;

    @FXML
    private VBox feedbackBox;

    @FXML
    private Button goBackBtn;

    private PathHelper pathHelper = new PathHelper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBookGallery();
        initListeners();
    }

    private void initListeners() {
        goBackBtn.setOnAction(event -> SceneHelper.changeScene(feedbackBox, pathHelper.getMAIN_SCENE()));
    }

    private void createBookGallery() {
        if (AppController.getInstance().getLibraryController().getLibrary().getLibraryBooks().isEmpty() && !AppController.getInstance().isFavouriteSelected()) {
            containerBookPane.setVisible(false);
            String message = "Error al cargar lista de libros";
            TransitionHelper.feedBackTransition(feedbackBox, message);
            return;
        }
        // CREATE ELEMENTS ACCORDING TO CHOICE
        List<Book> books = checkUserChoice();
        books.forEach(book -> {
            // LABELS
            Label number = new Label(String.valueOf(book.getId()));
            Label title = new Label(String.valueOf(book.getTitle()));
            number.getStyleClass().add("labelTypeB");
            title.getStyleClass().add("labelTypeB");
            // IMG
            ImageView bookImg = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathHelper.getBOOK_IMG()))));
            bookImg.setFitWidth(120);
            bookImg.setFitHeight(150);
            bookImg.setPreserveRatio(false);
            // CARD
            VBox bookCard = new VBox();
            bookCard.setAlignment(Pos.CENTER);
            bookCard.setSpacing(10);
            bookCard.getStyleClass().addAll("bookCard", "button-typeB");
            bookCard.setMaxSize(140, 280);

            bookCard.getChildren().addAll(number, bookImg, title);
            // EVENT CARD
            bookCard.setOnMouseClicked(event -> {
                AppController.getInstance().setShowOnlyInfo(true);
                AppController.getInstance().setIdSelected(book.getId());
                SceneHelper.changeScene(feedbackBox, pathHelper.getSEARCH_SCENE());
            });
            // ADD TO CONTAINER
            containerBookPane.getChildren().add(bookCard);
        });

    }

    private List<Book> checkUserChoice() {
        List<Book> books;
        if (AppController.getInstance().isFavouriteSelected()) {
            books = AppController.getInstance().getLibraryController().getLibrary().getFavouriteBooks();
            if (books.isEmpty()){
                containerBookPane.setVisible(false);
                String message = "La list está vacía";
                TransitionHelper.feedBackTransition(feedbackBox,message);
            }
        } else {
            books = AppController.getInstance().getLibraryController().getLibrary().getLibraryBooks();
        }
        return books;
    }
}

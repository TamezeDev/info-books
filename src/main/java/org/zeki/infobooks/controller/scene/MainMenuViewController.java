package org.zeki.infobooks.controller.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.zeki.infobooks.controller.app.ApiController;
import org.zeki.infobooks.controller.app.AppController;
import org.zeki.infobooks.util.PathHelper;
import org.zeki.infobooks.util.SceneHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuViewController implements Initializable {

    @FXML
    private ImageView catalogImg;

    @FXML
    private ImageView exportImg;

    @FXML
    private ImageView favouritesImg;

    @FXML
    private Label feedBackLabel;

    @FXML
    private VBox feedbackBox;

    @FXML
    private ImageView importImg;

    @FXML
    private VBox mainMenuBox;

    @FXML
    private ImageView searchImg;

    @FXML
    private ImageView titleImg;

    private PathHelper path = new PathHelper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initListeners();
    }

    private void initListeners() {
        // SHOW FULL CATALOG
        catalogImg.setOnMouseClicked((_) -> {
            AppController.getInstance().setFavouriteSelected(false);
            showCompleteCatalog();
        });
        // SHOW FAVOURITE LIST
        favouritesImg.setOnMouseClicked(_ -> {
            AppController.getInstance().setFavouriteSelected(true);
            SceneHelper.changeScene(feedbackBox,path.getCATALOG_SCENE());
        });
        // SEARCH BY ID
        searchImg.setOnMouseClicked(event -> {
            AppController.getInstance().setShowOnlyInfo(false);
            AppController.getInstance().setCurrentBook(null);
            SceneHelper.changeScene(feedbackBox,path.getSEARCH_SCENE());
        });
    }

    private void showCompleteCatalog() {
        // ONLY LOAD FIRST TIME (THEN USE LIST LOADED)
        if (AppController.getInstance().getLibraryController().getLibrary().getLibraryBooks().isEmpty()) {
            ApiController api = new ApiController();
            api.getAllBooks(feedbackBox);
        }
        SceneHelper.changeScene(feedbackBox, path.getCATALOG_SCENE());
    }
}

package org.zeki.infobooks.controller.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.zeki.infobooks.controller.app.ApiController;
import org.zeki.infobooks.controller.app.AppController;
import org.zeki.infobooks.controller.app.FileController;
import org.zeki.infobooks.util.PathHelper;
import org.zeki.infobooks.util.SceneHelper;
import org.zeki.infobooks.util.TransitionHelper;

import java.io.File;
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
    private VBox feedbackBox;

    @FXML
    private ImageView importImg;

    @FXML
    private VBox mainMenuBox;

    @FXML
    private ImageView searchImg;

    @FXML
    private Label signLabel;

    @FXML
    private ImageView titleImg;

    private PathHelper path = new PathHelper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkStartAnimation();
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
            SceneHelper.changeScene(feedbackBox, path.getCATALOG_SCENE());
        });
        // SEARCH BY ID
        searchImg.setOnMouseClicked(_ -> {
            AppController.getInstance().setShowOnlyInfo(false);
            AppController.getInstance().setCurrentBook(null);
            SceneHelper.changeScene(feedbackBox, path.getSEARCH_SCENE());
        });
        // EXPORT FAVOURITE BOOKS
        exportImg.setOnMouseClicked(_ -> {
            FileController fc = new FileController();
            fc.exportFavourites(feedbackBox);
        });
        // IMPORT FAVOURITE BOOKS
        importImg.setOnMouseClicked(_ -> {
            FileController fc = new FileController();
            fc.importFavourites(feedbackBox);
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

    private void checkStartAnimation() {
        if (!AppController.getInstance().isStartedApp()) {
            TransitionHelper.initialTransition(titleImg, mainMenuBox, signLabel);
        }
    }
}

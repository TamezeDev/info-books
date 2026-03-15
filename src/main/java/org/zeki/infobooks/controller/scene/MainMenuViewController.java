package org.zeki.infobooks.controller.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.zeki.infobooks.controller.api.ApiController;
import org.zeki.infobooks.model.Library;
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
        catalogImg.setOnMouseClicked((_) -> showCompleteCatalog());
    }

    private void showCompleteCatalog() {
        // ONLY LOAD FIRST TIME (THEN USE LIST LOADED)
        if (Library.getInstance().getLibraryBooks().isEmpty()) {
            ApiController api = new ApiController();
            api.getAllBooks(feedbackBox);
        }
        SceneHelper.changeScene(feedbackBox, path.getCATALOG_SCENE());


    }
}

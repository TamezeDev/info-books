package org.zeki.infobooks.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

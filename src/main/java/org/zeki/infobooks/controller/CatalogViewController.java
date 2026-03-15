package org.zeki.infobooks.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CatalogViewController implements Initializable {

    @FXML
    private FlowPane containerBookPane;

    @FXML
    private Label feedBackLabel;

    @FXML
    private VBox feedbackBox;

    @FXML
    private Button goBackBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

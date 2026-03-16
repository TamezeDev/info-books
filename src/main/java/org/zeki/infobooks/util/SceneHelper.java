package org.zeki.infobooks.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHelper {
    public static void changeScene(Node node, String pathFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneHelper.class.getResource(pathFxml));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) (node.getScene().getWindow());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("ERROR: " + e.getStackTrace());
            String feedbackMessage = "Error al cargar scene";
            VBox feedbackBox = (VBox) node;
            TransitionHelper.feedBackTransition(feedbackBox, feedbackMessage);
        }
    }
}

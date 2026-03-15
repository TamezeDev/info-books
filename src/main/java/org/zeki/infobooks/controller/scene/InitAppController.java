package org.zeki.infobooks.controller.scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.zeki.infobooks.util.PathHelper;

import java.io.IOException;

public class InitAppController extends Application {
    PathHelper pathHelper = new PathHelper();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitAppController.class.getResource(pathHelper.getMAIN_SCENE()));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("InfoBooks");
        stage.setScene(scene);
        stage.show();
    }
}

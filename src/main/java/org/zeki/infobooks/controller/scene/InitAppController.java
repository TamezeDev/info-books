package org.zeki.infobooks.controller.scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.zeki.infobooks.util.PathHelper;

import java.io.IOException;
import java.util.Objects;

public class InitAppController extends Application {
    PathHelper pathHelper = new PathHelper();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitAppController.class.getResource(pathHelper.getMAIN_SCENE()));
        Image mainIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathHelper.getMAIN_ICON())));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);

        stage.getIcons().add(mainIcon);
        stage.setTitle("InfoBooks");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

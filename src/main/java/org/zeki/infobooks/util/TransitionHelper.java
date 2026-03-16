package org.zeki.infobooks.util;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.zeki.infobooks.controller.app.AppController;

public class TransitionHelper {

    private static PauseTransition feedBackTransition = null;

    public static void feedBackTransition(VBox feedbackBox, String message) {
        // RESET TRANSITION
        if (feedBackTransition != null) {
            feedBackTransition.stop();
        }
        // SHOW FEEDBACK MESSAGE 2 SECONDS
        Label label = (Label) feedbackBox.getChildren().getFirst();
        label.setText(message);
        feedbackBox.setVisible(true);
        feedBackTransition = new PauseTransition(Duration.seconds(2));
        feedBackTransition.setOnFinished((event) -> {
            feedbackBox.setVisible(false);
            feedBackTransition = null;
        });
        feedBackTransition.play();
    }

    public static void initialTransition(ImageView titleImg, VBox mainMenuBox, Label signLabel) {
        // TITLE & SIGN APPEAR AT 1.5s
        FadeTransition titleBoxTransition = new FadeTransition(Duration.seconds(1.5), titleImg);
        FadeTransition signBoxTransition = new FadeTransition(Duration.seconds(1.5), signLabel);
        titleBoxTransition.setFromValue(0);
        titleBoxTransition.setToValue(1);
        signBoxTransition.setFromValue(0);
        signBoxTransition.setToValue(1);

        // MAIN FUNCTION APPEAR AT 2s
        FadeTransition mainBoxTransition = new FadeTransition(Duration.seconds(2), mainMenuBox);
        mainBoxTransition.setFromValue(0);
        mainBoxTransition.setToValue(1);
        // SEQUENCE
        SequentialTransition sequence = new SequentialTransition(
                titleBoxTransition, signBoxTransition, mainBoxTransition
        );
        sequence.play();
        AppController.getInstance().setStartedApp(true);
    }
}

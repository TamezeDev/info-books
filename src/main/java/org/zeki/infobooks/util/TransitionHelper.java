package org.zeki.infobooks.util;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TransitionHelper {
    private static PauseTransition feedBackTransition = null;

    public static void feedBackTransition(VBox feedbackBox,String message) {
        if (feedBackTransition != null) {
            feedBackTransition.stop();
        }
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
}

package com.example.mygame;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonScaleEffect {
    private static final double x = 1.2;

    public static void addScaleEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), button);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(x);
        scaleIn.setToY(x);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), button);
        scaleOut.setFromX(x);
        scaleOut.setFromY(x);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        button.setOnMouseEntered(event -> scaleIn.play());
        button.setOnMouseExited(event -> scaleOut.play());
    }
}


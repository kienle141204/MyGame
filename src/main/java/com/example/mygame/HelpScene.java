package com.example.mygame;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelpScene {
    private Scene scene;


    public HelpScene(Stage primaryStage,Scene scene1) throws FileNotFoundException {

        StackPane layout = new StackPane();
        Image background = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/helpScene1.png"));
        ImageView backgroundView = new ImageView(background);
        layout.getChildren().add(backgroundView);

        scene = new Scene(layout, 1000, 750);

        // Tạo nút Back
        Button backButton = new Button();
        backButton.getStyleClass().add("back-button");
        Image image = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/back.png"));
        ImageView imageView = new ImageView(image);
        backButton.setGraphic(imageView);
        layout.getChildren().add(imageView);
        layout.getChildren().add(backButton);
        scene.getStylesheets().add("file:///E:/code/MyGame/src/main/java/style.css");
        imageView.setFitHeight(75);
        imageView.setFitWidth(150);

        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(0, 32, 30, 0));

        // Hiệu ứng cho nút Back
        ButtonScaleEffect.addScaleEffect(backButton);

        backButton.setOnAction(event -> {
            primaryStage.setScene(scene1);
        });
    }

    public Scene getScene() {
        return scene;
    }
}

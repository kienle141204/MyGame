package com.example.mygame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
    private static final String[] IMAGE_PATHS = {
            "file:E:/code/MyGame/src/main/java/wi/10.png",
            "file:E:/code/MyGame/src/main/java/wi/11.png",
            "file:E:/code/MyGame/src/main/java/wi/12.png",
            "file:E:/code/MyGame/src/main/java/wi/13.png",
            "file:E:/code/MyGame/src/main/java/wi/14.png",
            "file:E:/code/MyGame/src/main/java/wi/15.png",
            "file:E:/code/MyGame/src/main/java/wi/16.png",
            "file:E:/code/MyGame/src/main/java/wi/17.png"
            // Thêm các đường dẫn hình ảnh khác vào đây
            // ảnh này  thêm ở package wi
    };
    private int currentFrame = 0;

    public HelpScene(Stage primaryStage,Scene scene1)  {

        StackPane layout = new StackPane();

        // Load hình ảnh background và hiển thị nó trong ImageView
        Image background = new Image("file:E:/code/MyGame/src/main/java/image/helpscene4.png");
        ImageView backgroundView = new ImageView(background);
        layout.getChildren().add(backgroundView);

        // Tạo ImageView để hiển thị nhân vật và thêm vào layout
        ImageView characterImageView = new ImageView();
        layout.getChildren().add(characterImageView);

        // Tạo nút Back và thêm vào layout
        Button backButton = new Button();
        backButton.getStyleClass().add("back-button");
        Image backImage = new Image("file:E:/code/MyGame/src/main/java/image/back.png");
        ImageView backImageView = new ImageView(backImage);
        backButton.setGraphic(backImageView);
        layout.getChildren().add(backButton);

        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(0, 32, 30, 0));



        // Đặt vị trí ban đầu của nhân vật
        characterImageView.setTranslateX(220); // Vị trí x
        characterImageView.setTranslateY(-30); // Vị trí y

        // Đặt kích thước cho nhân vật
        double characterWidth = 750; // Chiều rộng mới
        double characterHeight = 750; // Chiều cao mới
        characterImageView.setFitWidth(characterWidth);
        characterImageView.setFitHeight(characterHeight);

        // Hiệu ứng cho nút Back
        ButtonScaleEffect.addScaleEffect(backButton);

        backButton.setOnAction(event -> {
            primaryStage.setScene(scene1);
        });

        // Load hình ảnh nhân vật và thêm vào layout
        Image[] characterImages = new Image[IMAGE_PATHS.length];
        for (int i = 0; i < IMAGE_PATHS.length; i++) {
            Image fileInputStream = new Image(IMAGE_PATHS[i]);
            characterImages[i] = fileInputStream;
        }

        // Đặt hình ảnh ban đầu cho nhân vật
        characterImageView.setImage(characterImages[currentFrame]);

        // Tạo timeline để thay đổi hình ảnh của nhân vật
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> {
            currentFrame = (currentFrame + 1) % characterImages.length;
            characterImageView.setImage(characterImages[currentFrame]);
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Lặp lại vô hạn
        timeline.play();

        scene = new Scene(layout, 1000, 750);
        scene.getStylesheets().add("file:E:/code/MyGame/src/main/java/style.css");
    }

    public Scene getScene() {
        return scene;
    }
}

package com.example.mygame;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;

public class PlayLevel {
    private Scene scene;
    private static String[] IMAGE_PATHS ={
            "E:/code/MyGame/src/main/java/image/lv1.png",
            "E:/code/MyGame/src/main/java/image/lv2.png",
            "E:/code/MyGame/src/main/java/image/lv3.png",
            "E:/code/MyGame/src/main/java/image/lv4.png",
            "E:/code/MyGame/src/main/java/image/lv5.png"
    };
    private  static  String[] ANOTHER_IMAGE_PATHS={
            "E:/code/MyGame/src/main/java/image/easy1.png",
            "E:/code/MyGame/src/main/java/image/easy1.png",
            "E:/code/MyGame/src/main/java/image/easy1.png",
            "E:/code/MyGame/src/main/java/image/medium1.png",
            "E:/code/MyGame/src/main/java/image/hard1.png"
    };


    public PlayLevel(Stage primaryStage, Scene scene1) throws FileNotFoundException {

        StackPane layout = new StackPane();
        Image background = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/playlv1.png"));
        ImageView backgroundView = new ImageView(background);
        backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());// làm như này thì nền mới full cửa sổ được
        backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());
        layout.getChildren().add(backgroundView);

        scene = new Scene(layout, 1000, 750);

        // Tạo nút Back
        Button backButton = new Button();
        backButton.getStyleClass().add("back-button");
        Image image = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/back.png"));
        ImageView imageView = new ImageView(image);
        backButton.setGraphic(imageView);
        layout.getChildren().add(imageView);

        scene.getStylesheets().add("file:///E:/code/MyGame/src/main/java/style.css");
        imageView.setFitHeight(50);
        imageView.setFitWidth(100);

        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(0, 32, 30, 0));
        /////////////////////////////


        // Hiệu ứng cho nút Back
        ButtonScaleEffect.addScaleEffect(backButton);

        backButton.setOnAction(event -> {
            primaryStage.setScene(scene1);
        });

        //Các màn chơi
        Button game1 = new Button();
        Button game2 = new Button();
        Button game3 = new Button();
        Button game4 = new Button();
        Button game5 = new Button();

        Button[] games = {game1, game2, game3, game4, game5};
        for (Button game : games) {
            game.setPrefSize(175, 250);
        }

        for (int i = 0; i < IMAGE_PATHS.length; i++) {
            try {
                // Load hình ảnh gốc
                Image fileInputStream = new Image(new FileInputStream(IMAGE_PATHS[i]));

                // Tạo ImageView cho hình ảnh gốc
                ImageView originalImageView = new ImageView(fileInputStream);
                originalImageView.setFitWidth(140);
                originalImageView.setFitHeight(200);

                // Tạo ImageView cho hình ảnh khi con trỏ chuột vào
                Image hoverImage = new Image(new FileInputStream(ANOTHER_IMAGE_PATHS[i]));
                ImageView hoverImageView = new ImageView(hoverImage);
                hoverImageView.setFitWidth(140);
                hoverImageView.setFitHeight(200);
                hoverImageView.setVisible(false); // Ẩn hình ảnh khi không được sử dụng

                // Thêm hình ảnh vào trong nút
                StackPane stackPane = new StackPane(originalImageView, hoverImageView);
                games[i].setGraphic(stackPane);
                ButtonScaleEffect.addScaleEffect(games[i]);

                // Thiết lập sự kiện khi con trỏ chuột đi vào nút
                games[i].setOnMouseEntered(event -> {
                    originalImageView.setVisible(false);
                    hoverImageView.setVisible(true);
                });

                // Thiết lập sự kiện khi con trỏ chuột rời khỏi nút
                games[i].setOnMouseExited(event -> {
                    originalImageView.setVisible(true);
                    hoverImageView.setVisible(false);
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        HBox hbox = new HBox(20,game1, game2, game3, game4, game5);
        hbox.getStyleClass().add("hbox");
        hbox.setAlignment(Pos.CENTER);
        layout.getChildren().add(hbox);


        layout.getChildren().add(backButton);


    }

    public Scene getScene() {
        return scene;
    }
}

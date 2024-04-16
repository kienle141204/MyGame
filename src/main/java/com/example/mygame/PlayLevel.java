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

public class PlayLevel {
    private Scene scene;


    public PlayLevel(Stage primaryStage, Scene scene1) throws FileNotFoundException {

        StackPane layout = new StackPane();
        Image background = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/leveltest.png"));
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
        Button game1 = new Button("1");
        Button game2 = new Button("2");
        Button game3 = new Button("3");
        Button game4 = new Button("4");
        Button game5 = new Button("5");
        game1.setStyle("-fx-background-color: #111");
        game2.setStyle("-fx-background-color: #111");
        game3.setStyle("-fx-background-color: #111");
        game4.setStyle("-fx-background-color: #111");
        game5.setStyle("-fx-background-color: #111");

        Button[] games = {game1, game2, game3, game4, game5};
        for (Button game : games) {
            game.setPrefSize(175, 250);
        }

        HBox hbox = new HBox(10,game1, game2, game3, game4, game5);
        hbox.getStyleClass().add("hbox");
        hbox.setAlignment(Pos.CENTER);
        layout.getChildren().add(hbox);


        layout.getChildren().add(backButton);


    }

    public Scene getScene() {
        return scene;
    }
}

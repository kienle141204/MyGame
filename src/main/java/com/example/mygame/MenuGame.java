package com.example.mygame;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class MenuGame extends Application {
    private Stage primaryStage;
    private Scene scene1,scene2;



    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Menu Game");

        Button button1 = new Button();
        button1.getStyleClass().add("play-button"); // Thêm lớp CSS cho nút

        // Tải biểu tượng "play" từ tệp hình ảnh
        Image playIconImage = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/play-button-arrowhead.png"));
        ImageView playIconImageView = new ImageView(playIconImage);

        // Đặt biểu tượng "play" làm đồng hành của nút
        button1.setGraphic(playIconImageView);
        playIconImageView.setFitWidth(32); // Đặt chiều rộng tối đa
        playIconImageView.setFitHeight(32); // Đặt chiều cao tối đa

        Button button2 = new Button("exit");
        Button button3 = new Button("help");

        HBox hbox = new HBox(20, button1, button2, button3);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);

        StackPane layout1 = new StackPane();

        scene1 = new Scene(layout1, 900, 600);

        scene1.getStylesheets().add("file:///E:/code/MyGame/src/main/java/style.css");

        Image image1 = new Image("file:E:/code/MyGame/src/main/java/image/image1.png");
        ImageView backgroundImage = new ImageView(image1);
        layout1.getChildren().add(backgroundImage);
        backgroundImage.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundImage.fitHeightProperty().bind(primaryStage.heightProperty());
        layout1.getChildren().add(hbox);

        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

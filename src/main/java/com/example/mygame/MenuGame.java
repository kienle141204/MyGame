package com.example.mygame;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

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
        Image playIconImage = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/play5.png"));
        ImageView playIconImageView = new ImageView(playIconImage);

        // Đặt biểu tượng "play" làm đồng hành của nút
        button1.setGraphic(playIconImageView);
        playIconImageView.setFitWidth(150); // Đặt chiều rộng tối đa
        playIconImageView.setFitHeight(75); // Đặt chiều cao tối đa

        Button button2 = new Button("exit");
        Button button3 = new Button("help");

        VBox vbox = new VBox(20, button1, button2, button3);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        StackPane layout1 = new StackPane();

        scene1 = new Scene(layout1, 900, 600);

        scene1.getStylesheets().add("file:///E:/code/MyGame/src/main/java/style.css");

        Image image1 = new Image("file:E:/code/MyGame/src/main/java/image/mazeimage.png");
        ImageView backgroundImage = new ImageView(image1);
        layout1.getChildren().add(backgroundImage);
        backgroundImage.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundImage.fitHeightProperty().bind(primaryStage.heightProperty());
        layout1.getChildren().add(vbox);

        // Hiệu ứng ấn nút
        ScaleTransition scaleInButton1 = new ScaleTransition(Duration.millis(100), button1);
        scaleInButton1.setFromX(1);
        scaleInButton1.setFromY(1);
        scaleInButton1.setToX(1.2);
        scaleInButton1.setToY(1.2);

        ScaleTransition scaleOutButton1 = new ScaleTransition(Duration.millis(100), button1);
        scaleOutButton1.setFromX(1.2);
        scaleOutButton1.setFromY(1.2);
        scaleOutButton1.setToX(1);
        scaleOutButton1.setToY(1);

        ScaleTransition scaleInButton2 = new ScaleTransition(Duration.millis(100), button2);
        scaleInButton2.setFromX(1);
        scaleInButton2.setFromY(1);
        scaleInButton2.setToX(1.2);
        scaleInButton2.setToY(1.2);

        ScaleTransition scaleOutButton2 = new ScaleTransition(Duration.millis(100), button2);
        scaleOutButton2.setFromX(1.2);
        scaleOutButton2.setFromY(1.2);
        scaleOutButton2.setToX(1);
        scaleOutButton2.setToY(1);

        ScaleTransition scaleInButton3 = new ScaleTransition(Duration.millis(100), button3);
        scaleInButton3.setFromX(1);
        scaleInButton3.setFromY(1);
        scaleInButton3.setToX(1.2);
        scaleInButton3.setToY(1.2);

        ScaleTransition scaleOutButton3 = new ScaleTransition(Duration.millis(100), button3);
        scaleOutButton3.setFromX(1.2);
        scaleOutButton3.setFromY(1.2);
        scaleOutButton3.setToX(1);
        scaleOutButton3.setToY(1);



        button1.setOnMouseEntered(mouseEvent -> {
            scaleInButton1.play();
        });
        button1.setOnMouseExited(mouseEvent -> {
            scaleOutButton1.play();
        });
        button2.setOnMouseEntered(mouseEvent -> {
            scaleInButton2.play();
        });
        button2.setOnMouseExited(mouseEvent -> {
            scaleOutButton2.play();
        });
        button3.setOnMouseEntered(mouseEvent -> {
            scaleInButton3.play();
        });
        button3.setOnMouseExited(mouseEvent -> {
            scaleOutButton3.play();
        });




        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

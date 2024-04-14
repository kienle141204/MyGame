package com.example.mygame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
import javafx.geometry.Insets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class MenuGame extends Application {
    private Stage primaryStage;
    private Scene scene1,scene2;



    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Menu Game");

        Button button = new Button();
        button.getStyleClass().add("button");
        Image name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/name2.png"));
        ImageView nameView = new ImageView(name);
        button.setGraphic(nameView);
        nameView.setFitWidth(500); // Đặt chiều rộng tối đa
        nameView.setFitHeight(250); // Đặt chiều cao tối đa


        Button button1 = new Button();
        button1.getStyleClass().add("play-button");
        // Tải biểu tượng "play" từ tệp hình ảnh
        Image playIconImage = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/play1.png"));
        ImageView playIconImageView = new ImageView(playIconImage);
        // Đặt biểu tượng "play" làm đồng hành của nút
        button1.setGraphic(playIconImageView);
        playIconImageView.setFitWidth(150); // Đặt chiều rộng tối đa
        playIconImageView.setFitHeight(75); // Đặt chiều cao tối đa

        Button button2 = new Button();
        button2.getStyleClass().add("help-button");
        Image helpIconImage = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/help.png"));
        ImageView helpIconImageView = new ImageView(helpIconImage);
        button2.setGraphic(helpIconImageView);
        helpIconImageView.setFitWidth(150);
        helpIconImageView.setFitHeight(75);

        Button button3 = new Button();
        button3.getStyleClass().add("exit-button");
        Image exitIconImage = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/exit.png"));
        ImageView exitIconImageView = new ImageView(exitIconImage);
        button3.setGraphic(exitIconImageView);
        exitIconImageView.setFitWidth(150);
        exitIconImageView.setFitHeight(75);


        VBox vbox = new VBox(button,button1, button2, button3);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        StackPane.setMargin(vbox, new Insets(200, 0, 0, 0));

        StackPane layout1 = new StackPane();

        scene1 = new Scene(layout1, 1000, 750);

        scene1.getStylesheets().add("file:///E:/code/MyGame/src/main/java/style.css");

        Image image1 = new Image("file:E:/code/MyGame/src/main/java/image/mazeimage.png");
        ImageView backgroundImage = new ImageView(image1);
        layout1.getChildren().add(backgroundImage);
        backgroundImage.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundImage.fitHeightProperty().bind(primaryStage.heightProperty());
        layout1.getChildren().add(vbox);
        //layout1.getChildren().addAll(backgroundImage, vbox);

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


        //Khởi tạo scene2
        StackPane layout2 = new StackPane();
        Image backgroundScene2 = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/scene2.png"));
        ImageView backgroundView2 = new ImageView(backgroundScene2);
        layout2.getChildren().add(backgroundView2);

        scene2 = new Scene(layout2, 1000, 750);
        Button backButton = new Button();
        backButton.getStyleClass().add("back-button");
        Image image2 = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/back.png"));
        ImageView imageBack = new ImageView(image2);
        backButton.setGraphic(imageBack);
        layout2.getChildren().add(imageBack);
        layout2.getChildren().add(backButton);
        scene2.getStylesheets().add("file:///E:/code/MyGame/src/main/java/style.css");
        imageBack.setFitHeight(75);
        imageBack.setFitWidth(150);

        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(0, 32, 30, 0));

        ScaleTransition scaleInbackButton = new ScaleTransition(Duration.millis(100), backButton);
        scaleInbackButton.setFromX(1);
        scaleInbackButton.setFromY(1);
        scaleInbackButton.setToX(1.2);
        scaleInbackButton.setToY(1.2);

        ScaleTransition scaleOutbackButton = new ScaleTransition(Duration.millis(100), backButton);
        scaleOutbackButton.setFromX(1.2);
        scaleOutbackButton.setFromY(1.2);
        scaleOutbackButton.setToX(1);
        scaleOutbackButton.setToY(1);

        backButton.setOnMouseEntered(mouseEvent -> {
            scaleInbackButton.play();
        });
        backButton.setOnMouseExited(mouseEvent -> {
            scaleOutbackButton.play();
        });

        backButton.setOnAction(event->{
            primaryStage.setScene(scene1);
        });




        // Hiệu ứng khi ấn nút
        button2.setOnAction(event ->{
            primaryStage.setScene(scene2);
        });
        button3.setOnAction(event -> {
            Platform.exit();
        });



        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

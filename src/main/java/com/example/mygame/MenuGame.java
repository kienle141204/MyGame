package com.example.mygame;

import javafx.application.Application; // thư viện bắt buộc phải có
import javafx.application.Platform;
import javafx.geometry.Pos; // để điều chỉnh các box theo tọa độ
import javafx.scene.layout.StackPane; //tạo stackpane , giúp căn chỉnh nút (một phần nhỏ)
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button; // tạo nút và điều khiển nút
import javafx.scene.layout.HBox; // box ngang
import javafx.scene.layout.VBox;//box dọc
import javafx.scene.image.Image;// quản lý ảnh , nhưng không hiển thị ra giao diện người dùng
import javafx.scene.image.ImageView; // đưa ảnh ra giao diện người dùng
import javafx.animation.ScaleTransition; // hiệu ứng khi trỏ vào nút
import javafx.util.Duration; // điều khiển các hoạt động liên quan đến thời gian
import javafx.geometry.Insets; // chỉnh css ,lề , linh tinh

import java.io.FileInputStream;
import java.io.FileNotFoundException;

// Kiên : máy tôi chẳng hiểu sao không chạy được liên kết file theo đường dẫn tương đối
// ae pull về thì đổi lại đường dẫn của các file rồi chạy nhé

public class MenuGame extends Application {
    private Stage primaryStage;
    private Scene scene1;



    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Menu Game");
        // button chứa tên game , ấn vào hiện ra thông tin về nhóm
        Button button = new Button();
        button.getStyleClass().add("button");//add class css cho button
        Image name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/name2.png"));
        ImageView nameView = new ImageView(name);
        button.setGraphic(nameView);
        nameView.setFitWidth(500);
        nameView.setFitHeight(250);


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
        StackPane.setMargin(vbox, new Insets(100, 0, 0, 0));

        StackPane layout1 = new StackPane();

        scene1 = new Scene(layout1, 1000, 750);

        scene1.getStylesheets().add("file:///E:/code/MyGame/src/main/java/style.css");
        Image image1 = new Image("file:E:/code/MyGame/src/main/java/image/mazeimage.png");
        ImageView backgroundImage = new ImageView(image1);
        layout1.getChildren().add(backgroundImage);
        backgroundImage.fitWidthProperty().bind(primaryStage.widthProperty());// làm như này thì nền mới full cửa sổ được
        backgroundImage.fitHeightProperty().bind(primaryStage.heightProperty());
        layout1.getChildren().add(vbox);// 3 nút được hiển thị theo chiều dọc


        // Hiệu ứng ấn nút , to lên khi trỏ vào , nhỏ lại khi ra xa
        ButtonScaleEffect.addScaleEffect(button);
        ButtonScaleEffect.addScaleEffect(button1);
        ButtonScaleEffect.addScaleEffect(button2);
        ButtonScaleEffect.addScaleEffect(button3);


        button3.setOnAction(event -> {
            Platform.exit(); // kết thúc chương trình
        });
        //Khởi tạo scene2 , đây là cửa sổ mở ra khi ấn help
        // cài đặt một vào thứ cho scene2
        HelpScene helpScene = new HelpScene(primaryStage, scene1);
        //Scene scene2 = helpScene.getScene();
        // Hiệu ứng khi ấn nút help==button2 và exit==button3
        button2.setOnAction(event ->{
            primaryStage.setScene(helpScene.getScene());
        });
        //ấn và tên game hiện ra tên thành viên
        Member member = new Member(primaryStage, scene1);

        button.setOnAction(event->{
            primaryStage.setScene(member.getScene());
        });
        //
        PlayLevel playLevel = new PlayLevel(primaryStage, scene1);

        button1.setOnAction(event->{
            primaryStage.setScene(playLevel.getScene());
        });

        // Mặc định khi mở chương trình là chạy scene1
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

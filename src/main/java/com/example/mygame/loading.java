package com.example.mygame;

/*import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class loading extends Application {

    private static final String[] IMAGE_PATHS = {
            "E:/code/MyGame/src/main/java/image/frame1.png",
            "E:/code/MyGame/src/main/java/image/frame2.png",
            "E:/code/MyGame/src/main/java/image/frame3.png"
    };

    private static final Duration FRAME_DURATION = Duration.seconds(0.3); // Thời gian cho mỗi frame

    private int currentIndex = 0;
    private ImageView imageView = new ImageView();
    private Stage primaryStage;
    private Member member;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animated Image Loop Example");
        primaryStage.show();

        // Khởi tạo đối tượng Member
        try {
            member = new Member(primaryStage, scene);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Bắt đầu vòng lặp ảnh
        startImageLoop(primaryStage, scene);
    }

    public void startImageLoop(Stage primaryStage, Scene scene) {
        Timeline timeline = new Timeline(
                new KeyFrame(FRAME_DURATION, e -> {
                    imageView.setImage(loadImage(IMAGE_PATHS[currentIndex]));
                    currentIndex = (currentIndex + 1) % IMAGE_PATHS.length;
                })
        );
        timeline.setCycleCount(3); // Lặp lại vòng lặp 3 lần
        timeline.setOnFinished(event -> {
            // Mở cửa sổ Member khi kết thúc vòng lặp
            primaryStage.setScene(member.getScene());
        });
        timeline.play();
    }

    private Image loadImage(String path) {
        try {
            return new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null; // Trả về ảnh mặc định hoặc xử lý lỗi ở đây
        }
    }




    public static void main(String[] args) {
        launch(args);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}*/

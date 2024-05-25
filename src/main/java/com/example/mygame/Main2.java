package com.Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2 extends Application {
    private Map<KeyCode, Boolean> keys = new HashMap<>();
    private Character character;
    static Pane gamePane;
    private int[][] mazeData;
    private double canvasWidth = 1000;
    private double canvasHeight = 750;
    private MazeDrawer mazeDrawer;
    private List<Item> items = new ArrayList<>();
    private GraphicsContext gc; 
    public static ArrayList<Block> walls = new ArrayList<>();
    public static ArrayList<Rectangle> telegates = new ArrayList<>();
    private ImageView imageView;
    private boolean levelCompleted = false;
    private AnimationTimer timer;
    private Rectangle telegate;

    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dark Maze");
        Image icon = new Image(getClass().getResource("/Image/mazeicon.png").toString());
        primaryStage.getIcons().add(icon);

        // Tạo scene giới thiệu
        Pane introPane = new Pane();
        Image introBackgroundImage = new Image(getClass().getResource("/Image/mazeimage.png").toString());
        ImageView introBackgroundView = new ImageView(introBackgroundImage);
        introBackgroundView.setFitWidth(canvasWidth);
        introBackgroundView.setFitHeight(canvasHeight);
        introBackgroundView.setPreserveRatio(false);
        introPane.getChildren().add(introBackgroundView);
        Scene introScene = new Scene(introPane, canvasWidth, canvasHeight);

        // Kiểm tra sự kiện
        introScene.setOnMouseClicked(event -> {
            primaryStage.setScene(createGameScene(primaryStage));
            primaryStage.show();
        });

        primaryStage.setScene(introScene);
        primaryStage.show();
    }

    private Scene createGameScene(Stage primaryStage) {
            gamePane = new Pane();
            gamePane.getChildren().clear();
        
        Pane backgroundPane = new Pane();

        gamePane.getChildren().addAll(backgroundPane);

        Image icon = new Image(getClass().getResource("/Image/mazeicon.png").toString());

        primaryStage.getIcons().add(icon);

        Image backgroundImage = new Image(getClass().getResource("/Image/JungleMaze.jpg").toString());
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundPane.getChildren().add(backgroundView);

        // Đặt kích thước phù hợp cho ImageView
        backgroundView.setFitWidth(1000);
        backgroundView.setFitHeight(750);
        backgroundView.setPreserveRatio(false);

        mazeData = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1,1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 0, 0, 1, 0, 1, 1,1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 1, 0, 3, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        gc = canvas.getGraphicsContext2D();
        mazeDrawer = new MazeDrawer(mazeData, canvasWidth, canvasHeight);


        ImageView characterImage = new ImageView(new Image(getClass().getResource("/Image/NV1t1.png").toString()));
        character = new Character(characterImage, mazeDrawer);

        gamePane.getChildren().addAll(canvas, character);
        
        for (int i = 0; i < mazeDrawer.getRows(); i++) {
            for (int j = 0; j < mazeDrawer.getColumns(); j++) {
                if (mazeData[i][j] == 0) {
                    Block wall = new Block(j * mazeDrawer.getCellSizeWidth(), i * mazeDrawer.getCellSizeHeight(),mazeDrawer);
                }
                else if (mazeData[i][j] == 3){
                    telegate =new Rectangle(j * mazeDrawer.getCellSizeWidth(), i * mazeDrawer.getCellSizeHeight(), mazeDrawer.getCellSizeWidth(), mazeDrawer.getCellSizeHeight());
                    gamePane.getChildren().add(telegate);
                    telegates.add(telegate);
            }
        }
    }
    
       

        Item revealItem = new Item(200 , 195,30,30); // Initialize the item position correctly
        gamePane.getChildren().add(revealItem);
        items.add(revealItem);

        Image gateImage = new Image(getClass().getResource("/Image/gate.png").toString());
        imageView = new ImageView(gateImage);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        gamePane.getChildren().add(imageView);
        imageView.setTranslateX(500);
        imageView.setTranslateY(375);

        
        Scene scene = new Scene(gamePane, canvasWidth, canvasHeight);

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(canvas.getGraphicsContext2D());
                if(!revealItem.isTimerRunning()) mazeDrawer.highlightAreaAroundCharacter(gc, character.getX(), character.getY(), 100);
                 else mazeDrawer.drawFullMap(gc);
                checkItemCollision();
                updateItemVisibility() ;
                checkLevelCompletion(primaryStage);
                checktelegateCollision();
  
            }
        };
        timer.start();
        Scene gameScene = primaryStage.getScene();
        scene.setRoot(gamePane);    
        gameScene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        gameScene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        return gameScene;
    }
 


    public void update(GraphicsContext gc) {
        if (isPressed(KeyCode.UP)) {
           
                 character.animation.play();
                character.animation.setOffsetY(96);
                character.moveY(-1);
            
        } else if (isPressed(KeyCode.DOWN)) {
            
                character.animation.play();
                character.animation.setOffsetY(0);
                character.moveY(1);
            
        } else if (isPressed(KeyCode.RIGHT)) {
            
                character.animation.play();
                character.animation.setOffsetY(64);
                character.moveX(1);
            
        } else if (isPressed(KeyCode.LEFT)) {
            
                character.animation.play();
                character.animation.setOffsetY(32);
                character.moveX(-1);
            
        } else {
            character.animation.stop();
        }
    }   

    private void updateItemVisibility() {
        for (Item item : items) {
            if (character.getVisionBox().getBoundsInParent().intersects(item.getHitbox().getBoundsInParent())
                && !(character.getHitbox().getBoundsInParent().intersects(item.getHitbox().getBoundsInParent()))) {
                item.setItemVisible(true);
            } else {
                item.setItemVisible(false);
            }
        }
    }

    private void checkItemCollision() {
        for (Item item : items) {
            if (character.getHitbox().getBoundsInParent().intersects(item.getHitbox().getBoundsInParent())) {
                System.out.println(character.getBoundsInParent());
                if (item.getItemVisible()) {
                    item.disappear();
                    item.startTimer();
                }
            }
        }
    }  
    
    private void checktelegateCollision() {
        for (Rectangle telegate : telegates) {
            if (character.getHitbox().getBoundsInParent().intersects(telegate.getBoundsInParent())) {
                spawnCharacterRandomly();
                }
            }
        }

        private void spawnCharacterRandomly() {
            // Lặp cho đến khi tìm được một vị trí hợp lệ cho spawn
            while (true) {
                // Random vị trí
                    int min = 0;
                    int max = mazeData.length;
        
            // Tạo số ngẫu nhiên trong phạm vi từ min đến max (bao gồm cả max)
             int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        
                // Kiểm tra xem vị trí có hợp lệ không (không nằm trên tường)
                if (mazeData[randomNumber][randomNumber]==1) {
                    character.setTranslateX(randomNumber *mazeDrawer.getCellSizeWidth());
                    character.setTranslateY(randomNumber *mazeDrawer.getCellSizeHeight());
                    character.setX(randomNumber*mazeDrawer.getCellSizeWidth());
                    character.setY(randomNumber*mazeDrawer.getCellSizeHeight());
                    character.updateHitbox();
                    character.updateVisionBox();
                    break; // Thoát khỏi vòng lặp khi tìm được vị trí hợp lệ
                }
            }
        }
        
        // Check chạm vào cổng để hoàn thành 
        private void checkLevelCompletion(Stage primaryStage) {
        if (character.getHitbox().getBoundsInParent().intersects(imageView.getBoundsInParent())) { 
            levelCompleted = true;
            timer.stop();
            showLevelCompletionDialog(primaryStage);
        }
    }

    private void showLevelCompletionDialog(Stage primaryStage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        Label message = new Label("Level Completed!");
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            primaryStage.close();
            dialog.close();
        });
        dialogVbox.getChildren().addAll(message, okButton);

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    
}


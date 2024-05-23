package com.Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {
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
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dark Maze");
        gamePane = new Pane();
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
                { 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0 },
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
            }
        }
       

        Item revealItem = new Item(200 , 195,30,30); // Initialize the item position correctly
        gamePane.getChildren().add(revealItem);
        items.add(revealItem);

        
        Scene scene = new Scene(gamePane, canvasWidth, canvasHeight);

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(canvas.getGraphicsContext2D());
                if(!revealItem.isTimerRunning()) mazeDrawer.highlightAreaAroundCharacter(gc, character.getX(), character.getY(), 100);
                 else mazeDrawer.drawFullMap(gc);
                checkItemCollision();
                updateItemVisibility() ;
  
            }
        };
        timer.start();
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

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    
}


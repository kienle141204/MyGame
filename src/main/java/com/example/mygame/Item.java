package com.Game;

import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Item extends Region {
    private boolean visible;
    private ImageView imageView;
    private Rectangle hitbox;
    private Timeline timeline;
    private boolean timerRunning;

    public Item(double x, double y, double width, double height) {
        Image itemImage = new Image(getClass().getResource("/Image/phaohoa.png").toString());
        imageView = new ImageView(itemImage);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        getChildren().add(imageView);
        setTranslateX(x);
        setTranslateY(y);
        this.visible = true;
        
        
        // Tạo hitbox dựa trên vị trí và kích thước của Item
        hitbox = new Rectangle(x, y, 20, 20);

        timeline = new Timeline(new KeyFrame(Duration.seconds(3),event->{
            timerRunning = false;
        }));
        timeline.setCycleCount(1);
        timerRunning = false;

    }

    public boolean getItemVisible() {
        return visible;
    }
    public void setItemVisible(boolean visible){
        imageView.setVisible(visible);
    }

    public void disappear() {
        this.visible = false;
        getChildren().remove(imageView);
        Main.gamePane.getChildren().remove(this);
        
    }

    public void respawnAt(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
        hitbox.setX(x);
        hitbox.setY(y);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    public void startTimer(){
        timerRunning = true;
        timeline.playFromStart();
    }

    public boolean isTimerRunning(){
        return timerRunning;
    }
}

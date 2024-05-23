package com.Game;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

public class Character extends Pane {
    ImageView imageView;  
    private double x; // Tọa độ x của nhân vật
    private double y; // Tọa độ y của nhân vật
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 0;
    int width = 32;
    int height = 32;
    private Rectangle visionBox;
    private static final double VISION_WIDTH = 50; // Width of the vision box
    private static final double VISION_HEIGHT = 50;
    MazeDrawer mazeDrawer; // Thêm biến tham chiếu đến MazeDrawer
    private Rectangle hitbox;
    SpriteAnimation animation;

    public Character(ImageView imageView, MazeDrawer mazeDrawer) {
        this.mazeDrawer = mazeDrawer; // Set the mazeDrawer
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
        hitbox = new Rectangle(this.x,this.y,25,25);
        updateHitbox();
        this.x = 64; // Khởi tạo tọa độ x
        this.y = 64; // Khởi tạo tọa độ y
        setTranslateX(this.x);
        setTranslateY(this.y);
        // Initialize the vision box
        visionBox = new Rectangle (VISION_WIDTH, VISION_HEIGHT);
        updateVisionBox();
        
    }
    

    // Phương thức getter cho tọa độ x
    public double getX() {
        return this.x;
    }

    // Phương thức getter cho tọa độ y
    public double getY() {
        return this.y;
    }

    // Phương thức setter cho tọa độ x
    public void setX(double x) {
        this.x = x;
    }

    // Phương thức setter cho tọa độ y
    public void setY(double y) {
        this.y = y;
    }


        public void moveX(int dx) {
            double newX = this.x + dx;
            if (!willCollideWithWall(newX, this.y, hitbox.getWidth(), hitbox.getHeight())) {
                this.x = newX;
                setTranslateX(this.x);
                updateHitbox() ;
                updateVisionBox();
                //hitbox.setX(this.x);
            }
        }
    
        public void moveY(int dy) {
            double newY = this.y + dy;
            if (!willCollideWithWall(this.x, newY, hitbox.getWidth(), hitbox.getHeight())) {
                this.y = newY;
                setTranslateY(this.y);
                updateHitbox() ;
                updateVisionBox();
                //hitbox.setY(this.y);
            }
        }
    private void updateHitbox() {
        double centerX = getTranslateX() + width / 2;
        double centerY = getTranslateY() + height / 2;
        hitbox.setX(centerX - (25) / 2);
        hitbox.setY(centerY - (25) / 2);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    private void updateVisionBox() {
        double centerX = getTranslateX() + width / 2;
        double centerY = getTranslateY() + height / 2;
        visionBox.setX(centerX - VISION_WIDTH / 2);
        visionBox.setY(centerY - VISION_HEIGHT / 2);
    }

    public Rectangle getVisionBox() {
        return visionBox;
    }

    private boolean willCollideWithWall(double x, double y, double width, double height) {
        // Tạo hình chữ nhật đại diện cho vị trí mới của nhân vật
        Rectangle futureCharacter = new Rectangle(x, y, width, height);

        // Kiểm tra va chạm với từng tường
        for (Block wall: Main.walls) {
                if (futureCharacter.getBoundsInParent().intersects(wall.getwall().getBoundsInParent())) {
                    return true; // Nếu có va chạm, ngăn nhân vật di chuyển
                
            }
        }
        return false; // Nếu không có va chạm, cho phép nhân vật di chuyển
    }

    
}

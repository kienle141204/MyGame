package com.Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Character extends Pane {
    ImageView imageView;  
    private double x; // Tọa độ x của nhân vật
    private double y; // Tọa độ y của nhân vật
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 64;
    int width = 64;
    int height = 64;

    MazeDrawer mazeDrawer; // Thêm biến tham chiếu đến MazeDrawer

    SpriteAnimation animation;

    public Character(ImageView imageView, MazeDrawer mazeDrawer) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
        this.x = 0; // Khởi tạo tọa độ x
        this.y = 0; // Khởi tạo tọa độ y
        this.mazeDrawer = mazeDrawer; // Set the mazeDrawer
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

    public void moveX(int x) {
        boolean right = x > 0 ? true : false;
        for(int i = 0; i < Math.abs(x); i++) {
            if(right) {
                this.setTranslateX(this.getTranslateX() + 1);
                setX(getTranslateX());
            }
                
            else {
                this.setTranslateX(this.getTranslateX() - 1);
                setX(getTranslateX());
            }
              
        }
    }

    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for(int i = 0; i < Math.abs(y); i++) {
            if(down){
                this.setTranslateY(this.getTranslateY() + 1);
                setY(getTranslateY());
            } 
                
            else {
                this.setTranslateY(this.getTranslateY() -1);
                setY(getTranslateY());

            }
                
        }
    }

    public boolean isWallCollision(double x, double y) {
        // Kiểm tra xem nhân vật có chạm vào tường không
        int[][] mazeData = mazeDrawer.getMazeData();
        double cellSizeWidth = mazeDrawer.getCellSizeWidth();
        double cellSizeHeight = mazeDrawer.getCellSizeHeight();
    
        // Làm tròn tọa độ x và y đến số nguyên lớn nhất
        int column = ((int) Math.ceil(x / cellSizeWidth));
        int row = ((int) Math.ceil(y / cellSizeHeight));
    
        // Kiểm tra xem ô mê cung có là tường không
        return mazeData[row][column] == 0;
    }
}

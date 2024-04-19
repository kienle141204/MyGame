package com.example.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImageRectangle extends Rectangle {
    private ImageView imageView;

    public ImageRectangle(double width, double height, Color color, Image image) {
        super(width, height, color);
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
    }

    public ImageView getImageView() {
        return imageView;
    }
}

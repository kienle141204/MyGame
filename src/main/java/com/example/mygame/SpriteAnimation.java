package com.example.mygame;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
    private final ImageView imageView;
    private final int count;
    private final int offsetX;
    private final int width;
    private final int height;
    public SpriteAnimation(ImageView imageView, Duration duration, int count, int width, int height) {
        this.imageView = imageView;
        this.count = count;
        this.width = width;
        this.height = height;
        this.offsetX = 0;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double frac) {
        int frameCount = MazeDisplayer.NUM_OF_FRAMES; // Số frames trên mỗi hàng
        int index = (int) Math.min(Math.floor(frac * count), count - 1);
        int colIndex = index % frameCount;
        int x = colIndex * width + offsetX;
        imageView.setViewport(new javafx.geometry.Rectangle2D(x, 0, width, height));

    }
}




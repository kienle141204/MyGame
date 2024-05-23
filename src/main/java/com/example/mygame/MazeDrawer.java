package com.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MazeDrawer {
    private int[][] mazeData;
    private int rows;
    private int columns;
    private double cellSizeWidth;
    private double cellSizeHeight;

    public MazeDrawer(int[][] mazeData, double canvasWidth, double canvasHeight) {
        this.mazeData = mazeData;
        this.rows = mazeData.length;
        this.columns = mazeData[0].length;
        this.cellSizeWidth = canvasWidth / columns;
        this.cellSizeHeight = canvasHeight / rows;
    }

    public int[][] getMazeData() {
        return this.mazeData;
    }

    public double getCellSizeWidth() {
        return cellSizeWidth;
    }

    public double getCellSizeHeight() {
        return cellSizeHeight;
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    public void highlightAreaAroundCharacter(GraphicsContext gc, double characterX, double characterY, double radius) {
        gc.clearRect(0, 0, 1000, 750);

        Image wallImage = new Image(getClass().getResource("/Image/wall1.jpg").toString());

        int characterColumn = (int) (characterX / cellSizeWidth);
        int characterRow = (int) (characterY / cellSizeHeight);
        int startColumn = Math.max(0, characterColumn - 1);
        int endColumn = Math.min(columns, characterColumn + 2);
        int startRow = Math.max(0, characterRow-1);
        int endRow = Math.min(rows - 1, characterRow + 2);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i >= startRow && i <= endRow && j >= startColumn && j <= endColumn) {
                    if (mazeData[i][j] == 0) {
                        gc.drawImage(wallImage, j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                    } else {
                        if ((i == startRow && j >= startColumn && j <= endColumn) ||
                            (i == endRow && j >= startColumn && j <= endColumn) ||
                            (j == endColumn && i >= startRow && i <= endRow)) {
                            gc.setFill(Color.rgb(0, 0, 0, 0.5));
                            gc.fillRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                        } else {
                            gc.clearRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                        }
                    }
                } else {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                }
            }
        }
    }

    public void drawFullMap(GraphicsContext gc) {
        Image wallImage = new Image(getClass().getResource("/Image/wall1.jpg").toString());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mazeData[i][j] == 0) {
                    gc.drawImage(wallImage, j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                } else {
                    gc.clearRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                }
            }
        }
    }
}

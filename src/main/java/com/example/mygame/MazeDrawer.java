package com.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MazeDrawer {
    private int[][] mazeData;
    private int rows;
    private int columns;
    private double cellSizeWidth;
    private double cellSizeHeight;

    // Constructor
    public MazeDrawer(int[][] mazeData, double canvasWidth, double canvasHeight) {
        this.mazeData = mazeData;
        this.rows = mazeData.length;
        this.columns = mazeData[0].length;
        this.cellSizeWidth = canvasWidth / columns;
        this.cellSizeHeight = canvasHeight / rows;
    }

    // Phương thức để trả về mazeData
    public int[][] getMazeData() {
        return this.mazeData;
    }

    public void drawMaze(GraphicsContext gc) {
        // Calculate cell size
        double cellSizeWidth = 750.0 / columns;
        double cellSizeHeight = 750.0 / rows;
        // Iterate through the maze data and draw walls and empty spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mazeData[i][j] == 0) {
                    // Wall, draw a black rectangle
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                }
            }
        }
    }

    public boolean isWall(int x, int y) {
        return mazeData[y][x] == 0;
    }

    public double getCellSizeWidth() {
        return cellSizeWidth;
    }

    public double getCellSizeHeight() {
        return cellSizeHeight;
    }

    public void highlightAreaAroundCharacter(GraphicsContext gc, double characterX, double characterY, double radius) {
        // Clear the canvas
        gc.clearRect(0, 0, 750, 750);

        // Load wall image
        Image wallImage = new Image(getClass().getResource("/Image/wall1.jpg").toString());

        // Calculate the range of cells to draw around the character
        int characterColumn = (int) (characterX / cellSizeWidth);
        int characterRow = (int) (characterY / cellSizeHeight);
        int startColumn = Math.max(0, characterColumn - 1);
        int endColumn = Math.min(columns , characterColumn + 3);
        int startRow = Math.max(0, characterRow );
        int endRow = Math.min(rows - 1, characterRow + 2);
        // Create a BoxBlur effect for the blur effect
        BoxBlur blur = new BoxBlur();
        blur.setWidth(5); // Adjust the width of the blur effect as needed
        blur.setHeight(5); // Adjust the height of the blur effect as needed

        // Iterate through the range of cells and draw walls or clear space
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
               
                if (i >= startRow && i <= endRow && j >= startColumn && j <= endColumn) {
                  
                    // Draw wall or clear space around the character
                    if (mazeData[i][j] == 0) {
                        // Draw wall
                        gc.drawImage(wallImage, j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);

                    }
                    else{
                    if((i==startRow&& j >= startColumn && j <=endColumn)||(i==endRow&& j >= startColumn && j <=endColumn)||(j == endColumn&&i >= startRow && i <= endRow)){
                        gc.setFill(Color.rgb(0, 0, 0, 0.5));
                        gc.fillRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                    }
                     else {
                        // Draw empty space (clear the area)
                        gc.clearRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);
                     }
                    }
                } 
               
                else {
                    // Fill the area not close to the character with black color
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * cellSizeWidth, i * cellSizeHeight, cellSizeWidth, cellSizeHeight);

                }
            }
        }
    }
}

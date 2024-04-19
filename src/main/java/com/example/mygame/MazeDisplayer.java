package com.example.mygame;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


class MazeDisplayer
{
	 private static final int WIDTH = 400;
	 private static final int HEIGHT = 400;
	 private static final int RECT_SIZE = 20;
	 private Rectangle character;
	 private int characterX = 1;
	 private int characterY = 1;
	private GridPane root ; 
	private int [][] mazeData ;
	
	public GridPane getRoot() {
		return root;
	}
	public void setRoot(GridPane root) {
		this.root = root;
	}
	
	public int [][] getMazeData() {
		return mazeData;
	}
	public void setMazeData(int [][] mazeData) {
		this.mazeData = mazeData;
	}
	
	public MazeDisplayer(GridPane root, int [][] mazeData) 
	{
		setRoot(root) ;
		setMazeData(mazeData );
	}
	 private void drawCharacter() 
	 {
	        character = new Rectangle(RECT_SIZE, RECT_SIZE, Color.RED);
	        root.add(character, characterX, characterY);
	 }
	 private void drawMaze() 
	 {
		 for (int i = 0; i < mazeData.length; i++) {
		        for (int j = 0; j < mazeData[i].length; j++) {
		            if (mazeData[i][j] == 0) 
		            {
		            	Image name;
						try {
							name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/playlv1.png"));
							ImageView wall = new ImageView(name) ;
		                    wall.setFitWidth(20);
		                    wall.setFitHeight(20);
		                    root.add(wall, j, i);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		            } else {
		                Image name;
						try {
							name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/help.png"));
							ImageView path = new ImageView(name) ;
		                    path.setFitWidth(20);
		                    path.setFitHeight(20);
		                    root.add(path, j, i);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		            	
		            }
		        }
		    } 
	 }
	 private void handleKeyPress(KeyCode code) {
	        switch (code) {
	            case W:
	                moveCharacter(0, -1); // Lên
	                break;
	            case S:
	                moveCharacter(0, 1); // Xuống
	                break;
	            case A:
	                moveCharacter(-1, 0); // Trái
	                break;
	            case D:
	                moveCharacter(1, 0); // Phải
	                break;
	            default:
	                break;
	        }
	    }

	    private void moveCharacter(int dx, int dy) {
	        int newX = characterX + dx;
	        int newY = characterY + dy;
	        
	        // Kiểm tra xem có thể di chuyển tới vị trí mới hay không
	        if (mazeData[newY][newX] != 0) {
	            root.getChildren().remove(character);
	            characterX = newX;
	            characterY = newY;
	            root.add(character, characterX, characterY);
	        }
	    }
	public Scene getSceneMaze(int x, int y) //throws FileNotFoundException // x, y = toa do cua Scene 
	{
		drawMaze() ; 
		drawCharacter() ; 
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		return scene ; 
	}
}

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
import javafx.util.Duration;
import javafx.animation.PauseTransition;


class MazeDisplayer
{
	public static  int NUM_OF_FRAMES = 3;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 750;
	private static final int RECT_SIZE = 32;
	private ImageView character;
	private int characterX = 1;
	private int characterY = 1;
	private GridPane root ;
	private int [][] mazeData ;
	private Image characterImage;

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
	private void drawCharacter() throws FileNotFoundException
	{
		character = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/www.png")));
		SpriteAnimation animation = new SpriteAnimation(character,
		                Duration.millis(1000), 3, 32, 32);
		animation.setCycleCount(javafx.animation.Animation.INDEFINITE);
		animation.play();
		character.setFitWidth(RECT_SIZE);
		character.setFitHeight(RECT_SIZE);
		root.add(character, characterX, characterY);
		//
	}
	private void drawMaze()
	{
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[i].length; j++) {
				if (mazeData[i][j] == 0)
				{
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/wall.png"));
						ImageView wall = new ImageView(name) ;
						wall.setFitWidth(32);
						wall.setFitHeight(32);
						root.add(wall, j, i);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/path.jpg"));
						ImageView path = new ImageView(name) ;
						path.setFitWidth(32);
						path.setFitHeight(32);
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
			PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.2)); // Thời gian tạm dừng trước khi di chuyển
			pauseTransition.setOnFinished(event -> {
				characterX = newX;
				characterY = newY;
				root.getChildren().remove(character);
				root.add(character, characterX, characterY);
			});
			pauseTransition.play();
		}
	}
	public Scene getSceneMaze(int x, int y) throws FileNotFoundException // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		return scene ;
	}

}
package com.example.mygame;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.animation.KeyFrame;




class MazeDisplayer
{
	public static  int NUM_OF_FRAMES = 3;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 750;
	private static final int RECT_SIZE = 32;
	private ImageView character;
	private double characterX = 32;
	private double characterY = 32;
	private Pane root ;
	private int [][] mazeData ;
	private int currentFrame = 0;
	private Timeline timeline ;
	private StackPane stackPane ;
	

	public Pane getRoot() {
		return root;
	}
	public void setRoot(Pane root) {
		this.root = root;
	}

	public int [][] getMazeData() {
		return mazeData;
	}
	public void setMazeData(int [][] mazeData) {
		this.mazeData = mazeData;
	}

	public MazeDisplayer(Pane root, int [][] mazeData)
	{
		setRoot(root) ;
		setMazeData(mazeData);
	}
	private void drawCharacter() throws FileNotFoundException
	{
		character = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/hold1.png")));
		SpriteAnimation animation = new SpriteAnimation(character,
		                Duration.millis(1000), 3, 32, 32);
		animation.setCycleCount(javafx.animation.Animation.INDEFINITE);
		animation.play();
		character.setFitWidth(RECT_SIZE);
		character.setFitHeight(RECT_SIZE);
		character.setX(characterX);
		character.setY(characterY);
		root.getChildren().add(character);
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
						wall.setX(j*32);
						wall.setY(i*32);
						root.getChildren().add(wall);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else if (mazeData[i][j] == 1) {
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/path.jpg"));
						ImageView path = new ImageView(name) ;
						path.setFitWidth(32);
						path.setFitHeight(32);
						path.setX(j*32);
						path.setY(i*32);
						root.getChildren().add(path);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

				} else if(mazeData[i][j] == 2)
				{
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/gate.png"));
						ImageView gate = new ImageView(name) ;
						gate.setFitWidth(32);
						gate.setFitHeight(32);
						gate.setX(j*32);
						gate.setY(i*32);
						root.getChildren().add(gate);
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
				moveCharacter(0, -4); // Lên
				updateFrame(0,1,2,3);
				break;
			case S:
				moveCharacter(0, 4); // Xuống
				updateFrame(4,5,6,7);
				break;
			case A:
				moveCharacter(-4, 0); // Trái
				updateFrame(8,9,10,11);
				break;
			case D:
				moveCharacter(4, 0); // Phải
				updateFrame(12,13,14,15);
				break;
			default:
				break;
		}

	}

	private void moveCharacter(double dx, double dy) {
		double newX = characterX + dx;
		double newY = characterY + dy;
		if(check(newX,newY)==1){
			characterX = newX;
			characterY = newY;
			root.getChildren().remove(character);
			character.setX(characterX);
			character.setY(characterY);
			root.getChildren().add(character);

		}
		if (check(newX,newY)==2){
			showWinMessage();
		}
	}

	private int check(double characterX , double characterY) {
		int top = (int) ((characterY+5)/ 32);
		int left = (int) ((characterX+5)/ 32);
		int bottom = (int) (((characterY-5)+ 32) / 32);
		int right = (int) (((characterX-5) + 32) / 32);

		// Kiểm tra giới hạn mảng
		if (top < 0 || top >= mazeData.length || left < 0 || left >= mazeData[0].length ||
				bottom < 0 || bottom >= mazeData.length || right < 0 || right >= mazeData[0].length) {
			return 0;
		}

		if(mazeData[top][left] == 1 && mazeData[top][right] == 1 && mazeData[bottom][left] == 1 && mazeData[bottom][right] == 1) return 1;
		if(mazeData[bottom][right]==2) return 2;
		return 0;

	}
	public void updateFrame(int... frameIndices) {
		currentFrame = (currentFrame + 1) % frameIndices.length;
		int frameIndex = frameIndices[currentFrame];

		// Tạo đường dẫn tới tập tin ảnh mới dựa trên hành động
		String imagePath = "";
		switch (frameIndex) {
			case 0: // Di chuyển lên
				imagePath = "E:/code/MyGame/src/main/java/image/up1.png";
				break;
			case 4: // Di chuyển xuống
				imagePath = "E:/code/MyGame/src/main/java/image/down1.png";
				break;
			case 8: // Di chuyển qua trái
				imagePath = "E:/code/MyGame/src/main/java/image/left1.png";
				break;
			case 12: // Di chuyển qua phải
				imagePath = "E:/code/MyGame/src/main/java/image/right1.png";
				break;
			case 16:
				imagePath = "E:/code/MyGame/src/main/java/image/hold1.png";
				break;
			default:
				break;
		}
		timeline.playFromStart();

		// Nếu đường dẫn hợp lệ, cập nhật hình ảnh của characterImageView
		if (!imagePath.isEmpty()) {
			try {
				Image spriteSheet = new Image(new FileInputStream(imagePath));
				character.setImage(spriteSheet);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	private void showWinMessage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Chúc mừng!");
		alert.setHeaderText(null);
		alert.setContentText("Bạn đã chiến thắng!");

		alert.showAndWait();
	}

	public Scene getSceneMaze(int x, int y) throws FileNotFoundException // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		//PauseTransition pause = new PauseTransition(Duration.millis(1000));
		/*scene.setOnKeyReleased(e -> {
			pause.setOnFinished(event->{
				updateFrame(16);
			});
			pause.play();

		});*/
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}

}
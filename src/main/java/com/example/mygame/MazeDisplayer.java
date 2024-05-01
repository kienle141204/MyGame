package com.example.mygame;

import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.animation.KeyFrame;




class MazeDisplayer
{
	public static  int NUM_OF_FRAMES = 3;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 750;
	private int RECT_SIZE;
	private ImageView character;
	private ImageView gate;
	private double characterX;
	private double characterY=0;
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

	public MazeDisplayer(Pane root, int [][] mazeData, int RECT_SIZE,int characterX)
	{
		this.characterX = characterX;
		this.characterY = characterY;
		this.RECT_SIZE = RECT_SIZE;
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
	}
	private void lightCharacter(){
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.rgb(255,255,255,0.4)); // Màu sắc của ánh sáng
		dropShadow.setRadius(500);// Bán kính của ánh sáng
		dropShadow.setSpread(0.95);
		// Áp dụng hiệu ứng ánh sáng cho nhân vật
		character.setEffect(dropShadow);
	}
	private void lightGate(){
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.rgb(255,255,255,0.4)); // Màu sắc của ánh sáng
		dropShadow.setRadius(500);// Bán kính của ánh sáng
		dropShadow.setSpread(0.95);
		// Áp dụng hiệu ứng ánh sáng cho nhân vật
		gate.setEffect(dropShadow);
	}

	private void drawMaze()
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.95);

		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[i].length; j++) {
				if (mazeData[i][j] == 0 )
				{
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/wall.png"));
						ImageView wall = new ImageView(name) ;
						wall.setFitWidth(RECT_SIZE);
						wall.setFitHeight(RECT_SIZE);
						wall.setX(j*RECT_SIZE);
						wall.setY(i*RECT_SIZE);
						root.getChildren().add(wall);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else if (mazeData[i][j] == 1 || mazeData[i][j] == 3) {
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/path.jpg"));
						ImageView path = new ImageView(name) ;
						path.setFitWidth(RECT_SIZE);
						path.setFitHeight(RECT_SIZE);
						path.setX(j*RECT_SIZE);
						path.setY(i*RECT_SIZE);
						root.getChildren().add(path);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

				} else if (mazeData[i][j] == 9) {
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/ice.png"));
						ImageView path = new ImageView(name) ;
						path.setFitWidth(RECT_SIZE);
						path.setFitHeight(RECT_SIZE);
						path.setX(j*RECT_SIZE);
						path.setY(i*RECT_SIZE);
						root.getChildren().add(path);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}
	private void drawGate(){
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[i].length; j++) {
				if (mazeData[i][j] < 0) {
					Image name;
					try {
						name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/gate.png"));
						gate = new ImageView(name);
						gate.setFitWidth(RECT_SIZE);
						gate.setFitHeight(RECT_SIZE);
						gate.setX(j * RECT_SIZE);
						gate.setY(i * RECT_SIZE);

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
	private void darkMap(Pane root){
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.95);
		for(Node node : root.getChildren()){
			if(node instanceof ImageView){
				ImageView imageView = (ImageView) node;
				imageView.setEffect(colorAdjust);
			}
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
		if (check(newX,newY)==-1){
			showWinMessage("chúc mừng bạn đã vượt qua màn 1 , hãy cố gắng lên nhé");
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-2){
			showWinMessage("chúc mừng bạn đã vượt qua màn 2 , hãy cố gắng lên nhé");
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-3){
			showWinMessage("chúc mừng bạn đã vượt qua màn 3 , hãy cố gắng lên nhé");
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-4){
			showWinMessage("chúc mừng bạn đã vượt qua màn 4 , hãy cố gắng lên nhé");
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-5){
			showWinMessage("chúc mừng bạn đã vượt qua màn 5 , hãy cố gắng lên nhé");
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if(check(newX,newY)==3){
			characterX = 13*RECT_SIZE;
			characterY = 4*RECT_SIZE;
			root.getChildren().remove(character);
			character.setX(characterX);
			character.setY(characterY);
			root.getChildren().add(character);
		}

	}

	private int check(double characterX , double characterY) {
		int top = (int) ((characterY+5)/ RECT_SIZE);
		int left = (int) ((characterX+5)/ RECT_SIZE);
		int bottom = (int) (((characterY-5)+ RECT_SIZE) / RECT_SIZE);
		int right = (int) (((characterX-5) + RECT_SIZE) / RECT_SIZE);

		// Kiểm tra giới hạn mảng
		if (top < 0 || top >= mazeData.length || left < 0 || left >= mazeData[0].length ||
				bottom < 0 || bottom >= mazeData.length || right < 0 || right >= mazeData[0].length) {
			return 0;
		}

		if(mazeData[top][left] == 1 && mazeData[top][right] == 1 && mazeData[bottom][left] == 1 && mazeData[bottom][right] == 1) return 1;
		if(mazeData[top][left] == -1 || mazeData[top][right] == -1 || mazeData[bottom][left] == -1 || mazeData[bottom][right] == -1) return -1;
		if(mazeData[top][left] == -2 || mazeData[top][right] == -2 || mazeData[bottom][left] == -2 || mazeData[bottom][right] == -2) return -2;
		if(mazeData[top][left] == -3 || mazeData[top][right] == -3 || mazeData[bottom][left] == -3 || mazeData[bottom][right] == -3) return -3;
		if(mazeData[top][left] == -4 || mazeData[top][right] == -4 || mazeData[bottom][left] == -4 || mazeData[bottom][right] == -4) return -4;
		if(mazeData[top][left] == -5 || mazeData[top][right] == -5 || mazeData[bottom][left] == -5 || mazeData[bottom][right] == -5) return -5;
		if(mazeData[bottom][left]==3) return 3;
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


	public void showWinMessage(String s) {
		// Tạo một Alert với loại NONE
		Alert alert = new Alert(Alert.AlertType.NONE);
		alert.setHeaderText(null);
		alert.setContentText(s);

		// Tạo hình ảnh nền và thiết lập nền cho ô thông báo
		Image backgroundImage = new Image("file:E:/code/MyGame/src/main/java/image/mazeimage.png");
		BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
		Background background = new Background(backgroundImg);
		alert.getDialogPane().setBackground(background);

		// Tạo một ButtonType "OK" và thêm vào danh sách các loại nút của Alert
		ButtonType okButtonType = new ButtonType("", ButtonBar.ButtonData.OK_DONE);
		alert.getButtonTypes().add(okButtonType);

		// Lấy nút "OK" từ DialogPane và thiết lập biểu tượng cho nó
		Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
		Image okImage = new Image("file:E:/code/MyGame/src/main/java/image/okButton1.png");
		ImageView okImageView = new ImageView(okImage);
		okButton.setGraphic(okImageView);
		okButton.setStyle("-fx-background-color: transparent;\n" +
				"    -fx-border-width: 0;\n" +
				"    -fx-padding: 0;");


		// Hiển thị ô thông báo và đợi cho đến khi nó được đóng
		alert.showAndWait();
	}

	public Scene getSceneMaze1(int x, int y) throws  FileNotFoundException{
		drawMaze() ;
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze2(int x, int y) throws FileNotFoundException // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze3(int x, int y) throws FileNotFoundException // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze4(int x, int y) throws FileNotFoundException // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawGate();
		darkMap(root);
		drawCharacter() ;
		lightCharacter();
		lightGate();
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze5(int x, int y) throws FileNotFoundException // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawGate();
		darkMap(root);
		drawCharacter() ;
		lightCharacter();
		lightGate();
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}

}
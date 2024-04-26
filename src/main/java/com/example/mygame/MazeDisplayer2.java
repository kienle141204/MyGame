package com.example.mygame;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javafx.scene.control.Button;
//import java.awt.Checkbox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//import javax.print.attribute.standard.PrinterIsAcceptingJobs;


class MazeDisplayer2
{
	public static  int NUM_OF_FRAMES = 3;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static final int RECT_SIZE = 20;
	private ImageView character;
	private int characterX = 15;
	private int characterY = 0;
	private GridPane root ; 
	private int [][] mazeData ;
	protected static int limM, limN ; 
	
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
	
	public MazeDisplayer2(GridPane root, int [][] mazeData)
	{
		setRoot(root) ;
		setMazeData(mazeData );
	}
	private void drawCharacter() throws FileNotFoundException
	{
		character = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_right_1.png")));
		character.setFitWidth(RECT_SIZE);
		character.setFitHeight(RECT_SIZE);
		root.add(character, characterX, characterY);
	}
	 private void drawMaze() 
	 {
		 limM = mazeData.length ; limN = mazeData[0].length ; 
		 for (int i = 0; i < limM; i++) {
		        for (int j = 0; j < limN; j++) {
		            if (mazeData[i][j] == 0) 
		            {
		            	Image name;
						try {
							name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/wall.png"));
							ImageView wall = new ImageView(name) ;
		                    wall.setFitWidth(20);
		                    wall.setFitHeight(20);
		                    root.add(wall, j, i);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		            } else if((mazeData[i][j] == 1) || (mazeData[i][j] == 4)) 
		            {
		                Image name;
						try {
							name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/path.jpg"));
							ImageView path = new ImageView(name) ;
		                    path.setFitWidth(20);
		                    path.setFitHeight(20);
		                    root.add(path, j, i);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		            }
						else if(mazeData[i][j] == 2)
						{
							Image name;
							try {
								name = new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/gate.png"));
								ImageView gate = new ImageView(name) ;
			                    gate.setFitWidth(20);
			                    gate.setFitHeight(20);
			                    root.add(gate, j, i);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
		            }	
		        }
		    
	 }
	 static int fw = 0, fs = 0, fa = 0, fd = 0 ; 
	 private void handleKeyPress(KeyCode code) throws FileNotFoundException  {
	        switch (code) {
	            case W:
	            {
	                moveCharacter(0, -1, "W"); // Lên
	                fw = 1 - fw ; 
	                break;
	            }
	            case S:
	            {
	            	moveCharacter(0, 1, "S"); // Xuống
	            	fs = 1 - fs ; 
	            	break;
	            }
	                
	            case A:
	            {
	            	moveCharacter(-1, 0, "A"); // Trái
	                fa = 1 - fa ; 
	            	break;
	            }
	            case D:
	            {
	            	moveCharacter(1, 0, "D"); // Phải
	                fd = 1 - fd ; 
	            	break;
	            }
	            default:
	                break;
	        }
	    }
	 	boolean CheckWin(int x, int y) 
	 	{
	 		if(mazeData[x][y] == 2) return true ; 
	 		return false ;
	 	}
	 	public ImageView spritecharacter(String signal) throws FileNotFoundException // setup animation cho character
	 	{
	 		ImageView character1 = null ; 
	 		if(signal == "W") 
			{
				if(fw == 0)
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_up_1.png")));
				}
				else 
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_up_2.png")));
				}
			}
			else if(signal == "S")
			{
				if(fs == 0)
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_down_1.png")));
				}
				else 
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_down_2.png")));
				}
			}
			else if(signal == "A")
			{
				if(fa == 0)
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_left_1.png")));
				}
				else 
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_left_2.png")));
				}
			}
			else if(signal == "D")
			{
				if(fd == 0)
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_right_1.png")));
				}
				else 
				{
					character1 = new ImageView(new Image(new FileInputStream("E:/code/MyGame/src/main/java/image/boy_right_2.png")));
				}
			}
	 		return character1 ; 
	 	}
//	 	protected void transport_gate(int x, int y) 
//	 	{
//	 		if(mazeData[x][y] == 4) 
//	 		{
//	 			characterX = 1 ; 
//	 			characterY = 1 ; 
//	 		}
//	 	}
	    private void moveCharacter(int dx, int dy, String signal) throws FileNotFoundException {
	        int newX = characterX + dx;
	        int newY = characterY + dy;

	        // Kiểm tra xem có thể di chuyển tới vị trí mới hay không
	        if (mazeData[newY][newX] != 0) {
	            root.getChildren().remove(character);
	            character = spritecharacter(signal) ;
	            characterX = newX;
	            characterY = newY;
	            if(mazeData[characterY][characterX] == 4) 
		 		{
		 			characterX = 1 ; 
		 			characterY = 1 ; 
		 		}
	            if(mazeData[characterY][characterX] == 2) // alert thong bao chuc mung qua man
	            {
	            	Alert alert1 = new Alert(Alert.AlertType.INFORMATION) ;
	            	alert1.setTitle("Hoàn Thành Trò Chơi!!") ; 
	            	alert1.setContentText("Bạn đã hoàn thành trò chơi!");
	            	Button button1 = new Button("Cancel") ;
	            	
//	            	alert1.getButtonTypes(button1) ; 
	            	alert1.showAndWait();
	            }
	            root.add(character, characterX, characterY);
	        }
	    }
	public Scene getSceneMaze(int x, int y) throws FileNotFoundException  // x, y = toa do cua Scene 
	{
		drawMaze() ; 
		drawCharacter() ; 
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> {
			try {
				handleKeyPress(e.getCode());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		return scene ; 
	}
}

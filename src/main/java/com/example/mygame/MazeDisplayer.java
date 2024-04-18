package application;

import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


class MazeDisplayer
{
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
	
	public Scene getSceneMaze(int x, int y) //throws FileNotFoundException // x, y = toa do cua Scene 
	{
		for (int i = 0; i < mazeData.length; i++) {
	        for (int j = 0; j < mazeData[i].length; j++) {
//	            Rectangle rect ; 
	            if (mazeData[i][j] == 1) 
	            {
	            	Image name;
					try {
						name = new Image(new FileInputStream("C:\\Users\\MyPC\\Downloads/wall.jpg"));
						ImageView wall = new ImageView(name) ;
	                    wall.setFitWidth(30);
	                    wall.setFitHeight(30);
	                    root.add(wall, j, i);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } else {
	                Image name;
					try {
						name = new Image(new FileInputStream("C:\\Users\\MyPC\\OneDrive\\Documents/path.jpg"));
						ImageView path = new ImageView(name) ;
	                    path.setFitWidth(30);
	                    path.setFitHeight(30);
	                    root.add(path, j, i);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
	            	
	            }
//	            root.add(rect, j, i);
	        }
	    }
		Scene scene = new Scene(root, 620, 620);
		
		return scene ; 
	}

}

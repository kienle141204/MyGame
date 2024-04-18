package application;

import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
	
	public Scene getSceneMaze(int x, int y) // x, y = toa do cua Scene 
	{
		for (int i = 0; i < mazeData.length; i++) {
	        for (int j = 0; j < mazeData[i].length; j++) {
	            Rectangle rect ; 
	            if (mazeData[i][j] == 1) 
	            {
	            	rect = new Rectangle(30, 30);
	                rect.setFill(Color.BLACK);
	            } else {
	            	rect = new Rectangle(30, 30);
	                rect.setFill(Color.WHITE);
	            }
	            root.add(rect, j, i);
	        }
	    }
		Scene scene = new Scene(root, 620, 620);
		
		return scene ; 
	}

}
package views;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Hero;
import model.world.Cell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;



public class BoardView {
	
    private static final int GRID_SIZE = 15;
    private static final double CELL_SIZE = 45;
    private static Cell[][] map;
    
    private static GridPane gridPane;

	public static void initMap(Stage currentStage, boolean fs) throws IOException, MovementException, NotEnoughActionsException {
		map = Game.map;
		gridPane = new GridPane();
	    for (int row = 0; row < GRID_SIZE; row++) {
	    	for (int col = 0; col < GRID_SIZE; col++) {
	    		Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
	            rectangle.setFill(Color.BLACK);
	            rectangle.setStroke(Color.WHITE);
	            rectangle.setStrokeType(StrokeType.INSIDE);
	    	    
	    	    GridPane.setRowIndex(rectangle, row);
	            GridPane.setColumnIndex(rectangle, col);
	            gridPane.getChildren().add(rectangle);
	            gridPane.setAlignment(Pos.CENTER);
	        }
	    }
	    
	    try {
    		Game.loadHeroes("C:\\Users\\PC\\Documents\\GitHub\\Mile2\\Last of Us - Legacy\\src\\Heros.csv");
    	}
    	catch(FileNotFoundException e) {
    		System.out.println("Heros.csv file is not found");
    		return;
    	}
    	Hero h = Game.availableHeroes.remove(0);
    	Game.startGame(h);
    	
    	
 
    	h.move(Direction.UP);
    	
    	BoardView.updateMap(getVisibleCells());
    	
    	
    	Scene scene = new Scene(gridPane, 1500, 800);
    	currentStage.setTitle("15x15 grid");
    	currentStage.setScene(scene);
    	currentStage.setFullScreen(fs);
    	currentStage.show();


    }

    public static boolean[][] getVisibleCells() {
    	boolean[][] visible = new boolean[15][15];
    	
    	for (int i = 0; i < visible.length; i++) {
			for (int j = 0; j < visible[i].length; j++) {
				visible[i][j] = map[i][j].isVisible();
			}
		}
    	
    	return visible;
	              

	}
	
	
	
	public static void updateMap(boolean [][] visible) {
		
		for (int i = 0; i < visible.length; i++) {
			for (int j = 0; j < visible[i].length; j++) {
				if (visible[i][j])
					getRectangle(14 - i, j).setFill(Color.GREY);
			}
		}
		
	}
	
	private static Rectangle getRectangle(int row, int col) {
		for (javafx.scene.Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
				return (Rectangle) node;
		}
		return null;
	}
}

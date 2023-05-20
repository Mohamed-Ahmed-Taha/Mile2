package views;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.geometry.Pos;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Hero;
import model.world.Cell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.TranslateTransition;  
import javafx.util.Duration;

import engine.Game;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;



public class BoardView {
	
	private static final double screenX = Screen.getPrimary().getBounds().getMaxX();
	private static final double screenY = Screen.getPrimary().getBounds().getMaxY();
	
    private static final int GRID_SIZE = 15;
    private static final double CELL_SIZE = ((screenX/screenY)*(((((screenX*2)+(screenY*2)))*0.5))/100)+15;
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
    	h.setActionsAvailable(9999);
    	
    	
 
//    	h.move(Direction.UP);
    	
    	
    	Scene scene = new Scene(gridPane, 1500, 800);
    	currentStage.setTitle("15x15 grid");
    	currentStage.setScene(scene);
    	currentStage.setFullScreen(fs);
    	currentStage.show();
    	
    	BoardView.updateMap(getVisibleCells());
    	
    	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent move) {
        		if(move.getCode()== KeyCode.W) {
					try {
						h.move(Direction.UP);
					} catch (MovementException | NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		
        		if(move.getCode()== KeyCode.D) {
					try {
						h.move(Direction.RIGHT);
					} catch (MovementException | NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		
        		if(move.getCode()== KeyCode.A) {
					try {
						h.move(Direction.LEFT);
					} catch (MovementException | NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		
        		if(move.getCode()== KeyCode.S) {
					try {
						h.move(Direction.DOWN);
					} catch (MovementException | NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		Game.updateVisibility();
        		BoardView.updateMap(getVisibleCells());
        	}
        });

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
		
		Image joel = new Image("C:\\Users\\PC\\Documents\\GitHub\\Mile2\\Last of Us - Legacy\\src\\1212312453.jpeg");
		Image zomb = new Image("C:\\Users\\PC\\Documents\\GitHub\\Mile2\\Last of Us - Legacy\\src\\WhatsApp Image 2023-05-20 at 05.24.19.jpeg");
		
		for (int i = 0; i < visible.length; i++) {
			for (int j = 0; j < visible[i].length; j++) {
				if (visible[i][j]) {
					getRectangle(14 - i, j).setFill(Color.GREY);
					if(Game.checkHero(i, j))
						getRectangle(14 - i, j).setFill(new ImagePattern(joel));
					if(Game.checkZombie(i, j))
						getRectangle(14 - i, j).setFill(new ImagePattern(zomb));
				}
				else
					getRectangle(14 - i, j).setFill(Color.BLACK);
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

package views;
//import taha
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

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import java.lang.Math;

import controller.GameGridC;
import controller.MainMenuC;
import javafx.scene.layout.*;
import javafx.scene.image.*;

public class GameGridV {
	
	private static final double screenX = Screen.getPrimary().getBounds().getMaxX();
	private static final double screenY = Screen.getPrimary().getBounds().getMaxY();
	
	private static final int GRID_SIZE = 15;
	private static final double CELL_SIZE = ((screenX/screenY)*(Math.sqrt((((screenX*screenX)+(screenY*screenY)))))/100)+30;
	private static Cell[][] map;
	
	private static GridPane gridPane;
	
	public GameGridV(GameGridC controller, Stage stage, boolean fs, Hero h) {
		
		StackPane stack = new StackPane();
		
		Image empcell = new Image("/views/media/Empty Cell.jpeg");
		Image gridBg = new Image("/views/media/WhatsApp Image 2023-05-22 at 07.33.03.jpeg");
		ImageView bgView = new ImageView(gridBg);
		
		map = Game.map;
		gridPane = new GridPane();
	    for (int row = 0; row < GRID_SIZE; row++) {
	    	for (int col = 0; col < GRID_SIZE; col++) {
	    		Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
	            rectangle.setFill(null);
	            rectangle.setStroke(Color.WHITE);
	            rectangle.setStrokeType(StrokeType.INSIDE);

	    	    GridPane.setRowIndex(rectangle, row);
	            GridPane.setColumnIndex(rectangle, col);
	            gridPane.getChildren().add(rectangle);
	            gridPane.setAlignment(Pos.CENTER);
	        }
	    }
	    Game.startGame(h);
	    h.setActionsAvailable(9999);
	    
	    bgView.setFitHeight(Screen.getPrimary().getBounds().getMaxY());
	    bgView.setFitWidth(Screen.getPrimary().getBounds().getMaxX());

	    
	    stack.getChildren().add(bgView);
	    stack.getChildren().add(gridPane);
	    
	    
    	Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
    	stage.setTitle("15x15 grid");
    	stage.setScene(scene);
    	stage.setFullScreen(fs);
    	stage.show();
    	
    	updateMap(getVisibleCells());
    	
    	scene.setOnKeyPressed(controller);
 
	}	
	
	public static boolean[][] getVisibleCells(){
		boolean[][] visible = new boolean[15][15];
		for (int i = 0; i < visible.length; i++) {
			for (int j = 0; j < visible[i].length; j++) {
				visible[i][j] = map[i][j].isVisible();
			}
		}
		return visible;
		
	}
	
	public static void updateMap(boolean [][] visible) {

		Image joel = new Image("/views/media/Fighter/knight_f_idle_anim_f0.png");
		Image zomb = new Image("/views/media/Zombie/zombie_anim_f10.png");
		Image viscell = new Image("/views/media/0x72_DungeonTilesetII_v1.6.png");

		for (int i = 0; i < visible.length; i++) {
			for (int j = 0; j < visible[i].length; j++) {
				if (visible[i][j]) {
					getRectangle(14 - i, j).setFill(Color.GRAY);
					getRectangle(14 - i, j).setOpacity(0.5);
				if(Game.checkHero(i, j)) {
					getRectangle(14 - i, j).setFill(new ImagePattern(joel));
					getRectangle(14 - i, j).setOpacity(1);
				}
				if(Game.checkZombie(i, j)) {
					getRectangle(14 - i, j).setFill(new ImagePattern(zomb));
					getRectangle(14 - i, j).setOpacity(1);
				}
				}
			}
		}

	}
	
	public static void updateMapOnEndTurn(boolean [][] visible) {
	
		Image joel = new Image("/views/media/Fighter/knight_f_idle_anim_f0.png");
		Image zomb = new Image("/views/media/Zombie/zombie_anim_f10.png");
		Image viscell = new Image("/views/media/0x72_DungeonTilesetII_v1.6.png");
		Image empcell = new Image("/views/media/Empty Cell.jpeg");
		
			for (int i = 0; i < visible.length; i++) {
				for (int j = 0; j < visible[i].length; j++) {
					if (visible[i][j]) {
						getRectangle(14 - i, j).setFill(Color.GRAY);
						getRectangle(14 - i, j).setOpacity(0.5);
						if(Game.checkHero(i, j)) {
							getRectangle(14 - i, j).setFill(new ImagePattern(joel));
							getRectangle(14 - i, j).setOpacity(1);
						}
						if(Game.checkZombie(i, j)) {
							getRectangle(14 - i, j).setFill(new ImagePattern(zomb));
							getRectangle(14 - i, j).setOpacity(1);
						}
					}
					else
						getRectangle(14 - i, j).setFill(null);
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
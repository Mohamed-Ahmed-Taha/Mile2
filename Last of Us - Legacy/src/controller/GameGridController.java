package controller;

import engine.Game;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Hero;
import model.world.Cell;
import views.GameGridView;

public class GameGridController implements EventHandler<KeyEvent>{
	
	private Stage stage;		
	private GameGridView view;
	
	
	private static Cell[][] map;
	private Hero heroSelected;

	public GameGridController(Stage primaryStage) {
		stage = primaryStage;
		
		map = Game.map;
		heroSelected = SelectHeroController.getSelectedHero();
	    Game.startGame(heroSelected);
		
		view = new GameGridView(this, primaryStage);
		
		updateMap();
	}
	
	
//	@Override
//	public void handle(MouseEvent event) {
//		
//	}

	@Override
	public void handle(KeyEvent k) {
		if(k.getCode()== KeyCode.UP) {
			try {
				heroSelected.move(Direction.UP);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridView.updateMap(getVisibleCells());
		}
	
		if(k.getCode()== KeyCode.RIGHT) {
			try {
				heroSelected.move(Direction.RIGHT);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridView.updateMap(getVisibleCells());
		}
	
		if(k.getCode()== KeyCode.LEFT) {
			try {
				heroSelected.move(Direction.LEFT);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridView.updateMap(getVisibleCells());
		}
	
		if(k.getCode()== KeyCode.DOWN) {
			try {
				heroSelected.move(Direction.DOWN);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridView.updateMap(getVisibleCells());
		}
		
		if(k.getCode() == KeyCode.E) {
			GameGridView.updateMapOnEndTurn(getVisibleCells());
		}
		
		if(k.getCode() == KeyCode.ESCAPE) {
			stage.close();
		}

	}
	
	public static void updateMap() {
		
		

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

}

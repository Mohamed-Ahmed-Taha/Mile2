package controller;

import engine.Game;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Hero;
import views.GameGridV;

public class GameGridC implements EventHandler<KeyEvent>{
	
	public static Stage stage;		
	private static GameGridV view;
	private static Hero h;
	

	public GameGridC(Stage stage, Hero h) {
		view = new GameGridV(this, stage, stage.isFullScreen(), h);
	}
	
	public void handle(MouseEvent m) {
		
	}

	@Override
	public void handle(KeyEvent k) {
		if(k.getCode()== KeyCode.UP) {
			try {
				h.move(Direction.UP);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridV.updateMap(GameGridV.getVisibleCells());
		}
	
		if(k.getCode()== KeyCode.RIGHT) {
			try {
				h.move(Direction.RIGHT);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridV.updateMap(GameGridV.getVisibleCells());
		}
	
		if(k.getCode()== KeyCode.LEFT) {
			try {
				h.move(Direction.LEFT);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridV.updateMap(GameGridV.getVisibleCells());
		}
	
		if(k.getCode()== KeyCode.DOWN) {
			try {
				h.move(Direction.DOWN);
			} catch (MovementException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.updateVisibility();
			GameGridV.updateMap(GameGridV.getVisibleCells());
		}
		
		if(k.getCode() == KeyCode.E) {
			GameGridV.updateMapOnEndTurn(GameGridV.getVisibleCells());
		}
		
		if(k.getCode() == KeyCode.ESCAPE) {
			stage.close();
		}

	}

	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		GameGridC.stage = stage;
	}
	
	public static Hero getH() {
		return h;
	}
	
	public static void setH(Hero h) {
		GameGridC.h = h;
	}
}

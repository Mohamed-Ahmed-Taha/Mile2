package controller;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import views.GameGridView;

public class GameGridController implements EventHandler<Event>{
	
	private Stage stage;		
	private static GameGridView view;
	
	
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
	public void handle(Event event) {
		
		if (event instanceof KeyEvent) {
			KeyEvent keyEvent = (KeyEvent) event;
			
			switch (keyEvent.getCode()) {
			
			case UP:
				try {
					heroSelected.move(Direction.UP);
					updateMap();
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			case DOWN:
				try {
					heroSelected.move(Direction.DOWN);
					updateMap();
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
				
			case LEFT:
				try {
					heroSelected.move(Direction.LEFT);
					updateMap();
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
				
			case RIGHT:
				try {
					heroSelected.move(Direction.RIGHT);
					updateMap();
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
				
			case E:
				try {
					Game.endTurn();
					updateMap();
				} catch (InvalidTargetException | NotEnoughActionsException | NoAvailableResourcesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
				
			case ESCAPE:
				stage.close();
				
			default:
				
			
			}
			
			
		}
		
		
	}
	
	public static void updateMap() {
		
		char[][] mapForPrint = new char[15][15];
		// 'n' -> not visible
		// 'e' -> empty
		// 'x' -> explorer
		// 'f' -> fighter
		// 'm' -> medic
		// 'z' -> zombie
		// 's' -> supply
		// 'v' -> vaccine
		// 't' -> trap
		
		for (int i = 0; i < 15; i++) 
			for (int j = 0; j < 15; j++) 
				mapForPrint[i][j] = getCellType(map[i][j]);
		
		
		view.updateMap(mapForPrint);
		

	}
	
	private static char getCellType(Cell cell) {
		// TODO remove this comment
//		if (!cell.isVisible())
//			return 'n';
		
		if (cell instanceof CharacterCell) {
			if (((CharacterCell) cell).getCharacter() instanceof Explorer)
				return 'x';
			if (((CharacterCell) cell).getCharacter() instanceof Fighter)
				return 'f';
			if (((CharacterCell) cell).getCharacter() instanceof Medic)
				return 'm';
			if (((CharacterCell) cell).getCharacter() instanceof Zombie)
				return 'z';
		}
		if (cell instanceof CollectibleCell) {
			if (((CollectibleCell) cell).getCollectible() instanceof Supply)
				return 's';
			if (((CollectibleCell) cell).getCollectible() instanceof Vaccine)
				return 'v';
		}
		if (cell instanceof TrapCell)
			return 't';
		// cell is empty
		return 'e';
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

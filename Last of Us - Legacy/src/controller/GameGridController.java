package controller;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
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
import views.EndGameView;
import views.GameGridView;
import model.characters.Character;

public class GameGridController implements EventHandler<Event>{
	
	private Stage stage;		
	private static GameGridView view;
	
	
	private static Cell[][] map;
	private static Hero heroSelected;
	private Rectangle previousHeroRectangle;
	private Character targetSelected;
	private Rectangle previousTargetRectangle;
	private KeyCode currentAction;

	public GameGridController(Stage primaryStage, Hero  h) {
		stage = primaryStage;
		
		map = Game.map;
		heroSelected = h;
	    Game.startGame(heroSelected);
		
		view = new GameGridView(this, primaryStage);
		
		updateMapView();
	}
	
	

	public static Hero getHeroSelected() {
		return heroSelected;
	}



	@Override
	public void handle(Event event) {
		
		if (event instanceof KeyEvent) {
			getAction(((KeyEvent)event).getCode());
		}
		else if (event instanceof MouseEvent) {

			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				mouseClicked(event);
			}
		}
		
	}
	


	public void getAction(KeyCode keyCode) {
		
		switch (keyCode) {
		
		case UP:
			move(Direction.UP); break;
		case DOWN:
			move(Direction.DOWN); break;
		case LEFT:
			move(Direction.LEFT); break;			
		case RIGHT:
			move(Direction.RIGHT); break;
			
		case E:
			endTurn(); break;
			
		case A:
			attack(); break;
			
		case C:
			cure(); break;
			
		case S:
			useSpecial(); break; 
			
		case ESCAPE:
			stage.close();
			
		default: break;
		
		}
		
		view.updateCharacterBoxes();
		updateMapView();
		if (Game.checkGameOver())
			new EndGameView(stage, Game.checkWin());

	}
	
	
	private void mouseClicked(Event event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		int x = 14 - GridPane.getRowIndex(rectangle);
		int y = GridPane.getColumnIndex(rectangle);
		
		
		if (currentAction == null && Game.checkHero(x, y)) {
			heroSelected = (Hero) ((CharacterCell) map[x][y]).getCharacter();
			view.updateCharacterBoxes();
//			rectangle.setOpacity(0.5);
//			if (previousHeroRectangle != null) previousHeroRectangle.setOpacity(1);
//			previousHeroRectangle = rectangle;
		}
		else if (currentAction != null && map[x][y] instanceof CharacterCell) {
			targetSelected = ((CharacterCell) map[x][y]).getCharacter();
			getAction(currentAction);
//			rectangle.setOpacity(0.5);
//			if (previousTargetRectangle != null) previousTargetRectangle.setOpacity(1);
//			previousTargetRectangle = rectangle;
		}
		

	}
	
	private void move(Direction direction) {
		
		Point loc = heroSelected.getLocation();
		if (checkTrapCell(heroSelected.newCoord(loc.x, loc.y, direction)))
			view.playTrapAnimation();
		try {
			heroSelected.move(direction);
		} catch (MovementException | NotEnoughActionsException e) {
//			e.printStackTrace();
			view.printException(e);
		}
	}
	
	
	private void endTurn() {
		try {
			Game.endTurn();
		} catch (InvalidTargetException | NotEnoughActionsException | NoAvailableResourcesException e) {
//			e.printStackTrace();
			view.printException(e);
		}
	}
	
	
	private void attack() {
		
		if (currentAction == null) {
			currentAction = KeyCode.A;
			// TODO adjust attack animation in view
			return;
		}
		
		heroSelected.setTarget(targetSelected);
		try {
			heroSelected.attack();
		} catch (NotEnoughActionsException | InvalidTargetException e) {
//			e.printStackTrace();
			view.printException(e);
		}
		
		currentAction = null;
		
		

	}
	
	
	private void cure() {

		if (currentAction == null) {
			currentAction = KeyCode.C;
			// TODO adjust cure animation in view
			return;
		}
		
		heroSelected.setTarget(targetSelected);
		
		try {
			heroSelected.cure();
		} catch (InvalidTargetException | NoAvailableResourcesException | NotEnoughActionsException e) {
//			e.printStackTrace();
			view.printException(e);
		}
		
		currentAction = null;
		
	}
	
	
	private void useSpecial() {

		if (heroSelected instanceof Medic) {
			if (currentAction == null) {
				currentAction = KeyCode.S;
				// TODO adjust medic heal animation in view
				return;
			}
			else {
				heroSelected.setTarget(targetSelected);
			}
		}
		
		try {
			heroSelected.useSpecial();
		} catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e) {
//			e.printStackTrace();
			view.printException(e);
		}
		
		currentAction = null;
		
	}
	
	private boolean checkTrapCell(Point p) {
		if (Game.isEdge(p.x, p.y)) return false;
		return (map[p.x][p.y] instanceof TrapCell);
	}
	
	
	public static void updateMapView() {
		
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
		if (!cell.isVisible())
			return 'n';
		
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
	
	
	public static String getAttributes(Hero hero) {
		
		return "Name: " + hero.getName() +
				"\nType: " + SelectHeroController.getHeroType(hero) +
				"\nHP: " + hero.getCurrentHp() +
				"\nAttack Damage: " + hero.getAttackDmg() +
				"\nActions Available: " + hero.getActionsAvailable() +
				"\nSupplies: " + hero.getSupplyInventory().size() +
				"\nVaccine: " + hero.getVaccineInventory().size();
		
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

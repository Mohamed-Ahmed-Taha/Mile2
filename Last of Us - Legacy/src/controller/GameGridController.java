package controller;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
import views.GameGridView;
import model.characters.Character;

public class GameGridController implements EventHandler<Event>{
	
	private Stage stage;		
	private static GameGridView view;
	
	
	private static Cell[][] map;
	private Hero heroSelected;
	private Zombie targetZombie;
	private Hero targetHero;
	private Rectangle targetPrev;
//	private Rectangle previousTarget;

	public GameGridController(Stage primaryStage, Hero  h) {
		stage = primaryStage;
		
		map = Game.map;
		heroSelected = h;
	    Game.startGame(heroSelected);
		
		view = new GameGridView(this, primaryStage);
		
		updateMap();
	}
	
	

	@Override
	public void handle(Event event) {
		
		if (event instanceof KeyEvent) {
			keyPressed(event);
		}
		else if (event instanceof MouseEvent) {
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				mouseEntered(event);
			}
			else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				mouseExited(event);
			}
			else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				mouseClicked(event);
			}
		}
		
		if (Game.checkGameOver()) {
			if (Game.checkWin()) {}
				// new win screen
			else {}
				// new lose screen
		}
		
		
	}
	
	
	private void keyPressed(Event event) {
		KeyEvent keyEvent = (KeyEvent) event;
//		Rectangle rectangle = (Rectangle) event.getSource();
		
		switch (keyEvent.getCode()) {
		
		case UP:
			try {
				Point loc = heroSelected.getLocation();
				if (checkTrapCell(heroSelected.newCoord(loc.x, loc.y, Direction.UP)))
					view.playTrapAnimation();
				heroSelected.move(Direction.UP);
				updateMap();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break;
		case DOWN:
			try {
				Point loc = heroSelected.getLocation();
				if (checkTrapCell(heroSelected.newCoord(loc.x, loc.y, Direction.UP)))
					view.playTrapAnimation();
				heroSelected.move(Direction.DOWN);
				updateMap();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break;
			
		case LEFT:
			try {
				Point loc = heroSelected.getLocation();
				if (checkTrapCell(heroSelected.newCoord(loc.x, loc.y, Direction.UP)))
					view.playTrapAnimation();
				heroSelected.move(Direction.LEFT);
				updateMap();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break;
			
		case RIGHT:
			try {
				Point loc = heroSelected.getLocation();
				if (checkTrapCell(heroSelected.newCoord(loc.x, loc.y, Direction.UP)))
					view.playTrapAnimation();
				heroSelected.move(Direction.RIGHT);
				updateMap();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break;
			
		case E:
			try {
				Game.endTurn();
				updateMap();
			} catch (InvalidTargetException | NotEnoughActionsException | NoAvailableResourcesException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break;
			
		case A:
			try {
				heroSelected.setTarget(targetZombie);
//				getTarget();
				heroSelected.attack();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break;
			
		case C:
			try {
				heroSelected.setTarget(targetZombie);
				heroSelected.cure();
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (NoAvailableResourcesException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break;
			
		case S:
			try {
				if (heroSelected instanceof Medic)
					heroSelected.setTarget(heroSelected);
				heroSelected.useSpecial();
			} catch (NoAvailableResourcesException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				view.printException(e);

			} break; 
			
	
		case ESCAPE:
			stage.close();
			
		default:
			
		
		}
		
		
		
		updateMap();
	}
	
	private void mouseEntered(Event event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		int x = 14 - GridPane.getRowIndex(rectangle);
		int y = GridPane.getColumnIndex(rectangle);
		if (Game.checkHero(x, y)) {
			Hero hero = (Hero)((CharacterCell) map[x][y]).getCharacter();
			GameGridView.setAttributesPanel(getAttributes(hero));
			
		}
		else if (Game.checkZombie(x, y)) {
			if(map[x][y].isVisible()) {
				Zombie zombie = (Zombie)((CharacterCell) map[x][y]).getCharacter();
				GameGridView.setAttributesPanel("Zombie"+"\nHP: " + zombie.getCurrentHp()+"\nAttack Damage:" + zombie.getAttackDmg());
			}
		}
	}
	
	
	private void mouseExited(Event event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		int x = 14 - GridPane.getRowIndex(rectangle);
		int y = GridPane.getColumnIndex(rectangle);
		if (Game.checkHero(x, y)) {
			GameGridView.setAttributesPanel(null);			
		}
		else if (Game.checkZombie(x, y)) {
			GameGridView.setAttributesPanel(null);
		}
	}
	
	private void mouseClicked(Event event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		int x = 14 - GridPane.getRowIndex(rectangle);
		int y = GridPane.getColumnIndex(rectangle);
		rectangle.setOpacity(0.5);

		
		if (Game.checkHero(x, y) && (heroSelected == null || targetHero != null)) {
			heroSelected = (Hero) ((CharacterCell) map[x][y]).getCharacter();
			targetHero = null;
			targetZombie = null;
		}
		else if (heroSelected != null && Game.checkHero(x, y)) {
			targetHero = (Hero) ((CharacterCell) map[x][y]).getCharacter();
		}
		else if (heroSelected != null && Game.checkZombie(x, y)) {
			targetZombie = (Zombie) ((CharacterCell) map[x][y]).getCharacter();
		}
		
		
//		if (map[x][y] instanceof CharacterCell) {
//			if (!chooseTarget && Game.checkHero(x, y)) {
//				targetSelected = ((CharacterCell) map[x][y]).getCharacter();
////				chooseTarget = false;
//			}
//			else {
//				heroSelected = (Hero) ((CharacterCell) map[x][y]).getCharacter();
//				chooseTarget = true;
//			}
//		}
		
		
//		if (heroSelected == null && Game.checkHero(x, y)) {
//			heroSelected = (Hero) ((CharacterCell) map[x][y]).getCharacter();
//			targetSelected = null;
////			if (previousTarget != null) previousTarget.setOpacity(10);
//			rectangle.setOpacity(0.5);
//		}
//		else if (heroSelected != null && map[x][y] instanceof CharacterCell) {
//			targetSelected = ((CharacterCell) map[x][y]).getCharacter();
////			previousTarget.setOpacity(10);
//			rectangle.setOpacity(0.5);
//
//		}
//		previousTarget = rectangle;
		
		
	}
	
	
//	public void getTarget() {
//		
//		chooseTarget = !chooseTarget;
//		
//		
//	}
	
	
	private boolean checkTrapCell(Point p) {
		if (Game.isEdge(p.x, p.y)) return false;
		return (map[p.x][p.y] instanceof TrapCell);
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
//		 TODO remove this comment
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

package views.controller;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
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
import javafx.animation.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class GameGridController implements EventHandler<Event>{

	private static final long AI_TURN_DELAY = 1000;
	private Stage stage;
	private static GameGridView view;
	
	private MediaPlayer soundEffectPlayer;
	public static MediaPlayer mediaPlayer;
	private static Cell[][] map;
	private static Hero heroSelected;
	private static Character targetSelected;
	private KeyCode currentAction;
	private int hpBefore;
	private static int aiTurnDelay = 1000;
	public ArrayList<Hero> heroes = Game.heroes;


	public GameGridController(Stage primaryStage, Hero  h)  {
		stage = primaryStage;

		if(SelectHeroController.mediaPlayer != null)
			SelectHeroController.mediaPlayer.stop();




		String audioFile = "/views/media/cautious-path-01.mp3";
		Media media = new Media(getClass().getResource(audioFile).toExternalForm());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.6);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();





		map = Game.map;
		heroSelected = h;
		hpBefore = h.getCurrentHp();
	    Game.startGame(heroSelected);
		
		view = new GameGridView(this, primaryStage);
		
		updateMapView();
	}
	
	

	public static Hero getHeroSelected() {
		return heroSelected;
	}

	


	public static Character getTargetSelected() {
		return targetSelected;
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
		
		updateMapView();
		
	}
	private void enablePlayerInput(boolean enable) {
		if (enable) {
			GameGridView.getGridPane().setDisable(false);
		} else {
			GameGridView.getGridPane().setDisable(true);
		}
	}

	private void startAITurns() throws MovementException, NotEnoughActionsException {
		enablePlayerInput(false);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				for (Hero hero : heroes) {
					if (hero.getActionsAvailable() > 0) {
						Platform.runLater(() -> {
							try {
								performAIAction(hero);
							} catch (MovementException e) {
								throw new RuntimeException(e);
							} catch (NotEnoughActionsException e) {
								throw new RuntimeException(e);
							} catch (InvalidTargetException e) {
								throw new RuntimeException(e);
							} catch (NoAvailableResourcesException e) {
								throw new RuntimeException(e);
							}
						});
					}
				}
				Platform.runLater(() -> {
					GameGridView.updateCharacterBoxes();
					enablePlayerInput(true);
				});
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, AI_TURN_DELAY);
	}
	


	public void getAction(KeyCode keyCode) {
		if(keyCode.isArrowKey() && heroSelected.getActionsAvailable()>0){
			String soundEffectFile = "/views/media/move.mp3";
			Media soundEffectMedia = new Media(getClass().getResource(soundEffectFile).toExternalForm());
			soundEffectPlayer = new MediaPlayer(soundEffectMedia);
			soundEffectPlayer.setVolume(0.8);
			soundEffectPlayer.play();}

		switch (keyCode) {
		case ESCAPE:
			stage.close();
		
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
			useSpecial();
			if(heroSelected instanceof Medic  && targetSelected != null) {
				Point loc = targetSelected.getLocation();
				ScaleTransition rot = new ScaleTransition();
				rot.setNode(view.getRectangle(14-loc.x, loc.y));
				rot.setDuration(Duration.millis(100));
				rot.setCycleCount(2);
				rot.setInterpolator(Interpolator.LINEAR);
				rot.setByX(0.5);
				rot.setByY(0.5);
				rot.setByZ(0.5);
				rot.setAutoReverse(true);
				rot.play();
			} break;

		case F:
			System.out.println("F pressed");
			boolean allHeroesActionsUsedUp = true;
			for (Hero hero : heroes) {
				if (hero.getActionsAvailable() > 0) {
					allHeroesActionsUsedUp = false;
					break;
				}
			}

			if (!allHeroesActionsUsedUp) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						try {
							startAITurns();
						} catch (MovementException e) {
							throw new RuntimeException(e);
						} catch (NotEnoughActionsException e) {
							throw new RuntimeException(e);
						}
					}
				}, aiTurnDelay);
			} break;


			
		default: break;
		
		}
		
		view.updateCharacterBoxes();
		if (Game.checkGameOver())
			new EndGameView(stage, Game.checkWin());
		targetSelected = null;
		
		if(hpBefore > heroSelected.getCurrentHp()) {
			Point loc = heroSelected.getLocation();
			String audioFile = "/views/media/minecraft-hit-sound.mp3";
			Media media = new Media(getClass().getResource(audioFile).toExternalForm());
			MediaPlayer soundEffectPlayer = new MediaPlayer(media);
			soundEffectPlayer.play();
			RotateTransition rot = new RotateTransition();
			rot.setNode(view.getRectangle(14-loc.x, loc.y));
			rot.setDuration(Duration.millis(100));
			rot.setCycleCount(2);
			rot.setByAngle(22.5);
			rot.setAutoReverse(true);
			rot.play();
			
			hpBefore = heroSelected.getCurrentHp();
		}


	}
	
	
	private void mouseClicked(Event event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		int x = 14 - GridPane.getRowIndex(rectangle);
		int y = GridPane.getColumnIndex(rectangle);
		
		
		if (currentAction == null && Game.checkHero(x, y)) {
			heroSelected = (Hero) ((CharacterCell) map[x][y]).getCharacter();
			targetSelected = null;
			view.updateCharacterBoxes();
			rectangle.setStroke(Color.RED);
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
		if (checkTrapCell(heroSelected.newCoord(loc.x, loc.y, direction)) && heroSelected.getActionsAvailable() >0){
			String audioFile = "/views/media/Trapper's trap sound effect.mp3";
			Media media = new Media(getClass().getResource(audioFile).toExternalForm());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();}
			//view.trapAnimation(loc.x,loc.y); // fix this
		if(checkCollectibleCell(heroSelected.newCoord(loc.x, loc.y, direction)) && heroSelected.getActionsAvailable() >0){
			String audioFile = "/views/media/Pick up Sound effect.mp3";
			Media media = new Media(getClass().getResource(audioFile).toExternalForm());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
		}

		try {
			heroSelected.move(direction);
		} catch (MovementException | NotEnoughActionsException e) {
			view.printException(e);
		}
	}
	
	
	public static void endTurn() {
		try {

			Game.endTurn();
		} catch (InvalidTargetException | NotEnoughActionsException | NoAvailableResourcesException e) {
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
			String audioFile = "/views/media/minecraft-hit-sound.mp3";
			Media media = new Media(getClass().getResource(audioFile).toExternalForm());
			MediaPlayer soundEffectPlayer = new MediaPlayer(media);
			soundEffectPlayer.play();
			Point loc = targetSelected.getLocation();
			RotateTransition rot = new RotateTransition();
			rot.setNode(view.getRectangle(14-loc.x, loc.y));
			rot.setDuration(Duration.millis(100));
			rot.setCycleCount(2);
			rot.setByAngle(22.5);
			rot.setAutoReverse(true);
			rot.play();
//			Point loch = heroSelected.getLocation();
//			RotateTransition roth = new RotateTransition();
//			rot.setNode(view.getRectangle(14-loch.x, loch.y));
//			roth.setDuration(Duration.millis(100));
//			roth.setCycleCount(2);
//			roth.setByAngle(-22.5);
//			roth.setAutoReverse(true);
//			rot.play();
		} catch (NotEnoughActionsException | InvalidTargetException e) {
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
			String audioFile = "/views/media/Undertale Sound Effect - Heal.mp3";
			Media media = new Media(getClass().getResource(audioFile).toExternalForm());
			MediaPlayer soundEffectPlayer = new MediaPlayer(media);
			soundEffectPlayer.play();

			Point loc = targetSelected.getLocation();
			ScaleTransition rot = new ScaleTransition();
			rot.setNode(view.getRectangle(14-loc.x, loc.y));
			rot.setDuration(Duration.millis(100));
			rot.setCycleCount(2);
			rot.setInterpolator(Interpolator.LINEAR);
			rot.setByX(0.5);
			rot.setByY(0.5);
			rot.setByZ(0.5);
			rot.setAutoReverse(true);
			rot.play();
		} catch (InvalidTargetException | NoAvailableResourcesException | NotEnoughActionsException e) {
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
			view.printException(e);
		}
		
		currentAction = null;

	}
	
	private boolean checkTrapCell(Point p) {
		if (Game.isEdge(p.x, p.y)) return false;
		return (map[p.x][p.y] instanceof TrapCell);
	}

	private boolean checkCollectibleCell(Point p){
		if (Game.isEdge(p.x, p.y)) return false;
		return (map[p.x][p.y] instanceof CollectibleCell);
	}
	private boolean checkHeroCell(Point p){
		if (Game.isEdge(p.x, p.y)) return false;
		return (map[p.x][p.y] instanceof CharacterCell && ((CharacterCell) map[p.x][p.y]).getCharacter() instanceof Hero);
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

	private void performAIAction(Hero hero) throws MovementException, NotEnoughActionsException, InvalidTargetException, NoAvailableResourcesException {
		int actionsLeft = hero.getActionsAvailable();
		while (actionsLeft > 0) {
			Point loc = hero.getLocation();

			for (int i = 0; i < 4; i++) {
				Point p = hero.newCoord(loc.x, loc.y, indexToDirection(i));
				if (!Game.isEdge(p.x, p.y)){
				if(Game.getAdjZombie(loc) != null && !hero.getVaccineInventory().isEmpty() && hero.getActionsAvailable() > 0){
					Zombie z = (Zombie) Game.getAdjZombie(loc);
					hero.setTarget(z);
					hero.cure();
				}
				else
				if (checkCollectibleCell(p) ){
					move(indexToDirection(i));
					} else if (checkHeroCell(p) && hero instanceof Medic && !hero.getSupplyInventory().isEmpty() && hero.getActionsAvailable() > 0) {
							hero.setTarget(((CharacterCell) map[p.x][p.y]).getCharacter());
					hero.useSpecial();
				}
				else {
					Point q;
					for (int j = 0; j < 4; j++) {
						q = hero.newCoord(loc.x, loc.y, indexToDirection((j)));
						if (!Game.isEdge(q.x, q.y) && Game.map[q.x][q.y] instanceof CharacterCell && ((CharacterCell) Game.map[q.x][q.y]).getCharacter() == null && hero.getActionsAvailable() > 0){
							move(indexToDirection(j));
							 }
					}

				}
			}

			}

			if(hero.getActionsAvailable() == 0) break;

			actionsLeft--;

		}
		updateMapView();
	}

	public static Direction indexToDirection(int i){
		switch (i){
			case 0 : return Direction.UP;
			case 1 : return Direction.LEFT;
			case 2 : return Direction.RIGHT;
			case 3 : return Direction.DOWN;



		}
		return null;
	}

}

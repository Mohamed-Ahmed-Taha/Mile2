package controller;

import engine.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import views.BoardView;
import model.world.Cell;
import model.characters.*;

import java.io.File;
import java.io.FileNotFoundException;

public class Controller extends Application {
	
	private static Cell[][] map;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	map = Game.map;
       
    	BoardView.initMap(primaryStage);
    	try {
    		Game.loadHeroes("Last of Us - Legacy/src/Heros.csv");
    	}
    	catch(FileNotFoundException e) {
    		System.out.println("Heros.csv file is not found");
    		return;
    	}
    	Hero h = Game.availableHeroes.remove(0);
    	Game.startGame(h);
    	
    	
 
    	
    	BoardView.updateMap(getVisibleCells());
    	
    	h.move(Direction.UP);
    	


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

    public static void main(String[] args) {
		System.out.println(new File("Heros.csv").getAbsolutePath());
		launch(args);
    }
}

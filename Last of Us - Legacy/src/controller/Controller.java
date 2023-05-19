package controller;

import engine.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import views.BoardView;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.input.*;
import model.world.Cell;
import model.characters.*;

public class Controller extends Application {
	
	private static Cell[][] map;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	map = Game.map;
       
    	BoardView.initMap(primaryStage);
    	try {
    		Game.loadHeroes("D:\\Work\\College\\GUC\\S Semester 4\\Programming Lab - CSEN 401\\Game Project\\Milestone 2\\Repo 2\\Mile2\\Last of Us - Legacy\\src\\engine\\Heros.csv");
    	}
    	catch(Exception e) {
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
        launch(args);
    }
}

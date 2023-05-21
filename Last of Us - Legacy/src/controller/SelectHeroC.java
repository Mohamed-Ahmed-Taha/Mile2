package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.characters.Hero;
import model.world.Cell;
import views.SelectHeroV;
import controller.GameGridC;

import java.util.ArrayList;

import engine.Game;

public class SelectHeroC implements EventHandler<MouseEvent>{
	
	public static Stage stage;	
	private static ArrayList<Hero> availableHeroes;
	private static Hero heroPicked;
	private static SelectHeroV view;
	
	public SelectHeroC(Stage stage, boolean fs) {
		availableHeroes = Game.availableHeroes;
		view = new SelectHeroV(this, stage, fs);
		heroPicked = null;
	}

	@Override
	public void handle(MouseEvent event) {
		
		String name = ((Button) (event.getSource())).getText();
				
		switch (name){
		
			case "Joel":
				heroPicked = availableHeroes.get(0); 
				GameGridC gameGrid = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage);
				GameGridC.setH(heroPicked); break;
		
			case "Ellie":
				heroPicked = availableHeroes.get(1); 
				GameGridC gameGrid1 = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage); 
				GameGridC.setH(heroPicked); break;
		
			case "Tess":
				heroPicked = availableHeroes.get(2);
				GameGridC gameGrid2 = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage); 
				GameGridC.setH(heroPicked); break;
		
			case "Riley": 
				heroPicked = availableHeroes.get(3);
				GameGridC gameGrid3 = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage); 
				GameGridC.setH(heroPicked); break;
		
			case "Tommy":
				heroPicked = availableHeroes.get(4);
				GameGridC gameGrid4 = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage); 
				GameGridC.setH(heroPicked); break;
		
			case "Bill":
				heroPicked = availableHeroes.get(5);
				GameGridC gameGrid5 = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage); 
				GameGridC.setH(heroPicked); break;
		
			case "David":
				heroPicked = availableHeroes.get(6);
				GameGridC gameGrid6 = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage); 
				GameGridC.setH(heroPicked); break;

			case "Henry":
				heroPicked = availableHeroes.get(7);
				GameGridC gameGrid7 = new GameGridC(stage, heroPicked);
				GameGridC.setStage(stage); 
				GameGridC.setH(heroPicked); break;
				
			case "Back":
				try {
					MainMenuC mainMenu = new MainMenuC(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainMenuC.setStage(stage);
			
		}
		
		Game.startGame(heroPicked);
		// open gridView
		
	}
	
		public static Stage getStage() {
		return stage;
	}


	public static void setStage(Stage stage) {
		SelectHeroC.stage = stage;
	}

	public static Hero getHeroPicked() {
		return heroPicked;
	}
	
	
}

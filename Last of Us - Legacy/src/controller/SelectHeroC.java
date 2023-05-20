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

import java.util.ArrayList;

import engine.Game;

public class SelectHeroC implements EventHandler<MouseEvent>{
	
	private static ArrayList<Hero> availableHeroes;
	private static Hero heroPicked;
	private static SelectHeroV view;
	
	public SelectHeroC(Stage primaryStage) {
		availableHeroes = Game.availableHeroes;
		view = new SelectHeroV(this, primaryStage);
		heroPicked = null;
	}


	@Override
	public void handle(MouseEvent event) {
		
		String name = ((Button) (event.getSource())).getText();
				
		switch (name){
		
			case "Joel":
				heroPicked = availableHeroes.get(0); break;
		
			case "Ellie":
				heroPicked = availableHeroes.get(1); break;
		
			case "Tess":
				heroPicked = availableHeroes.get(2); break;
		
			case "Riley": 
				heroPicked = availableHeroes.get(3); break;
		
			case "Tommy":
				heroPicked = availableHeroes.get(4); break;
		
			case "Bill":
				heroPicked = availableHeroes.get(5); break;
		
			case "David":
				heroPicked = availableHeroes.get(6); break;

			case "Henry":
				heroPicked = availableHeroes.get(7); break;
			
		}
		
		Game.startGame(heroPicked);
		// open gridView
		
	}
	
	


	public static Hero getHeroPicked() {
		return heroPicked;
	}
	
	
}

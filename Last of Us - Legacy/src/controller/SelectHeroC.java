package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.characters.Hero;
import views.SelectHeroV;
import engine.Game;

public class SelectHeroC implements EventHandler<MouseEvent>{
	
	private String label;
	
	public SelectHeroC(String label) {
		label=this.label;
	}

//	public static EventHandler<MouseEvent> select = new EventHandler<MouseEvent>() {
//		public void handle(MouseEvent m) {
//			Game.availableHeroes.get(0);
//			
//		}
//	};

	@Override
	public void handle(MouseEvent arg0) {
		
		if(label.equals("Joel")) {
			Hero h = Game.availableHeroes.get(0);
			Game.startGame(h);
		}
		
		if(label.equals("Ellie")) {
			Hero h = Game.availableHeroes.get(1);
			Game.startGame(h);
		}
		
		if(label.equals("Tess")) {
			Hero h = Game.availableHeroes.get(2);
			Game.startGame(h);
		}
		
		if(label.equals("Riley")) {
			Hero h = Game.availableHeroes.get(3);
			Game.startGame(h);
		}
		
		if(label.equals("Tommy")) {
			Hero h = Game.availableHeroes.get(4);
			Game.startGame(h);
		}
		
		if(label.equals("Bill")) {
			Hero h = Game.availableHeroes.get(5);
			Game.startGame(h);
		}
		
		if(label.equals("David")) {
			Hero h = Game.availableHeroes.get(6);
			Game.startGame(h);
		}
		
		if(label.equals("Henry")) {
			Hero h = Game.availableHeroes.get(7);
			Game.startGame(h);
		}

		
	}
	
	
}

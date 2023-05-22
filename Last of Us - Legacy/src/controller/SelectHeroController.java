package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import views.SelectHeroView;
import java.util.ArrayList;
import engine.Game;

public class SelectHeroController implements EventHandler<MouseEvent>{
	
	private Stage stage;	
	private SelectHeroView view;
	
	private static ArrayList<Hero> availableHeroes;
	private static Hero selectedHero;
	
	
	public SelectHeroController(Stage primaryStage) {
		stage = primaryStage;
		availableHeroes = Game.availableHeroes;
		view = new SelectHeroView(this, stage);
	}


	
	public static Hero getSelectedHero() {
		return selectedHero;
	}



	@Override
	public void handle(MouseEvent event) {
				
		if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
		
			Button heroButton = ((Button) event.getSource());
			Hero hero = getHeroFromAvailableHeroes(heroButton);
			
			if (heroButton.getText().equals("Back")) {
				try {
					new MainMenuController(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
			selectedHero = hero;
			new GameGridController(stage);
						
		}
		
		else if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			Button hoverButton = (Button) (event.getSource());
			view.hoverIn(hoverButton);
			
		}
		
		else if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
			Button hoverButton = (Button) (event.getSource());
			view.hoverOut(hoverButton);
						
		}
		
	}
	
	
	private static String getHeroType(Hero hero) {
		if (hero instanceof Fighter)
			return "Fighter";
		if (hero instanceof Medic)
			return "Medic";
		if (hero instanceof Explorer)
			return "Explorer";
		return "";
	}
	
	
	public static String getStartAttributes(Button heroButton) {
		
		Hero hero = getHeroFromAvailableHeroes(heroButton);
		
		return "Name: " + hero.getName() +
				"\nType: " + getHeroType(hero) +
				"\nMax HP: " + hero.getMaxHp() +
				"\nAttack Damage: " + hero.getAttackDmg() +
				"\nMax Actions Available: " + hero.getMaxActions();
		
	}
	
	
	private static Hero getHeroFromAvailableHeroes(Button button) {
		
		switch (button.getText()){
	
		case "Joel":
			return availableHeroes.get(0); 	
		case "Ellie":
			return availableHeroes.get(1); 			
		case "Tess":
			return availableHeroes.get(2); 
		case "Riley": 
			return availableHeroes.get(3); 		
		case "Tommy":
			return availableHeroes.get(4); 		
		case "Bill":
			return availableHeroes.get(5); 		
		case "David":
			return availableHeroes.get(6); 
		case "Henry":
			return availableHeroes.get(7); 		
		default:
			return null;
		}
		

		
	}
	
}

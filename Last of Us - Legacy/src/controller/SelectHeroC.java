package controller;

import javafx.event.Event;
import javafx.scene.control.Tooltip;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Hero;
import model.world.Cell;
import views.SelectHeroV;
import controller.GameGridC;

import java.util.ArrayList;

import javax.swing.ToolTipManager;

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
	public void handle(MouseEvent c) {
		
		Tooltip hinfo = new Tooltip();
		
		if(c.getEventType() == MouseEvent.MOUSE_CLICKED) {
		
		String name = ((Button) (c.getSource())).getText();
				
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
		}
		
		if(c.getEventType() == MouseEvent.MOUSE_ENTERED) {
			Button hover = (Button) (c.getSource());
			hover.setTextFill(Color.LIGHTGRAY);
			Font ac = new Font("Agency FB", 30);

			
			switch (hover.getText()){
			
			case "Joel":
				hinfo.setText("Max HP: 140 \n Max Actions: 5 \n Attack Damage: 30 \n Type: Fighter");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo); break;
		
			case "Ellie":
				hinfo.setText("Max HP: 110 \n Max Actions: 6 \n Attack Damage: 15 \n Type: Medic");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo); break;
		
			case "Tess":
				hinfo.setText("Max HP: 80 \n Max Actions: 6 \n Attack Damage: 20 \n Type: Explorer");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo); break;
		
			case "Riley": 
				hinfo.setText("Max HP: 90 \n Max Actions: 5 \n Attack Damage: 25 \n Type: Explorer");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo); break;
		
			case "Tommy":
				hinfo.setText("Max HP: 95 \n Max Actions: 5 \n Attack Damage: 25 \n Type: Explorer");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo); break;
		
			case "Bill":
				hinfo.setText("Max HP: 100 \n Max Actions: 7 \n Attack Damage: 10 \n Type: Medic");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo); break;
		
			case "David":
				hinfo.setText("Max HP: 150 \n Max Actions: 4 \n Attack Damage: 35 \n Type: Fighter");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo); break;

			case "Henry":
				hinfo.setText("Max HP: 105 \n Max Actions: 6 \n Attack Damage: 15 \n Type: Medic");
				hinfo.setFont(ac);
				hinfo.setPrefSize(250, 200);
				hinfo.setShowDelay(Duration.ZERO);
				hinfo.setShowDuration(Duration.INDEFINITE);
				hinfo.setHideDelay(Duration.ZERO);
				hover.setTooltip(hinfo);
				
			}

			
		}
		
		if(c.getEventType() == MouseEvent.MOUSE_EXITED) {
			Button hover = (Button) (c.getSource());
			hover.setTextFill(Color.WHITE);
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

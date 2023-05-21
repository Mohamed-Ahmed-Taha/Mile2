package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import views.MainMenuV;

import javax.swing.event.HyperlinkEvent.EventType;

import controller.SelectHeroC;
import javafx.scene.control.*;

public class MainMenuC implements EventHandler<MouseEvent>{
	
	public static Stage stage;		
	private static MainMenuV view;
	
	public MainMenuC(Stage stage) throws Exception {
		view = new MainMenuV(this, stage, stage.isFullScreen());
		
	}

	public void handle(MouseEvent c) {
		
		if(c.getEventType() == MouseEvent.MOUSE_CLICKED) {
		
		String click = ((Button) (c.getSource())).getText();
		
		
		if(click.equals("Start Game")) {
			SelectHeroC selectHero = new SelectHeroC(stage, stage.isFullScreen());
			SelectHeroC.setStage(stage);
		}
			
			
		
		if(click.equals("Options")) {
			OptionsMenuC optionsMenu = new OptionsMenuC(stage, stage.isFullScreen());
			OptionsMenuC.setStage(stage);
		}
			
		
		if(click.equals("Exit"))
			stage.close();
		}
		
		if(c.getEventType() == MouseEvent.MOUSE_ENTERED) {
			Button hover = (Button) (c.getSource());
			hover.setTextFill(Color.LIGHTGRAY);
		}
		
		if(c.getEventType() == MouseEvent.MOUSE_EXITED) {
			Button hover = (Button) (c.getSource());
			hover.setTextFill(Color.WHITE);
		}

			
	}

	public static Stage getStage() {
		return stage;
	}


	public static void setStage(Stage stage) {
		MainMenuC.stage = stage;
	}

}

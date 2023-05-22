package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.MainMenuView;
import javafx.scene.control.*;

public class MainMenuController implements EventHandler<MouseEvent>{
	
	private Stage stage;		
	private MainMenuView view;
	
	public MainMenuController(Stage primaryStage) throws Exception {
		stage = primaryStage;
		view = new MainMenuView(this, primaryStage);
		
	}

	public void handle(MouseEvent event) {
		
		if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
		
			String click = ((Button) (event.getSource())).getText();
		
			
			switch (click) {
			
			case "Start Game":
				new SelectHeroController(stage); break;
			case "Options":
				new OptionsMenuController(stage); break;
			case "Exit":
				stage.close();
				
			}
		}
		
		if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			Button hoverButton = (Button) (event.getSource());
			view.hoverIn(hoverButton);
		}
		
		if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
			Button hoverButton = (Button) (event.getSource());
			view.hoverOut(hoverButton);
		}

			
	}

}

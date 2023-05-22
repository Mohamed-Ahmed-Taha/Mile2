package controller;

import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.OptionsMenuView;

public class OptionsMenuController implements EventHandler<MouseEvent>{
	
	private Stage stage;
	private OptionsMenuView view;
	
	public OptionsMenuController(Stage primaryStage) {
		stage = primaryStage;
		view = new OptionsMenuView(this, primaryStage);
		
	}
	
	
	@Override
	public void handle(MouseEvent event) {
		
		if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			String buttonBase = ((ButtonBase) (event.getSource())).getText();				
			switch (buttonBase) {
	
			case "Fullscreen":
				stage.setFullScreen(!stage.isFullScreen()); break;
			case "Sound":
				break;
			case "Back":
				try {
					new MainMenuController(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			}
			
		}
			
		if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			ButtonBase hoverButton = (ButtonBase) event.getSource();
			view.hoverIn(hoverButton);
		}
		
		if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
			ButtonBase hoverButton = (ButtonBase) event.getSource();
			view.hoverOut(hoverButton);
		}
		
	}

}

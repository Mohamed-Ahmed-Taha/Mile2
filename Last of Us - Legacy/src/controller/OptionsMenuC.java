package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import views.MainMenuV;
import views.OptionsMenuV;

public class OptionsMenuC implements EventHandler<MouseEvent>{
	
	public static Stage stage;
	private static OptionsMenuV view;
	
	public OptionsMenuC(Stage stage, boolean fs) {
		view = new OptionsMenuV(this, stage, fs);
		
	}
	@Override
	public void handle(MouseEvent c) {
		
		if(c.getEventType() == MouseEvent.MOUSE_CLICKED) {
		if((c.getSource()) instanceof CheckBox) {
			String check = ((CheckBox) (c.getSource())).getText();
			
			if(check.equals("Fullscreen"))
				if(stage.isFullScreen()) {
					stage.setFullScreen(false);				}
				else {
					stage.setFullScreen(true);
				}
			
			if(check.equals("Sound")) {

				}
			
		}
		
		if((c.getSource()) instanceof Button) {
			String click = ((Button) (c.getSource())).getText();
			if(click.equals("Back")) {
				try {
					MainMenuC mainMenu = new MainMenuC(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainMenuC.setStage(stage);
			}
		}
		}
			
		if(c.getEventType() == MouseEvent.MOUSE_ENTERED) {
		if((c.getSource()) instanceof Button) {
			Button hover = (Button) (c.getSource());
			hover.setTextFill(Color.LIGHTGRAY);
		}
		
		if((c.getSource()) instanceof CheckBox) {
			CheckBox hover = (CheckBox) (c.getSource());
			hover.setTextFill(Color.LIGHTGRAY);
		}
		
		}
		
		if(c.getEventType() == MouseEvent.MOUSE_EXITED) {
			if((c.getSource()) instanceof Button) {
				Button hover = (Button) (c.getSource());
				hover.setTextFill(Color.WHITE);
			}
			
			if((c.getSource()) instanceof CheckBox) {
				CheckBox hover = (CheckBox) (c.getSource());
				hover.setTextFill(Color.WHITE);
			}
			
		}

		
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		OptionsMenuC.stage = stage;
	}

}

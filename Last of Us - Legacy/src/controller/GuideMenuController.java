package controller;

import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.GuideMenuView;

public class GuideMenuController implements EventHandler<MouseEvent>{
	
	private Stage stage;
	private GuideMenuView view;
	
	public GuideMenuController(Stage primaryStage) {
		stage = primaryStage;
		view = new GuideMenuView(this, primaryStage);
		
	}
	
	
	@Override
	public void handle(MouseEvent event) {
		
		try {
			new MainMenuController(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

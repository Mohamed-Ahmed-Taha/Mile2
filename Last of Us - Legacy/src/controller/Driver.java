package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.SelectHeroV;

public class Driver extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		SelectHeroV selectHero = new SelectHeroV(primaryStage);

		
	}
	
	public static void main(String[] args) {
		launch();
	}
	


}

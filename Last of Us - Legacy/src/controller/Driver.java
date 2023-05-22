package controller;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import engine.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {
	
	@Override
	public void init() {
		
		try {
			File file = new File(getClass().getResource("/views/media/Heros.csv").toURI());
			Game.loadHeroes(file.toString());
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		new MainMenuController(primaryStage);
//		primaryStage.setFullScreen(true);
//		MainMenuC mainMenu = new MainMenuC(primaryStage);
//		MainMenuC.setStage(primaryStage);
		
	}
	
	public static void main(String[] args) {
			launch();
	}
	
	
	


}

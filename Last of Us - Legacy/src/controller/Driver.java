package controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import engine.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import views.MainMenuV;

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
		
		primaryStage.setFullScreen(true);
		MainMenuC mainMenu = new MainMenuC(primaryStage);
		MainMenuC.setStage(primaryStage);
		
	}
	
	public static void main(String[] args) {
			launch();
	}
	
	
	


}

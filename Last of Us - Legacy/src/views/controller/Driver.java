package views.controller;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import engine.Game;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Driver extends Application {
	private static final String AUDIO_FILE = "/views/media/The Last Of Us - Theme Song.mp3";
	public static MediaPlayer mediaPlayer;

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
		Media media = new Media(getClass().getResource(AUDIO_FILE).toExternalForm());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.5);
		mediaPlayer.play();

		new MainMenuController(primaryStage);
		primaryStage.setOnCloseRequest(event -> mediaPlayer.stop());

//		primaryStage.setFullScreen(true);
//		MainMenuC mainMenu = new MainMenuC(primaryStage);
//		MainMenuC.setStage(primaryStage);
		
	}
	
	public static void main(String[] args) {
			launch();
	}
	
	
	


}

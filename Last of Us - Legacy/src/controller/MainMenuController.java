package controller;

import engine.Game;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import views.MainMenuView;
import javafx.scene.control.*;


public class MainMenuController implements EventHandler<MouseEvent>{

	private Stage stage;		
	private MainMenuView view;
	private MediaPlayer soundEffectPlayer;


	public MainMenuController(Stage primaryStage) throws Exception {
		stage = primaryStage;
		view = new MainMenuView(this, primaryStage);
		
	}

	public void handle(MouseEvent event) {
		
		if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {



			String click = ((Button) (event.getSource())).getText();
			String soundEffectFile = "/views/media/button-09a.mp3";
			Media soundEffectMedia = new Media(getClass().getResource(soundEffectFile).toExternalForm());
			soundEffectPlayer = new MediaPlayer(soundEffectMedia);
			
			switch (click) {
			
			case "Start Game":
				SelectHeroController controller = new SelectHeroController(stage, Game.availableHeroes);
				if (Driver.mediaPlayer != null) {
					Driver.mediaPlayer.stop();
				}
				soundEffectPlayer.play();
				controller.initialize();
				break;
			case "Guide":
				soundEffectPlayer.play();
				new GuideMenuController(stage); break;
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

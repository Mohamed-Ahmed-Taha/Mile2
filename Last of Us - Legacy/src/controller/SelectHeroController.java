package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import views.SelectHeroView;
import java.util.ArrayList;
import engine.Game;

public class SelectHeroController {
    public static MediaPlayer mediaPlayer;
    private Stage stage;
    private ArrayList<Hero> availableHeroes;

    public SelectHeroController(Stage stage, ArrayList<Hero> availableHeroes) {
        this.stage = stage;
        this.availableHeroes = availableHeroes;
    }

    public void initialize() {
        SelectHeroView view = new SelectHeroView(stage, availableHeroes, this);
        view.createUI();

        String audioFile = "/views/media/jim-walter-choose-your-character-(1).mp3";
        Media media = new Media(getClass().getResource(audioFile).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();

    }

    public void onCharacterSelected(Hero h)  {
        String soundEffectFile = "/views/media/button-09a.mp3";
        Media soundEffectMedia = new Media(getClass().getResource(soundEffectFile).toExternalForm());
        MediaPlayer soundEffectPlayer = new MediaPlayer(soundEffectMedia);
        soundEffectPlayer.play();
        new GameGridController(stage, h);
    }
    
    public static String getHeroType(Hero h) {
    	if(h instanceof Fighter) {
    		return "Fighter";
    	}
    	if(h instanceof Medic) {
    		return "Medic";
    	}
    	else {
    		return "Explorer";
    	}
    }
}

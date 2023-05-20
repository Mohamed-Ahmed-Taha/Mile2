package controller;

import engine.Game;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import views.BoardView;
import model.world.Cell;
import model.characters.*;
import views.Mainmenu;

import java.io.File;
import java.io.FileNotFoundException;

public class Controller extends Application {
	
	

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
//		Media sound = new Media(new File("C:\\Users\\PC\\Documents\\GitHub\\Mile2\\Last of Us - Legacy\\src\\gamemusic.mp3").toURI().toString());
//        MediaPlayer mediaplayer = new MediaPlayer(sound);
//        mediaplayer.play();
       
    	Mainmenu.start(primaryStage, true);
    	
    }

    public static void main(String[] args) {
		System.out.println(new File("Heros.csv").getAbsolutePath());
		launch(args);
    }
}

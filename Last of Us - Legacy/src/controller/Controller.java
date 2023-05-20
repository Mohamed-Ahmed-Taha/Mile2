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
       
    	Mainmenu.start(primaryStage, true);
    	
    }

    public static void main(String[] args) {
		System.out.println(new File("Heros.csv").getAbsolutePath());
		launch(args);
    }
}

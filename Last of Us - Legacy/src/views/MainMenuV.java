package views;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.input.KeyEvent;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.IOException;

import java.io.File;
import controller.MainMenuC;
import controller.SelectHeroC;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.scene.input.*;


public class MainMenuV {
	
	public MainMenuV(MainMenuC controller, Stage stage, boolean fs) throws Exception{
		
		Font ac = new Font("Agency FB", 12);
		
		VBox t = new VBox();
		Image titlepic = new Image("views/media/Screenshot 2023-05-19 205105.png");
		ImageView picview = new ImageView(titlepic);
		picview.setFitWidth(200);
		picview.setFitHeight(100);
		Button start = new Button("Start Game");
		Button options = new Button("Options");
		Button exit = new Button("Exit");
		
		start.setFont(ac);
		options.setFont(ac);
		exit.setFont(ac);
		
		start.setBackground(null);
		options.setBackground(null);
		exit.setBackground(null);
		
		start.setTextFill(Color.WHITE);
		options.setTextFill(Color.WHITE);
		exit.setTextFill(Color.WHITE);
	
		t.setScaleX(4);
		t.setScaleY(4);
		t.setSpacing(5);
		t.getChildren().add(picview);
		t.getChildren().add(start);
		t.getChildren().add(options);
		t.getChildren().add(exit);
		t.setAlignment(Pos.CENTER);
		t.setStyle("-fx-background-color: #000000;");
		
		Scene scene = new Scene(t, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
		stage.setTitle("main menu");
	  	stage.setScene(scene);
//		Media sound = new Media("C:\\Users\\Omar Abulsorour\\Documents\\Mile2\\Last of Us - Legacy\\src\\gamemusic2.mp3");
//		MediaPlayer mediaplayer = new MediaPlayer(sound);
//		mediaplayer.setAutoPlay(true);
		if(fs)
			stage.setFullScreen(true);
		else
			stage.setFullScreen(false);
		stage.show();
		
		start.setOnMouseClicked(controller);
		options.setOnMouseClicked(controller);
		exit.setOnMouseClicked(controller);
	}

}

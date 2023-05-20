package views;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.input.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.event.*;
import javafx.scene.input.*;
import views.Optionsmenu;


public class Mainmenu {	
	
	public static void start(Stage stage, boolean fs) throws Exception {
		
		VBox t = new VBox();
		Image titlepic = new Image("C:\\Users\\PC\\Documents\\GitHub\\Mile2\\Last of Us - Legacy\\src\\Screenshot 2023-05-19 205105.png");
		ImageView picview = new ImageView(titlepic);
		picview.setFitWidth(200);
		picview.setFitHeight(100);
		Button start = new Button("Start Game");
		Button options = new Button("Options");
		Button exit = new Button("Exit");

		
		t.setScaleX(5);
		t.setScaleY(5);
		t.setSpacing(5);
		t.getChildren().add(picview);
		t.getChildren().add(start);
		t.getChildren().add(options);
		t.getChildren().add(exit);
		t.setAlignment(Pos.CENTER);
		t.setStyle("-fx-background-color: #000000;");
		
		
		EventHandler<MouseEvent> pressop = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent p ) {
				try {
					Optionsmenu.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		EventHandler<MouseEvent> pressext = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent p ) {
				stage.close();
			}
		};	
		
		EventHandler<MouseEvent> pressstr = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent p ) {
				try {
					BoardView.initMap(stage, fs);
				} catch (MovementException | NotEnoughActionsException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		
		Scene scene = new Scene(t, 1500, 800);
        stage.setTitle("main menu");
        stage.setScene(scene);
		Media sound = new Media(new File("C:\\Users\\PC\\Documents\\GitHub\\Mile2\\Last of Us - Legacy\\src\\gamemusic2.mp3").toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(sound);
        mediaplayer.setAutoPlay(true);
        if(fs)
        	stage.setFullScreen(true);
        else
        	stage.setFullScreen(false);
        stage.show();
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent esc) {
        		if(esc.getCode()== KeyCode.ESCAPE )
        			stage.close();
        	}
        });
        start.addEventFilter(MouseEvent.MOUSE_CLICKED, pressstr);
        options.addEventFilter(MouseEvent.MOUSE_CLICKED, pressop);
        exit.addEventFilter(MouseEvent.MOUSE_CLICKED, pressext);
		
	}
	
}

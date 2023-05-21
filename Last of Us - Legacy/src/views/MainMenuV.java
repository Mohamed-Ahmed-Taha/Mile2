package views;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.input.KeyEvent;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import controller.MainMenuC;
import controller.SelectHeroC;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.scene.image.*;
import javafx.animation.FadeTransition;
import javafx.event.*;
import javafx.scene.input.*;


public class MainMenuV {
	
	
	public MainMenuV(MainMenuC controller, Stage stage, boolean fs) throws Exception{
		
		Font ac = new Font("Agency FB", 12);
		
		StackPane stack = new StackPane();
		
		
		
		
		VBox t = new VBox();
		Image titlepic = new Image("/views/media/WhatsApp_Image_2023-05-21_at_08.56.27-removebg-preview.png");
		Image titlebg = new Image("/views/media/john-sweeney-main-menu.jpg");
		ImageView picview = new ImageView(titlepic);
		ImageView bgview = new ImageView(titlebg);
		picview.setFitWidth(200);
		picview.setFitHeight(100);
		Button start = new Button("Start Game");
		Button options = new Button("Options");
		Button exit = new Button("Exit");
		
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(3000));
		fade.setFromValue(0);
		fade.setToValue(10);
		fade.setNode(t);
		fade.play();
		
		start.setFont(ac);
		options.setFont(ac);
		exit.setFont(ac);
		
		start.setBackground(null);
		options.setBackground(null);
		exit.setBackground(null);
		
		start.setTextFill(Color.WHITE);
		options.setTextFill(Color.WHITE);
		exit.setTextFill(Color.WHITE);
	
		picview.setScaleX(0.5);
		picview.setScaleY(0.5);
		t.setScaleX(4);
		t.setScaleY(4);
		t.setSpacing(4);
		t.getChildren().add(picview);
		t.getChildren().add(start);
		t.getChildren().add(options);
		t.getChildren().add(exit);
		t.setAlignment(Pos.CENTER);
		t.setBackground(null);
		
		stack.getChildren().add(bgview);		
		stack.getChildren().add(t);
		
		Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
		stage.setTitle("main menu");
	  	stage.setScene(scene);
	  	if(fs)
			stage.setFullScreen(true);
		else
			stage.setFullScreen(false);
		stage.show();

		
		start.setOnMouseClicked(controller);
		options.setOnMouseClicked(controller);
		exit.setOnMouseClicked(controller);
		
		start.setOnMouseEntered(controller);
		options.setOnMouseEntered(controller);
		exit.setOnMouseEntered(controller);
		
		start.setOnMouseExited(controller);
		options.setOnMouseExited(controller);
		exit.setOnMouseExited(controller);
	}

}

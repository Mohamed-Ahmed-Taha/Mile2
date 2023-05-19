package views;

import javafx.application.Application;
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
import java.io.FileInputStream; 
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.event.*;
import javafx.scene.input.*;
import views.Optionsmenu;

public class Mainmenu extends Application {
	
	public void start(Stage stage) throws Exception {
		VBox t = new VBox();
		FileInputStream titlepic = new FileInputStream("C:\\Users\\PC\\Pictures\\Screenshots\\Screenshot 2023-05-19 205105.png");
		Image pic = new Image(titlepic);
		ImageView picview = new ImageView(pic);
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
					Optionsmenu.start();
					stage.close();
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
		
		
		Scene scene = new Scene(t, 1500, 800);
        stage.setTitle("main menu");
        stage.setScene(scene);
        
        stage.show();
        options.addEventFilter(MouseEvent.MOUSE_CLICKED, pressop);
        exit.addEventFilter(MouseEvent.MOUSE_CLICKED, pressext);
        
        	
        
		
	}
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}
}

package views;

import java.awt.font.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.FileInputStream; 
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.*;
import views.Mainmenu;

public class Optionsmenu{
	
	public static boolean flagfs = true ;

	public static void start(Stage stage) throws Exception {
		
		VBox op = new VBox();
		CheckBox fs = new CheckBox("Fullscreen");
		fs.setSelected(flagfs);
		fs.setTextFill(Color.WHITE);
		CheckBox s = new CheckBox("Sound");
		s.setTextFill(Color.WHITE);
		Button r = new Button("Back");
		
		
		op.setScaleX(5);
		op.setScaleY(5);
		op.setSpacing(20);
		op.setAlignment(Pos.CENTER);
		op.getChildren().add(fs);
		op.getChildren().add(s);
		op.getChildren().add(r);
		op.setStyle("-fx-background-color: #000000;");
		
		EventHandler<MouseEvent> fsbox = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent p ) {
				if(fs.isSelected()) {
					stage.setFullScreen(true);
					flagfs = true;
					
				}
				
				else {
					stage.setFullScreen(false);
					flagfs = false;
				}
				
			}
		};
		
		EventHandler<MouseEvent> press = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent p ) {
				try {
					Mainmenu.start(stage, flagfs);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Scene scene = new Scene(op, 1500, 800);
        stage.setTitle("options");
        stage.setScene(scene);
        stage.setFullScreen(flagfs);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent esc) {
        		if(esc.getCode()== KeyCode.ESCAPE )
        			try {
    					Mainmenu.start(stage, flagfs);
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        	}
        });
        fs.addEventFilter(MouseEvent.MOUSE_CLICKED, fsbox);
        r.addEventFilter(MouseEvent.MOUSE_CLICKED, press);

	}

}

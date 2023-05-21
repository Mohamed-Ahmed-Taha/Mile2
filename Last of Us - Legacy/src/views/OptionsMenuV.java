package views;

import controller.MainMenuC;
import controller.OptionsMenuC;
import javafx.stage.Stage;
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

public class OptionsMenuV {
	
	public OptionsMenuV(OptionsMenuC controller, Stage stage, boolean fs) {
		
				Font ac = new Font("Agency FB", 12);
				StackPane stack = new StackPane();
				
				Image titlebg = new Image("/views/media/john-sweeney-main-menu.jpg");
				ImageView bgview = new ImageView(titlebg);
		
				VBox op = new VBox();
				CheckBox full = new CheckBox("Fullscreen");
				full.setSelected(fs);
				full.setTextFill(Color.WHITE);
				CheckBox s = new CheckBox("Sound");
				s.setTextFill(Color.WHITE);
				Button r = new Button("Back");
				
				full.setFont(ac);
				s.setFont(ac);
				r.setFont(ac);
				
				full.setBackground(null);
				s.setBackground(null);
				r.setBackground(null);
				
				full.setTextFill(Color.WHITE);
				s.setTextFill(Color.WHITE);
				r.setTextFill(Color.WHITE);
		
		
				op.setScaleX(5);
				op.setScaleY(5);
				op.setSpacing(20);
				op.setAlignment(Pos.CENTER);
				op.getChildren().add(full);
				op.getChildren().add(s);
				op.getChildren().add(r);
				
				stack.getChildren().add(bgview);
				stack.getChildren().add(op);
				
				Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
		        stage.setTitle("options");
		        stage.setScene(scene);
		        stage.setFullScreen(fs);
		        stage.show();
		        
		        full.setOnMouseClicked(controller);
		        s.setOnMouseClicked(controller);
		        r.setOnMouseClicked(controller);
		        
		        full.setOnMouseEntered(controller);
		        s.setOnMouseEntered(controller);
		        r.setOnMouseEntered(controller);
		        
				full.setOnMouseExited(controller);
				s.setOnMouseExited(controller);
				r.setOnMouseExited(controller);
		
		
		}
	}


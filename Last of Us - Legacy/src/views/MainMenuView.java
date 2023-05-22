package views;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import controller.MainMenuController;
import javafx.scene.image.*;
import javafx.animation.FadeTransition;



public class MainMenuView {
	
	public MainMenuView(MainMenuController controller, Stage stage) throws Exception{
		
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
		fade.setDuration(Duration.millis(2000));
		fade.setFromValue(0);
		fade.setToValue(10);
		fade.setNode(t);
		
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
		
		start.setOnMouseClicked(controller);
		options.setOnMouseClicked(controller);
		exit.setOnMouseClicked(controller);
		
		start.setOnMouseEntered(controller);
		options.setOnMouseEntered(controller);
		exit.setOnMouseEntered(controller);
		
		start.setOnMouseExited(controller);
		options.setOnMouseExited(controller);
		exit.setOnMouseExited(controller);
		
		Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
		stage.setTitle("main menu");
		stage.setScene(scene);
		// stage is initially set to full screen, can be changed in options
		stage.setFullScreen(true);

		fade.play();
		stage.show();
	}
	
	
	public void hoverIn(Button button) {
		button.setTextFill(Color.LIGHTGRAY);
	}
	
	public void hoverOut(Button button) {
		button.setTextFill(Color.WHITE);
	}

}

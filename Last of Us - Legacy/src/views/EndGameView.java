package views;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EndGameView {
	
	public EndGameView (Stage primaryStage, boolean win) {
		
		Font ac = new Font("Agency FB", 12);
		
		VBox stackPane = new VBox();
		Image gameOver = new Image("views\\media\\UBs6WS-removebg-preview.png");
		Image youWin = new Image("views\\media\\2bw1030-removebg-preview.png");
		
		ImageView gameOverView = new ImageView((win ? youWin:gameOver));
		Button exit = new Button("Exit");
		
		FadeTransition fade = new FadeTransition();
		
		fade.setDuration(Duration.millis(1000));
		fade.setFromValue(10);
		fade.setToValue(0.5);
		fade.setCycleCount(1000);
		fade.setAutoReverse(true);
		fade.setNode(exit);
		
		exit.setFont(ac);
		exit.setBackground(null);
		exit.setTextFill(Color.WHITE);
		exit.setScaleX(10);
		exit.setScaleY(10);
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				primaryStage.close();
			}
			
		});
		exit.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				fade.play();
			}
			
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				fade.jumpTo(Duration.ZERO);
				fade.stop();
			}
			
		});
		
		stackPane.getChildren().addAll(gameOverView, exit);
	    stackPane.setStyle("-fx-background-color: #000000;");
	    stackPane.setAlignment(Pos.CENTER);
	    stackPane.setTranslateY(-100);

		
		Scene scene = new Scene(stackPane, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
//		scene.setFill(Color.BLACK);
		primaryStage.hide();
		primaryStage.setScene(scene);
		primaryStage.getScene().setFill(Color.BLACK);
		primaryStage.show();

	}
	
}

package views;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.KeyCombination;

import javax.sound.sampled.*;

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

import java.io.File;
import java.io.InputStream;


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
		Button guide = new Button("Guide");
		Button exit = new Button("Exit");
		
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(2000));
		fade.setFromValue(0);
		fade.setToValue(10);
		fade.setNode(t);
		
		start.setFont(ac);
		guide.setFont(ac);
		exit.setFont(ac);
		
		start.setBackground(null);
		guide.setBackground(null);
		exit.setBackground(null);
		
		start.setTextFill(Color.WHITE);
		guide.setTextFill(Color.WHITE);
		exit.setTextFill(Color.WHITE);
	
		picview.setScaleX(0.5);
		picview.setScaleY(0.5);
		t.setScaleX(4);
		t.setScaleY(4);
		t.setSpacing(4);
		t.getChildren().add(picview);
		t.getChildren().add(start);
		t.getChildren().add(guide);
		t.getChildren().add(exit);
		t.setAlignment(Pos.CENTER);
		t.setBackground(null);
		
		stack.getChildren().add(bgview);		
		stack.getChildren().add(t);
		
		start.setOnMouseClicked(controller);
		guide.setOnMouseClicked(controller);
		exit.setOnMouseClicked(controller);
		
		start.setOnMouseEntered(controller);
		guide.setOnMouseEntered(controller);
		exit.setOnMouseEntered(controller);
		
		start.setOnMouseExited(controller);
		guide.setOnMouseExited(controller);
		exit.setOnMouseExited(controller);


//		File file = new File(getClass().getResource("views/media/titlesound.mp3").toURI());
//		playAudio(file.toString());
//
//		Media media = new Media(new File("views/media/The Last Of us - Theme song.mp3").toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.play();

		Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
		stage.setTitle("main menu");
		stage.hide();
		stage.setScene(scene);
		// stage is initially set to full screen, can be changed in options
		stage.setFullScreen(true);
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);


		fade.play();
		stage.show();
	}

	public static void playAudio(String filePath) {
		try {
			InputStream audioInputStream = MainMenuView.class.getResourceAsStream(filePath);
			if (audioInputStream == null) {
				System.err.println("Audio file not found: " + filePath);
				return;
			}
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioInputStream);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	public void hoverIn(Button button) {
		button.setTextFill(Color.LIGHTGRAY);
	}
	
	public void hoverOut(Button button) {
		button.setTextFill(Color.WHITE);
	}

}

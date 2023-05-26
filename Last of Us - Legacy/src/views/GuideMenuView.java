package views;

import views.controller.GuideMenuController;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;

public class GuideMenuView {
	
	public GuideMenuView(GuideMenuController controller, Stage stage) {
				
		Font ac = new Font("Agency FB", 50);
		StackPane stack = new StackPane();
		
		Button back = new Button("Back");
		back.setTextFill(Color.WHITE);
		back.setBackground(null);
		back.setOnMouseClicked(controller);
		back.setFont(ac);
		
		VBox g = new VBox();
		Image titlebg = new Image("/views/media/john-sweeney-main-menu.jpg");
		ImageView bgview = new ImageView(titlebg);
		
		VBox box = new VBox();
		box.setMinWidth(Screen.getPrimary().getBounds().getMaxX()-360);
		box.setMinHeight(Screen.getPrimary().getBounds().getMaxY()-360);
		box.setSpacing(10);
		
		Color backgroundColor = new Color(0.5, 0.5, 0.5, 0.5);
		BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(backgroundFill);
		
		box.setBackground(background);
		box.setAlignment(Pos.CENTER);
		
		Label controls = new Label();
		controls.setFont(ac);
		controls.setText(" use arrow keys in order to move (costs action points) \n\n press 'C' then select a zombie to cure (costs a vaccine) \n\n press 'A' then select a zombie to attack (costs action points) \n\n press 'S' to activate a character's special (costs a supply): \n    Fighters: can attack without using action points (you need to have one action point) \n    Medics: can select and heal adjacent heroes (you need to have one action point) \n    Explorers: reveal the whole map until the end of the turn (you need to have one action point) \n\n to win you need to use all the vaccines and have 5 or more heroes alive \n\n you lose if all your heroes die");
		controls.setTextFill(Color.WHITE);
		box.getChildren().addAll(controls, back);
		stack.getChildren().addAll(bgview, box);
		Scene scene = new Scene(stack);
		stage.setTitle("Guide");
		stage.hide();
		stage.setScene(scene);
		// stage is initially set to full screen, can be changed in options
		stage.setFullScreen(true);
		stage.show();
		
				
	}
	
}


package views;

import controller.OptionsMenuController;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;

public class OptionsMenuView {
	
	public OptionsMenuView(OptionsMenuController controller, Stage stage) {
				
		Font ac = new Font("Agency FB", 12);
		StackPane stack = new StackPane();
				
		Image titlebg = new Image("/views/media/john-sweeney-main-menu.jpg");
		ImageView bgview = new ImageView(titlebg);
		
		VBox op = new VBox();
		CheckBox full = new CheckBox("Fullscreen");
		full.setSelected(stage.isFullScreen());
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
				
		full.setOnMouseClicked(controller);
		s.setOnMouseClicked(controller);
		r.setOnMouseClicked(controller);
				
		full.setOnMouseEntered(controller);
		s.setOnMouseEntered(controller);
		r.setOnMouseEntered(controller);
				
		full.setOnMouseExited(controller);
		s.setOnMouseExited(controller);
		r.setOnMouseExited(controller);
				
		Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
		stage.setTitle("options");
		stage.hide();
		stage.setScene(scene);
		stage.setFullScreen(true);	
		stage.show();
		
	}
	
	
	public void hoverIn(ButtonBase buttonBase) {
		buttonBase.setTextFill(Color.LIGHTGRAY);
	}
	
	public void hoverOut(ButtonBase buttonBase) {
		buttonBase.setTextFill(Color.WHITE);
	}
	
}


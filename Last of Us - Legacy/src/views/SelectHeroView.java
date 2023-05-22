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
import controller.SelectHeroController;

public class SelectHeroView {
	
	public SelectHeroView(SelectHeroController controller, Stage stage) {
		
		Font ac = new Font("Agency FB", 12);
		
		VBox v = new VBox();
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		
		Button joelB = new Button("Joel");
		Button ellieB = new Button("Ellie");
		Button tessB = new Button("Tess");
		Button rileyB = new Button("Riley");
		Button tommyB = new Button("Tommy");
		Button billB = new Button("Bill");
		Button davidB = new Button("David");
		Button henryB = new Button("Henry");
		Button r = new Button("Back");
		
		joelB.setFont(ac);
		ellieB.setFont(ac);
		tessB.setFont(ac);
		rileyB.setFont(ac);
		tommyB.setFont(ac);
		billB.setFont(ac);
		davidB.setFont(ac);
		henryB.setFont(ac);
		r.setFont(ac);
		
		joelB.setBackground(null);
		ellieB.setBackground(null);
		tessB.setBackground(null);
		rileyB.setBackground(null);
		tommyB.setBackground(null);
		billB.setBackground(null);
		davidB.setBackground(null);
		henryB.setBackground(null);
		r.setBackground(null);
		
		joelB.setTextFill(Color.WHITE);
		ellieB.setTextFill(Color.WHITE);
		tessB.setTextFill(Color.WHITE);
		rileyB.setTextFill(Color.WHITE);
		tommyB.setTextFill(Color.WHITE);
		billB.setTextFill(Color.WHITE);
		davidB.setTextFill(Color.WHITE);
		henryB.setTextFill(Color.WHITE);
		r.setTextFill(Color.WHITE);
		
		setHoverText(joelB);
		setHoverText(ellieB);
		setHoverText(tessB);
		setHoverText(rileyB);
		setHoverText(tommyB);
		setHoverText(billB);
		setHoverText(davidB);
		setHoverText(henryB);
		
		h1.getChildren().add(joelB);
		h1.getChildren().add(ellieB);
		h1.getChildren().add(tessB);
		h1.getChildren().add(rileyB);
		h1.setAlignment(Pos.CENTER);
		h1.setSpacing(10);
		
		h2.getChildren().add(tommyB);
		h2.getChildren().add(billB);
		h2.getChildren().add(davidB);
		h2.getChildren().add(henryB);
		h2.setAlignment(Pos.CENTER);
		h2.setSpacing(10);
				
		v.getChildren().addAll(h1, h2, r);
		v.setAlignment(Pos.CENTER);
		v.setSpacing(5);
		v.setScaleX(5);
		v.setScaleY(5);
		v.setStyle("-fx-background-color: #000000;");
		
		joelB.setOnMouseClicked(controller);
		ellieB.setOnMouseClicked(controller);
		tessB.setOnMouseClicked(controller);
		rileyB.setOnMouseClicked(controller);
		tommyB.setOnMouseClicked(controller);
		billB.setOnMouseClicked(controller);
		davidB.setOnMouseClicked(controller);
		henryB.setOnMouseClicked(controller);
		r.setOnMouseClicked(controller);
		
		joelB.setOnMouseEntered(controller);
		ellieB.setOnMouseEntered(controller);
		tessB.setOnMouseEntered(controller);
		rileyB.setOnMouseEntered(controller);
		tommyB.setOnMouseEntered(controller);
		billB.setOnMouseEntered(controller);
		davidB.setOnMouseEntered(controller);
		henryB.setOnMouseEntered(controller);
		r.setOnMouseEntered(controller);
		
		joelB.setOnMouseExited(controller);
		ellieB.setOnMouseExited(controller);
		tessB.setOnMouseExited(controller);
		rileyB.setOnMouseExited(controller);
		tommyB.setOnMouseExited(controller);
		billB.setOnMouseExited(controller);
		davidB.setOnMouseExited(controller);
		henryB.setOnMouseExited(controller);
		r.setOnMouseExited(controller);
				
		Scene scene = new Scene(v, Screen.getPrimary().getBounds().getMaxX()- 360, Screen.getPrimary().getBounds().getMaxY()-360);
		stage.setScene(scene);
		stage.setFullScreen(stage.isFullScreen());
//		stage.show();
	}
	
	public void hoverIn(Button button) {
		button.setTextFill(Color.LIGHTGRAY);
	}
	
	public void hoverOut(Button button) {
		button.setTextFill(Color.WHITE);
	}
	
	public void setHoverText(Button hoverButton) {
		
		String hoverText = SelectHeroController.getStartAttributes(hoverButton);
		
		Tooltip hoverBox = new Tooltip(hoverText);
		
		Font ac = new Font("Agency FB", 30);

		hoverBox.setFont(ac);
		hoverBox.setShowDelay(Duration.ZERO);
		hoverBox.setShowDuration(Duration.INDEFINITE);
		hoverBox.setHideDelay(Duration.ZERO);
		hoverButton.setTooltip(hoverBox);

	}
	
}

package views;
//import taha
import javafx.scene.Scene;
import javafx.stage.*;
import model.characters.Hero;
import model.world.CharacterCell;
import model.characters.Hero;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.util.Duration;

import java.lang.Math;
import controller.GameGridController;
import engine.Game;
import javafx.scene.layout.*;
import javafx.scene.image.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameGridView {
	
	private static final double screenX = Screen.getPrimary().getBounds().getMaxX();
	private static final double screenY = Screen.getPrimary().getBounds().getMaxY();
	
	private static final int GRID_SIZE = 15;
	private static final double CELL_SIZE = ((screenX/screenY)*(Math.sqrt((((screenX*screenX)+(screenY*screenY)))))/100) + 22;
	
	private static GridPane gridPane;
	private static Label heroAttributesPanel;
	private static Stage stage;
	private static StackPane stack;

	
	public GameGridView(GameGridController controller, Stage primaryStage) {
		
		GameGridView.stage = primaryStage;
		stage.setAlwaysOnTop(true);
		
		Font ac = new Font("Agency FB", 20);
		
		stack = new StackPane();
		HBox hBox= new HBox(300);
		
		heroAttributesPanel = new Label();
		
		Image empcell = new Image("/views/media/Empty Cell.jpeg");
		Image gridBg = new Image("/views/media/pxArt.png");
		ImageView bgView = new ImageView(gridBg);
		
		gridPane = new GridPane();
	    for (int row = 0; row < GRID_SIZE; row++) {
	    	for (int col = 0; col < GRID_SIZE; col++) {
	    		Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
	            rectangle.setFill(null);
	            rectangle.setStroke(Color.WHITE);
	            rectangle.setStrokeType(StrokeType.INSIDE);
	            rectangle.setOnMouseEntered(controller);
	            rectangle.setOnMouseExited(controller);
	            rectangle.setOnMouseClicked(controller);

	            
	    	    GridPane.setRowIndex(rectangle, row);
	            GridPane.setColumnIndex(rectangle, col);
	            gridPane.getChildren().add(rectangle);
//	            gridPane.setAlignment(Pos.CENTER);
	        }
	    }
	    
	    bgView.setFitHeight(Screen.getPrimary().getBounds().getMaxY());
	    bgView.setFitWidth(Screen.getPrimary().getBounds().getMaxX());

	    hBox.getChildren().addAll(gridPane, heroAttributesPanel);
	    hBox.setAlignment(Pos.CENTER_LEFT);
	    heroAttributesPanel.setFont(ac);
	    heroAttributesPanel.setScaleX(2.5);
	    heroAttributesPanel.setScaleY(2.5);
//	    heroAttributesPanel.setBackground();
	    
	    stack.getChildren().add(bgView);
	    
	    gridPane.setAlignment(Pos.CENTER_LEFT);
	    
	    stack.getChildren().add(hBox);
	    
    	Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
    	stage.setTitle("15x15 grid");
		stage.hide();
    	stage.setScene(scene);
    	stage.setFullScreen(stage.isFullScreen());
    	stage.show();
    	    	
    	scene.setOnKeyPressed(controller);
 
	}	
	
	
	
	public static GridPane getGridPane() {
		return gridPane;
	}



	public void updateMap(char[][] mapForPrint) {

//		Image joel = new Image("/views/media/Fighter/knight_f_idle_anim_f0.png");
//		Image zomb = new Image("/views/media/Zombie/zombie_anim_f10.png");
//		Image viscell = new Image("/views/media/0x72_DungeonTilesetII_v1.6.png");
		
		
		for (int i = 0; i < 15; i++) 
			for (int j = 0; j < 15; j++) 
				renderCell(14 - i, j, mapForPrint[i][j]);

	}
	
	static Image joel = new Image("/views/media/joel.png");
	static Image ellie = new Image("/views/media/ellie.png");
	static Image tess = new Image("/views/media/tess.png");
	static Image riley = new Image("/views/media/riley.png");
	static Image tommy = new Image("/views/media/tommy.png");
	static Image bill = new Image("/views/media/bill.png");
	static Image david = new Image("/views/media/david.png");
	static Image henry = new Image("/views/media/henry.png");
	static Image clicker = new Image("/views/media/clicker.png");
	static Image supplies = new Image("/views/media/supplies.png");
	static Image vaccine = new Image("/views/media/vaccine.png");
	
	public static String getName(int i, int j) {
		if(Game.checkHero(i, j)) {
			Hero hero = (Hero)((CharacterCell) Game.map[i][j]).getCharacter();
			return hero.getName();
		}
		
		else
			return null;
		
	}
	
	private static void renderCell(int i, int j, char cell) {
		
		// 'n' -> not visible
		// 'e' -> empty
		// 'x' -> explorer
		// 'f' -> fighter
		// 'm' -> medic
		// 'z' -> zombie
		// 's' -> supply
		// 'v' -> vaccine
		// 't' -> trap
		
		// TODO instead of color, should be loading certain image
		Color color;
		
		switch (cell) {
		
		case 'n':
			color = Color.BLACK; 
			getRectangle(i, j).setFill(color);
			getRectangle(i, j).setOpacity(0.5); break;
			
		case 'e':
		case 't':
			color = Color.WHITE; 
			getRectangle(i, j).setFill(color); 
			getRectangle(i, j).setOpacity(0.5); break;
		case 'z':
			getRectangle(i, j).setFill(new ImagePattern(clicker));
			getRectangle(i, j).setOpacity(1); break;
		case 's':
			getRectangle(i, j).setFill(new ImagePattern(supplies));
			getRectangle(i, j).setOpacity(0.5); break;
		case 'v':
			getRectangle(i, j).setFill(new ImagePattern(vaccine));
			getRectangle(i, j).setOpacity(0.5); break;
		case 'x':
			if(getName(14 - i, j) != null) {
				switch (getName(14 - i, j)) {
					case ("Tess") -> getRectangle(i, j).setFill(new ImagePattern(tess));
					case ("Riley Abel") -> getRectangle(i, j).setFill(new ImagePattern(riley));
					case ("Tommy Miller") -> getRectangle(i, j).setFill(new ImagePattern(tommy));
				}
			}
			getRectangle(i, j).setOpacity(1);break;
		case 'f':
			if(getName(14- i, j) != null){
				switch (getName(14 - i, j)) {
					case ("Joel Miller") -> getRectangle(i, j).setFill(new ImagePattern(joel));
					case ("David") -> getRectangle(i, j).setFill(new ImagePattern(david));
				}
			}
			getRectangle(i, j).setOpacity(1); break;
		case 'm':
			if(getName(14- i, j) != null) {
				switch (getName(14 - i, j)) {
					case ("Bill") -> getRectangle(i, j).setFill(new ImagePattern(bill));
					case ("Ellie Williams") -> getRectangle(i, j).setFill(new ImagePattern(ellie));
					case ("Henry Burell") -> getRectangle(i, j).setFill(new ImagePattern(henry));
				}
			}
			getRectangle(i, j).setOpacity(1); break;
			
		}
		
	}
	
	
	public static void setAttributesPanel(String heroAttributes) {
		
		heroAttributesPanel.setText(heroAttributes);
		heroAttributesPanel.setTextFill(Color.WHITE);
		
	}
	
	
	public void printException(Exception e) {
		
//		Rectangle rectangle = new Rectangle();
//		rectangle.setFill(Color.BLACK);
//		
//		Label l = new Label(e.getMessage());
//		
//		stack.getChildren().addAll(rectangle, l);

		
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(e.getMessage());
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);
		alert.show();

		
//		JOptionPane pane = new JOptionPane();
//		JFrame frame = new JFrame();
//		
//		JOptionPane.showMessageDialog(null, e.getMessage());
		
//		pane.showmess
		
//		Popup popup = new Popup();
//		Label text = new Label(e.getMessage());
//		StackPane stackPane = new StackPane();
//		stackPane.set
//		
//		popup.centerOnScreen();
//		popup.getContent().add(text);
//		popup.show(stage);
//		popup.setAutoHide(true);
//		popup.set
		
//		PopupWindow popupWindow = ;
		
	}
	
	
	public void playTrapAnimation() {
		
		// TODO make trap animation
		
	}
	
	
	private static Rectangle getRectangle(int row, int col) {
		for (javafx.scene.Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
				return (Rectangle) node;
		}
		return null;
	}
	
}

package views;
//import taha
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.Math;
import controller.GameGridController;
import javafx.scene.layout.*;
import javafx.scene.image.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameGridView {
	
	private static final double screenX = Screen.getPrimary().getBounds().getMaxX();
	private static final double screenY = Screen.getPrimary().getBounds().getMaxY();
	
	private static final int GRID_SIZE = 15;
	private static final double CELL_SIZE = ((screenX/screenY)*(Math.sqrt((((screenX*screenX)+(screenY*screenY)))))/100) + 25;
	
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
		Image gridBg = new Image("/views/media/WhatsApp Image 2023-05-22 at 07.33.03.jpeg");
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
			color = Color.BLACK; break;
		case 'e':
		case 't':
			color = Color.WHITE; break;
		case 'x':
			color = Color.YELLOW; break;
		case 'f':
			color = Color.BLUE; break;
		case 'm':
			color = Color.GREEN; break;
		case 'z':
			color = Color.RED; break;
		case 's':
			color = Color.GOLD; break;
		case 'v':
			color = Color.PINK; break;
		default:
			color = Color.BEIGE;
			
		}
		
		getRectangle(i, j).setFill(color);
		
	}
	
	
	public static void setAttributesPanel(String heroAttributes) {
		
		heroAttributesPanel.setText(heroAttributes);
		
	}
	
	
	public void printException(Exception e) {
		
//		Rectangle rectangle = new Rectangle();
//		rectangle.setFill(Color.BLACK);
//		
//		Label l = new Label(e.getMessage());
//		
//		stack.getChildren().addAll(rectangle, l);

		
		
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(e.getMessage());
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
	
	private static Rectangle getRectangle(int row, int col) {
		for (javafx.scene.Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
				return (Rectangle) node;
		}
		return null;
	}
	
}

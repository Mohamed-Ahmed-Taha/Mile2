package views;
//import taha
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.geometry.Pos;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.Point;
import java.lang.Math;
import controller.GameGridController;
import engine.Game;
import javafx.scene.layout.*;
import javafx.scene.image.*;

public class GameGridView {
	
	private static final double screenX = Screen.getPrimary().getBounds().getMaxX();
	private static final double screenY = Screen.getPrimary().getBounds().getMaxY();
	
	private static final int GRID_SIZE = 15;
	private static final double CELL_SIZE = ((screenX/screenY)*(Math.sqrt((((screenX*screenX)+(screenY*screenY)))))/100) + 25;
	
	private static GridPane gridPane;
	
	public GameGridView(GameGridController controller, Stage stage) {
		
		StackPane stack = new StackPane();
		BorderPane border = new BorderPane();
		
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

	    	    GridPane.setRowIndex(rectangle, row);
	            GridPane.setColumnIndex(rectangle, col);
	            gridPane.getChildren().add(rectangle);
	            gridPane.setAlignment(Pos.CENTER);
	        }
	    }
	    
	    bgView.setFitHeight(Screen.getPrimary().getBounds().getMaxY());
	    bgView.setFitWidth(Screen.getPrimary().getBounds().getMaxX());

	    
	    stack.getChildren().add(bgView);
	    border.setCenter(gridPane);
	    stack.getChildren().add(border);
	    
	    
    	Scene scene = new Scene(stack, Screen.getPrimary().getBounds().getMaxX()-360, Screen.getPrimary().getBounds().getMaxY()-360);
    	stage.setTitle("15x15 grid");
		stage.hide();
    	stage.setScene(scene);
    	stage.setFullScreen(stage.isFullScreen());
    	stage.show();
    	
//    	updateMap(GameGridController.getVisibleCells());
    	
    	scene.setOnKeyPressed(controller);
    	gridPane.setOnMouseEntered(controller);
		for(int i = 0; i<Game.heroes.size(); i++) {
			Point location = Game.heroes.get(i).getLocation();
			getRectangle(location.x, location.y).setOnMouseEntered(controller);
		}
		
 
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
	
	
	public static Rectangle getRectangle(int row, int col) {
		for (javafx.scene.Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
				return (Rectangle) node;
		}
		return null;
	}
	
}

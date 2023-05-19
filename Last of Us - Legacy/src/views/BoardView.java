package views;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import java.util.ArrayList;



public class BoardView {
	
    private static final int GRID_SIZE = 15;
    private static final double CELL_SIZE = 45;
    
    private static GridPane gridPane = new GridPane();
    private static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	
	public static void initMap(Stage currentStage) {

	    for (int row = 0; row < GRID_SIZE; row++) {
	    	for (int col = 0; col < GRID_SIZE; col++) {
	    		Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
	            rectangle.setFill(Color.BLACK);
	            rectangle.setStroke(Color.BLACK);
	            rectangle.setStrokeType(StrokeType.INSIDE);
	    	    
	    	    rectangles.add(rectangle);
	    	    GridPane.setRowIndex(rectangle, row);
	            GridPane.setColumnIndex(rectangle, col);
	            gridPane.getChildren().add(rectangle);
	            gridPane.setAlignment(Pos.CENTER);
	        }
	    }
	              

        Scene scene = new Scene(gridPane, CELL_SIZE*GRID_SIZE +10, CELL_SIZE* GRID_SIZE +10);
        currentStage.setTitle("15x15 grid");
        currentStage.setScene(scene);
        currentStage.show();
	}
	
	public static void updateMap(boolean [][] visible) {
		
		for (int i = 0; i < visible.length; i++) {
			for (int j = 0; j < visible[i].length; j++) {
				if (visible[i][j])
					getRectangle(14 - i, j).setFill(Color.WHITE);
			}
		}
		
	}
	
	private static Rectangle getRectangle(int row, int col) {
		for (javafx.scene.Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
				return (Rectangle) node;
		}
		return null;
	}
}

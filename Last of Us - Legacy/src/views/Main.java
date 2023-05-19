package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Main extends Application {
    private static final int GRID_SIZE = 15;
    private static final double CELL_SIZE = 45;
    @Override
    public void start(Stage stage) throws Exception {

        GridPane gridPane = new GridPane();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
                rectangle.setFill(Color.NAVAJOWHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeType(StrokeType.INSIDE);

                GridPane.setRowIndex(rectangle, row);
                GridPane.setColumnIndex(rectangle, col);
                gridPane.getChildren().add(rectangle);
            }
        }

        gridPane.setAlignment(Pos.CENTER);
//        double userMaxX = Screen.getPrimary().getBounds().getMaxX();
//        double userMaxY = Screen.getPrimary().getBounds().getMaxY();

        Scene scene = new Scene(gridPane, CELL_SIZE*GRID_SIZE +10, CELL_SIZE* GRID_SIZE +10);
        stage.setTitle("15x15 grid");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

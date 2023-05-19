package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Main extends Application {
    private static final int GRID_SIZE = 15;
    private static final double CELL_SIZE = 66.5;
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);

                GridPane.setRowIndex(rectangle, row);
                GridPane.setColumnIndex(rectangle, col);
                gridPane.getChildren().add(rectangle);
            }
        }
        
//        Label l = new Label("zby");
//        Label l2 = new Label("tez");
//        VBox v = new VBox();
//        v.getChildren().addAll(l, l2);
//        root.setCenter(v);
        
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);
        stage.setTitle("15x15 grid");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PrivMain extends Application {
    private static final int GRID_SIZE = 15;
    private static final int CELL_SIZE = 30;
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
        root.setCenter(gridPane);


        Scene scene = new Scene(root);
        stage.setTitle("15x15 grid");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

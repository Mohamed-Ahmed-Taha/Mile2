package views;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class CustomProgressBar extends StackPane {
    private ProgressBar progressBar;
    private Label label;

    public CustomProgressBar(double value, double max) {
        progressBar = new ProgressBar(value / max);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        label = new Label((int) value + "/" + (int) max);
        StackPane.setAlignment(label, Pos.CENTER);

        getChildren().addAll(progressBar, label);
        setAlignment(Pos.CENTER);
    }
}


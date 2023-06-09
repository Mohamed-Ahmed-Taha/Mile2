package views;


import views.controller.SelectHeroController;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

import model.characters.*;

public class SelectHeroView {
	
    ArrayList<Hero> availableHeroes;
    private static final int CELL_SIZE = 315;
    private static final int GRID_SIZE = 4;
    private Stage stage;
    private SelectHeroController controller;
    GridPane gridPane = new GridPane();
    private MediaPlayer soundEffectPlayer;


    public SelectHeroView(Stage stage, ArrayList<Hero> availableHeroes, SelectHeroController controller) {
    this.stage = stage;
    this.availableHeroes = availableHeroes;
    this.controller = controller;


}

public void createUI() {
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setHgap(100);
    gridPane.setVgap(100);

    int row = 0;
    int col = 0;

    for (Hero h : availableHeroes) {
            VBox characterBox = createCharacterBox(h);
            characterBox.setOnMouseEntered(e -> {
                    characterBox.setPrefSize(CELL_SIZE , CELL_SIZE );
                    characterBox.setStyle("-fx-background-color: rgba(139, 64, 0, 0.2);");
                String soundEffectFile = "/views/media/Menu Tick-Hover.mp3";
                Media soundEffectMedia = new Media(getClass().getResource(soundEffectFile).toExternalForm());
                soundEffectPlayer = new MediaPlayer(soundEffectMedia);
                soundEffectPlayer.setCycleCount(1);
                soundEffectPlayer.play();
                animateCharacterInfoAppearance(characterBox, h);
            });
            characterBox.setOnMouseExited(e -> {
                    characterBox.setPrefSize(CELL_SIZE, CELL_SIZE);
                    characterBox.setStyle("-fx-background-color: transparent;");
            });
            characterBox.setOnMouseClicked(e -> {
                    controller.onCharacterSelected(h);
            });

            gridPane.add(characterBox, col, row);
            col++;

            if (col == GRID_SIZE) {
                    col = 0;
                    row++;
            }
    }

    BorderPane rootPane = new BorderPane(gridPane);

    Label selectLabel = new Label("Select your Hero");
    selectLabel.setFont(Font.font("Agency FB", FontWeight.EXTRA_BOLD, 64));
    selectLabel.setTextFill(Color.WHITE);
    BorderPane.setAlignment(selectLabel, Pos.CENTER);
    rootPane.setTop(selectLabel);

    rootPane.setStyle("-fx-background-color: #000000;");

    Scene scene = new Scene(rootPane);
    stage.setScene(scene);
    stage.setFullScreen(true);
    stage.setTitle("Select Hero Screen");

    scene.setOnKeyPressed(e ->{
                if(e.getCode() == KeyCode.ESCAPE)
                    stage.close();
            }
    );

    stage.show();
    }

public void animateCharacterInfoAppearance(VBox characterBox, Hero h) {
    if(characterBox.getChildren().size() == 2){
    ArrayList<Text> infoTexts = new ArrayList<>();
    int fontSize = 20;
    String font = "Agency FB";
    Text actionLabel = new Text("Actions: " + h.getMaxActions());
        if(h.getMaxActions()>=6)
            actionLabel.setFill(Color.LIGHTGREEN);
        else actionLabel.setFill(Color.RED);
    actionLabel.setFont(Font.font(font, fontSize));
    infoTexts.add(actionLabel);
    Text hpLabel = new Text("HP: " + h.getCurrentHp());
        if(h.getCurrentHp()>=100)
            hpLabel.setFill(Color.LIGHTGREEN);
        else hpLabel.setFill(Color.RED);

    hpLabel.setFont(Font.font(font, fontSize));
    infoTexts.add(hpLabel);
    Text attackLabel = new Text("Attack: " + h.getAttackDmg());
    if(h.getAttackDmg()>=25)
        attackLabel.setFill(Color.LIGHTGREEN);
    else attackLabel.setFill(Color.RED);
    attackLabel.setFont(Font.font(font, fontSize));
    infoTexts.add(attackLabel);

    String type = "";
    if(h instanceof Fighter)
            type = "Fighter" ;
    if(h instanceof Explorer)
            type = "Explorer";
    if(h instanceof Medic)
            type = "Medic";

    Text typeLabel = new Text("Type: " + type);
    typeLabel.setFill(Color.LIGHTGOLDENRODYELLOW);
    typeLabel.setFont(Font.font(font, fontSize));
    infoTexts.add(typeLabel);

    FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), characterBox);
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
    fadeTransition.play();

    SequentialTransition sequentialTransition = new SequentialTransition();
        for (Text infoText : infoTexts) {
            infoText.setOpacity(0);
            infoText.setTranslateY(-20);
            characterBox.getChildren().add(infoText);

            PauseTransition delay = new PauseTransition(Duration.millis(200));
            FadeTransition fadeIn = new FadeTransition(Duration.millis(200), infoText);
            TranslateTransition translateIn = new TranslateTransition(Duration.millis(200), infoText);
            fadeIn.setToValue(1);
            translateIn.setToY(0);

            sequentialTransition.getChildren().addAll(delay, new ParallelTransition(fadeIn, translateIn));
        }
    sequentialTransition.play();
    } }
    public VBox createCharacterBox(Hero h) {

            VBox characterBox = new VBox();
            characterBox.setAlignment(Pos.CENTER);
        ImageView heroIcon = null;

        switch (h.getName()) {

            case "Joel Miller" ->
                    heroIcon = new ImageView(new Image("views/media/joel.png"));

            case "Ellie Williams" ->
                    heroIcon = new ImageView(new Image("views/media/ellie.png"));

            case "Tess" ->
                    heroIcon = new ImageView(new Image("views/media/tess.png"));

            case "Riley Abel" ->
                    heroIcon = new ImageView(new Image("views/media/riley.png"));

            case "Tommy Miller" ->
                    heroIcon = new ImageView(new Image("views/media/tommy.png"));

            case "Bill" ->
                    heroIcon = new ImageView(new Image("views/media/bill.png"));

            case "David" ->
                    heroIcon = new ImageView(new Image("views/media/david.png"));

            case "Henry Burell" ->
                    heroIcon = new ImageView(new Image("views/media/henry.png"));
        }

            ImageView imageView = heroIcon;
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            Text nameLabel = new Text(h.getName());
            nameLabel.setFill(Color.WHITE);
            nameLabel.setFont(Font.font("Agency FB", FontWeight.EXTRA_BOLD, 32));
            //remember to add images based on the names
//            Text attackDamageLabel = new Text("Att: " + h.getAttackDmg());
//            attackDamageLabel.setFill(Color.WHITE);
//            Text hpLabel = new Text("HP: " + h.getCurrentHp());
//            hpLabel.setFill(Color.WHITE);
//            String type = "";
//            if(h instanceof Fighter)
//                    type = "Fighter" ;
//            if(h instanceof Explorer)
//                    type = "Explorer";
//            if(h instanceof Medic)
//                    type = "Medic";
//            Text typeLabel = new Text(type);
//            typeLabel.setFill(Color.WHITE);
//            Text actionsPerTurnLabel = new Text("Actions per Turn: " + h.getMaxActions());
//            actionsPerTurnLabel.setFill(Color.WHITE);

            // characterBox.getChildren().addAll(imageView, nameLabel, attackDamageLabel, hpLabel, typeLabel, actionsPerTurnLabel);
            characterBox.getChildren().addAll(imageView, nameLabel);
            characterBox.setPrefSize(CELL_SIZE, CELL_SIZE);
            return characterBox;
    }

}

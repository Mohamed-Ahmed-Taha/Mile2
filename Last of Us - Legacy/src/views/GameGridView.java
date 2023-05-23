package views;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.*;
import model.characters.*;
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
import java.util.ArrayList;

import controller.GameGridController;
import engine.Game;
import javafx.scene.layout.*;
import javafx.scene.image.*;

import static engine.Game.heroes;


public class GameGridView {
	
	private static final double screenX = Screen.getPrimary().getBounds().getMaxX();
	private static final double screenY = Screen.getPrimary().getBounds().getMaxY();
	
	private static final int GRID_SIZE = 15;
	private static final double CELL_SIZE = ((screenX/screenY)*(Math.sqrt((((screenX*screenX)+(screenY*screenY)))))/100) + 22;
	
	private static GridPane gridPane;
	private static FlowPane heroAttributesPanel;
	private static Stage stage;
	private static StackPane stack;

	
	public GameGridView(GameGridController controller, Stage primaryStage) {
		
		GameGridView.stage = primaryStage;
		stage.setAlwaysOnTop(true);
		
		Font ac = new Font("Agency FB", 20);
		
		stack = new StackPane();
		HBox hBox= new HBox(300);
		
		heroAttributesPanel = new FlowPane();
		heroAttributesPanel.setHgap(20);
		heroAttributesPanel.setVgap(50);
		heroAttributesPanel.setTranslateX(-150);
		heroAttributesPanel.setTranslateY(100);
		heroAttributesPanel.setMinWidth(420);


		heroAttributesPanel.getChildren().add(createCharacterBox2(controller.getHeroSelected()));


		
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
		gridPane.setTranslateX(50);

		bgView.setFitHeight(Screen.getPrimary().getBounds().getMaxY());
	    bgView.setFitWidth(Screen.getPrimary().getBounds().getMaxX());

	    hBox.getChildren().addAll(gridPane, heroAttributesPanel);
	    hBox.setAlignment(Pos.CENTER_LEFT);

	    
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

	public Pane createCharacterBox2(Hero hero) {
		ImageView heroIcon = null;
		switch (hero.getName()) {
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
		heroIcon.setFitWidth(50);
		heroIcon.setFitHeight(50);



		Label nameLabel = new Label(hero.getName());

		ProgressBar hpBar = new ProgressBar(hero.getCurrentHp()/100.0);
		hpBar.setMaxWidth(Double.MAX_VALUE);

		// Create labels for attack damage, actions left, and type
		Label attackDamageLabel = new Label("Attack Damage: " + hero.getAttackDmg());
		Label actionsLeftLabel = new Label("Actions Left: " + hero.getActionsAvailable());
		String type = "";
		if(hero instanceof Fighter)
			type = "Fighter" ;
		if(hero instanceof Explorer)
			type = "Explorer";
		if(hero instanceof Medic)
			type = "Medic";
		Label typeLabel = new Label("Type: " + type);

		VBox characterBox = new VBox();
		characterBox.setSpacing(10);
		characterBox.setPadding(new Insets(10));
		characterBox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

		HBox topRow = new HBox(heroIcon, nameLabel);
		topRow.setSpacing(10);
		topRow.setFillHeight(true);
		topRow.setHgrow(nameLabel, Priority.ALWAYS);
		characterBox.getChildren().addAll(topRow, hpBar, attackDamageLabel, actionsLeftLabel, typeLabel);
		characterBox.setMinWidth(200);
		characterBox.setMinHeight(150);

		return characterBox;
	}
	public void updateCharacterBoxes() {
		heroAttributesPanel.getChildren().clear();

		for (Hero hero : heroes) {
			Pane characterBox = createCharacterBox2(hero);
			heroAttributesPanel.getChildren().add(characterBox);
		}
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
				switch(getName(14 - i, j)) {

				case("Tess"):
					getRectangle(i, j).setFill(new ImagePattern(tess)); break;
				case("Riley Abel"):
					getRectangle(i, j).setFill(new ImagePattern(riley)); break;
				case("Tommy Miller"):
					getRectangle(i, j).setFill(new ImagePattern(tommy));
				}
			}
			getRectangle(i, j).setOpacity(1);break;
		case 'f':
			if(getName(14- i, j) != null){
				switch(getName(14 - i, j)) {
			
				case("Joel Miller"):
					getRectangle(i, j).setFill(new ImagePattern(joel)); break;
				case("David"):
					getRectangle(i, j).setFill(new ImagePattern(david));
				}
			}
			getRectangle(i, j).setOpacity(1); break;
		case 'm':
			if(getName(14- i, j) != null) {
				switch(getName(14 - i, j)) {
			
				case("Bill"):
					getRectangle(i, j).setFill(new ImagePattern(bill)); break;
				case("Ellie Williams"):
					getRectangle(i, j).setFill(new ImagePattern(ellie)); break;
				case("Henry Burell"):
					getRectangle(i, j).setFill(new ImagePattern(henry));
				}
			}
			getRectangle(i, j).setOpacity(1); break;
		default:

			
		}
		
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

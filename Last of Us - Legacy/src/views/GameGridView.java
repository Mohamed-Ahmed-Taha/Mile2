package views;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import model.characters.*;
import model.characters.Character;
import model.world.CharacterCell;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import java.awt.Point;
import java.lang.Math;
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
		
		new Font("Agency FB", 20);
		
		stack = new StackPane();
		HBox hBox= new HBox(300);
		
		heroAttributesPanel = new FlowPane();
		heroAttributesPanel.setHgap(20);
		heroAttributesPanel.setVgap(50);
		heroAttributesPanel.setTranslateX(-150);
		heroAttributesPanel.setTranslateY(50);
		heroAttributesPanel.setMinWidth(420);


		heroAttributesPanel.getChildren().add(createCharacterBox2(GameGridController.getHeroSelected()));


		
		new Image("/views/media/Empty Cell.jpeg");
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
    	stage.setFullScreen(true);
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
		nameLabel.setFont(Font.font("Agency FB", FontWeight.BOLD,  20));

		ProgressBar hpBar = new ProgressBar(hero.getCurrentHp()/(double) hero.getMaxHp());
		hpBar.setMaxWidth(Double.MAX_VALUE);


		Label attackDamageLabel = new Label("Attack Damage: " + hero.getAttackDmg());
		attackDamageLabel.setFont(Font.font("Agency FB", FontWeight.BOLD,  20));
		Label actionsLeftLabel = new Label("Actions Left: " + hero.getActionsAvailable());
		actionsLeftLabel.setFont(Font.font("Agency FB", FontWeight.BOLD,  20));
		Label vaccineLabel = new Label("Vaccines: " + hero.getVaccineInventory().size());
		vaccineLabel.setFont(Font.font("Agency FB", FontWeight.BOLD,  20));
		Label supplyLabel = new Label("Supplies: " + hero.getSupplyInventory().size());
		supplyLabel.setFont(Font.font("Agency FB", FontWeight.BOLD,  20));

		String type = "";
		if(hero instanceof Fighter)
			type = "Fighter" ;
		if(hero instanceof Explorer)
			type = "Explorer";
		if(hero instanceof Medic)
			type = "Medic";
		Label typeLabel = new Label("Type: " + type);
		typeLabel.setFont(Font.font("Agency FB", FontWeight.BOLD,  20));

		VBox characterBox = new VBox();
		characterBox.setSpacing(10);
		characterBox.setPadding(new Insets(10));

		HBox topRow = new HBox(heroIcon, nameLabel);
		topRow.setSpacing(10);
		topRow.setFillHeight(true);
		HBox.setHgrow(nameLabel, Priority.ALWAYS);

		Color backgroundColor = new Color(0.5, 0.5, 0.5, 0.5);
		BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(backgroundFill);

		characterBox.setBackground(background);
		if(hero == GameGridController.getHeroSelected()) {
			characterBox.getChildren().addAll(topRow, hpBar, attackDamageLabel, actionsLeftLabel, typeLabel, vaccineLabel, supplyLabel);
			characterBox.setMinWidth(200);
			characterBox.setMinHeight(150);
			characterBox.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}

		else {
			characterBox.getChildren().addAll(topRow, hpBar, attackDamageLabel, actionsLeftLabel, typeLabel);
			characterBox.setMinWidth(200);
			characterBox.setMinHeight(150);
			characterBox.setBorder(null);
		}
		

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
			
		for (int i = 0; i < 15; i++) 
			for (int j = 0; j < 15; j++) 
				renderCell(14 - i, j, mapForPrint[i][j]);
		
		
		Hero heroSelected = GameGridController.getHeroSelected();
		Point loc = heroSelected.getLocation();
		getRectangle(14 - loc.x, loc.y).setStroke(Color.GREEN);
		getRectangle(14 - loc.x, loc.y).setStrokeWidth(2.5);
		
		Character targetSelected = GameGridController.getTargetSelected();
		if (targetSelected != null) {
			loc = targetSelected.getLocation();
			getRectangle(14 - loc.x, loc.y).setStroke(Color.RED);
			getRectangle(14 - loc.x, loc.y).setStrokeWidth(2.5);
		}
		
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
		
		getRectangle(i, j).setStroke(Color.WHITE);
		getRectangle(i, j).setStrokeWidth(1);
		
	}

	
	
	public void printException(Exception e) {
		
		
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(e.getMessage());
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(stage);
		alert.show();
	
	}
	
	
	public void playTrapAnimation() {
		
		// TODO make trap animation
		
	}
	
	
	public static Rectangle getRectangle(int row, int col) {
		for (javafx.scene.Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
				return (Rectangle) node;
		}
		return null;
	}
	
}

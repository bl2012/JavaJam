package source;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
//import javafx.scene.image.ImageView;
//import javafx.scene.image.Image;
//import javafx.scene.control.ContentDisplay;
//import java.util.Random;
//import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.shape.*;
import java.util.LinkedList;


/**
* This is the UI for the InfiniTower game.
* @author Scott Simmons
* @version 1.0
**/

public class InfiniTower extends Application {

	Player player;
	BorderPane primaryPane;
	Label roomInfo;
	
	public void start(Stage primaryStage) {

		player = new Player();
		//The topmost pane where all subsequent panes will go
		primaryPane = new BorderPane();

		// this is just a little square that goes in the center of the 
		// "map" display

		
		roomInfo = new Label();
		primaryPane.setTop(roomInfo);
		roomInfo.setText("Ye find yeself in yon tower.");

		updateMap(player.getCurrentFloor());
		//********************Player Actions**************************
		GridPane playerActions = new GridPane();
		Button climbUp = new Button("Climb Up");
		Button climbDown = new Button("Climb Down");
		Button look = new Button("Look");
		GridPane.setConstraints(climbUp, 0, 0);
		GridPane.setConstraints(climbDown, 0, 1);
		GridPane.setConstraints(look, 0, 2);
		playerActions.getChildren().addAll(climbUp, climbDown, look);
		BorderPane.setAlignment(playerActions, Pos.CENTER);
		primaryPane.setLeft(playerActions);
		
		climbUp.setOnAction(e -> {
			player.ClimbUp();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		climbUp.setOnAction(e -> {
			player.ClimbDown();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		climbUp.setOnAction(e -> {
			player.Look();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		
		//**********************Attack/Flee ******************************
		Button attackBtn = new Button("ATTACK!");
		GridPane.setConstraints(attackBtn, 0, 3);
		playerActions.getChildren().add(attackBtn);
		attackBtn.setOnAction(e -> {
			player.Attack();
			updateDescription(player.getCurrentFloor());
		});
		Button fleeBtn = new Button("FLEE!");
		GridPane.setConstraints(fleeBtn, 0, 4);
		playerActions.getChildren().add(fleeBtn);
		fleeBtn.setOnAction(e -> {
			player.Flee();
			updateDescription(player.getCurrentFloor());
		});
		
		
		// this "compass" pane holds the compass buttons that are stored in 
		// a gridPane
		//*****************************Directional Buttons***************************
		VBox compass = new VBox();
		GridPane compassBtns = new GridPane();
		Button northBtn = new Button("North");
		Button southBtn = new Button("South");
		Button eastBtn = new Button("East");
		Button westBtn = new Button("West");
		GridPane.setConstraints(northBtn, 1, 0);
		GridPane.setConstraints(southBtn, 1, 2);
		GridPane.setConstraints(eastBtn, 2, 1);
		GridPane.setConstraints(westBtn, 0, 1);
		compassBtns.getChildren().addAll(northBtn, southBtn, eastBtn, westBtn);
		//compass.setCenter(compassBtns);
		BorderPane.setAlignment(compass, Pos.CENTER);
		BorderPane.setMargin(compass, new Insets(0,0,0,200));
		compass.getChildren().add(compassBtns);

		primaryPane.setBottom(compass);

		northBtn.setOnAction(e -> {
			player.GoNorth();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		southBtn.setOnAction(e -> {
			player.GoSouth();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		eastBtn.setOnAction(e -> {
			player.GoEast();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		westBtn.setOnAction(e -> {
			player.GoWest();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		climbUp.setOnAction(e -> {
			player.ClimbUp();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		climbDown.setOnAction(e -> {
			player.ClimbDown();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		look.setOnAction(e -> {
			player.Look();
			updateDescription(player.getCurrentFloor());
		});

		Scene scene = new Scene(primaryPane, 500, 500);	
		
		primaryStage.setTitle("InfiniTower");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void updateMap(Floor f) {
		
		int currXCoord = player.getCurrentCoord().getX();
		int currYCoord = player.getCurrentCoord().getY();
		LinkedList<Room> rooms = f.getRooms();
		Pane map = new Pane();
		for (int i = rooms.size()-1; i >= 0; i--) {
			Room room = rooms.get(i);
			String type = room.getType();
			int roomXCoord = room.getCoord().getX();
			int roomYCoord = room.getCoord().getY();
			int relXCoord = roomXCoord - currXCoord;
			int relYCoord = roomYCoord - currYCoord;
			//Rectangle r = new Rectangle();
			Rectangle mapSquare = new Rectangle();
			mapSquare.setStrokeWidth(2);
			
			
			if(type.equals("ladder")) {
				mapSquare.setStroke(Color.GREEN);
			} else if(type.equals("enemy")) {
				mapSquare.setStroke(Color.RED);
			} else {
				mapSquare.setFill(Color.BLACK);
				mapSquare.setStroke(Color.BLACK);
			}
			if(i == 0) {
				mapSquare.setFill(Color.BLUE);
			}
			
			mapSquare.setWidth(20);
			mapSquare.setHeight(20);
			mapSquare.setX(190 + relXCoord * 23);
			mapSquare.setY(200 +relYCoord * 23);
			//System.out.println("Square: " + relXCoord + "," + relYCoord + " created");
			if(Math.abs(relYCoord)<=8 && Math.abs(relXCoord)<=8){
				map.getChildren().addAll(mapSquare);
				//primaryPane.setCenter(map);
			}
		}
		primaryPane.setCenter(map);
	}
	public void updateDescription(Floor f) {
		roomInfo.setText(f.getCurrentRoom().getDescription());
	}
}
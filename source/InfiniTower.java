package source;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.PrimaryPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.shape.*;
import java.util.LinkedList;


public class InfiniTower extends Application {

	Player player;
	PrimaryPane primaryPane;
		
	public void start(Stage primaryStage) {

		player = new Player();
		
		//The topmost pane where all subsequent panes will go
		primaryPane = new PrimaryPane();

		// this is just a little square that goes in the center of the 
		// "map" display
		updateMap(player.getCurrentFloor());
		
		//********************Player Actions**************************

		primaryPane.getPlayerActions().getClimbUp().setOnAction(e -> {
			player.Climb("up");
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		primaryPane.getPlayerActions().getClimbDown().setOnAction(e -> {
			player.Climb("down");
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		primaryPane.getPlayerActions().getLook().setOnAction(e -> {
			player.Look();
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		
		//**********************Attack/Flee ******************************
		
		primaryPane.getPlayerActions().getAttackBtn().setOnAction(e -> {
			player.Combat("attack");
			updateDescription(player.getCurrentFloor());
		});

		primaryPane.getPlayerActions().getFleeBtn().setOnAction(e -> {
			player.Combat("flee");
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		
		//*****************************Directional Buttons***************************

		primaryPane.getCompass().getNorthBtn().setOnAction(e -> {
			player.GoDir("north");
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		primaryPane.getCompass().getSouthBtn().setOnAction(e -> {
			player.GoDir("south");
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		primaryPane.getCompass().getEastBtn().setOnAction(e -> {
			player.GoDir("east");
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});
		primaryPane.getCompass().getWestBtn().setOnAction(e -> {
			player.GoDir("west");
			updateMap(player.getCurrentFloor());
			updateDescription(player.getCurrentFloor());
		});

		Scene scene = new Scene(primaryPane, 525, 525);	
		
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
			mapSquare.setStrokeWidth(3);
			
			
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
			mapSquare.setX(190 + relXCoord * 25);
			mapSquare.setY(200 +relYCoord * 25);
			//System.out.println("Square: " + relXCoord + "," + relYCoord + " created");
			if(Math.abs(relYCoord)<=8 && Math.abs(relXCoord)<=8){
				map.getChildren().addAll(mapSquare);
				//primaryPane.setCenter(map);
			}
		}
		primaryPane.setCenter(map);
	}
	public void updateDescription(Floor f) {
		primaryPane.getRoomInfo().setText(f.getCurrentRoom().getDescription());
	}
}
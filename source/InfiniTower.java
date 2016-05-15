package source;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
//import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	public void start(Stage primaryStage) {

		player = new Player();
		//The topmost pane where all subsequent panes will go
		primaryPane = new BorderPane();

		// this is just a little square that goes in the center of the 
		// "map" display

		Rectangle centerSquare = new Rectangle();
		//centerSquare.setX(200);
		//centerSquare.setY(200);
		centerSquare.setWidth(20);
		centerSquare.setHeight(20);
		primaryPane.setCenter(centerSquare);

		updateMap(player.getCurrentFloor());

		// this "compass" pane holds the compass buttons that are stored in 
		// a gridPane
		BorderPane compass = new BorderPane();
		GridPane compassBtns = new GridPane();
		Button northBtn = new Button("North");
		Button southBtn = new Button("South");
		Button eastBtn = new Button("East");
		Button westBtn = new Button("West");
		compassBtns.setConstraints(northBtn, 1, 0);
		compassBtns.setConstraints(southBtn, 1, 2);
		compassBtns.setConstraints(eastBtn, 2, 1);
		compassBtns.setConstraints(westBtn, 0, 1);
		compassBtns.getChildren().addAll(northBtn, southBtn, eastBtn, westBtn);
		compass.setCenter(compassBtns);

		primaryPane.setBottom(compass);

		northBtn.setOnAction(e -> {
			player.GoNorth();
			updateMap(player.getCurrentFloor());
		});
		southBtn.setOnAction(e -> {
			player.GoSouth();
			updateMap(player.getCurrentFloor());
		});
		eastBtn.setOnAction(e -> {
			player.GoEast();
			updateMap(player.getCurrentFloor());
		});
		westBtn.setOnAction(e -> {
			player.GoWest();
			updateMap(player.getCurrentFloor());
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
		//int roomNum = 0;
		int currXCoord = player.getCurrentCoord().getX();
		int currYCoord = player.getCurrentCoord().getY();
		LinkedList<Room> rooms = f.getRooms();
		Pane map = new Pane();
		for (int i = rooms.size()-1; i >= 0; i--) {
			Room room = rooms.get(i);

			int roomXCoord = room.getCoord().getX();
			int roomYCoord = room.getCoord().getY();
			int relXCoord = roomXCoord - currXCoord;
			int relYCoord = roomYCoord - currYCoord;
			//Rectangle r = new Rectangle();
			Rectangle mapSquare = new Rectangle();
			
			if(i == 0) {
				mapSquare.setFill(Color.BLUE);
			}
			
			mapSquare.setWidth(20);
			mapSquare.setHeight(20);
			mapSquare.setX(250 + relXCoord * 21);
			mapSquare.setY(250 +relYCoord * 21);
			//System.out.println("Square: " + relXCoord + "," + relYCoord + " created");
			map.getChildren().addAll(mapSquare);
			//primaryPane.setCenter(map);
		}
		primaryPane.setCenter(map);
	}
}
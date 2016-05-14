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


/**
* This is the UI for the InfiniTower game.
* @author Scott Simmons
* @version 1.0
**/

public class InfiniTower extends Application {

	public void start(Stage primaryStage) {

		//The topmost pane where all subsequent panes will go
		BorderPane primaryPane = new BorderPane();

		// this is just a little square that goes in the center of the 
		// "map" display
		Rectangle centerSquare = new Rectangle();
		//centerSquare.setX(200);
		//centerSquare.setY(200);
		centerSquare.setWidth(20);
		centerSquare.setHeight(20);
		primaryPane.setCenter(centerSquare);

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

		Scene scene = new Scene(primaryPane, 500, 500);	
		
		primaryStage.setTitle("InfiniTower");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
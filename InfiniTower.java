import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

/**
* This is the UI for the InfiniTower game.
* @author Scott Simmons
* @version 1.0
**/

public class InfiniTower extends Application {

	public void start(Stage primaryStage) {

		BorderPane primaryPane = new BorderPane();


		Scene scene = new Scene(primaryPane);	
		primaryStage.setTitle("InfiniTower");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PrimaryPane extends BorderPane{
	
	private PlayerActions playerActions;
	private Compass compass;
	private Label roomInfo;
	
	public PrimaryPane()
	{
		playerActions = new PlayerActions();
		setLeft(playerActions);
		setAlignment(playerActions, Pos.CENTER);
		
		compass = new Compass();
		setBottom(compass);
		setAlignment(compass, Pos.CENTER);
		setMargin(compass, new Insets(0,0,0,200));
		getCompass().getChildren().add(getCompass().getCompassBtns());
		
		roomInfo = new Label();
		roomInfo.setWrapText(true);
		roomInfo.setText("Ye find yeself in yon tower.");

		setTop(roomInfo);
	}
	
	// getters and setters
	
	public PlayerActions getPlayerActions() {
		return playerActions;
	}

	public void setPlayerActions(PlayerActions playerActions) {
		this.playerActions = playerActions;
	}

	public Compass getCompass() {
		return compass;
	}

	public void setCompass(Compass compass) {
		this.compass = compass;
	}
	
	public Label getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(Label roomInfo) {
		this.roomInfo = roomInfo;
	}
}

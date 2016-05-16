package ui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Compass extends VBox{

	// this "compass" pane holds the compass buttons that are stored in 
	// a gridPane
	
	private GridPane compassBtns;
	private Button northBtn;
	private Button southBtn;
	private Button eastBtn;
	private Button westBtn;
	
	public Compass()
	{
		compassBtns = new GridPane();
		
		northBtn = new Button("North");
		southBtn = new Button("South");
		eastBtn = new Button("East");
		westBtn = new Button("West");
		
		GridPane.setConstraints(northBtn, 1, 0);
		GridPane.setConstraints(southBtn, 1, 2);
		GridPane.setConstraints(eastBtn, 2, 1);
		GridPane.setConstraints(westBtn, 0, 1);
		
		compassBtns.getChildren().addAll(northBtn, southBtn, eastBtn, westBtn);

	}
	
	public GridPane getCompassBtns() {
		return compassBtns;
	}

	public void setCompassBtns(GridPane compassBtns) {
		this.compassBtns = compassBtns;
	}

	public Button getNorthBtn() {
		return northBtn;
	}

	public void setNorthBtn(Button northBtn) {
		this.northBtn = northBtn;
	}

	public Button getSouthBtn() {
		return southBtn;
	}

	public void setSouthBtn(Button southBtn) {
		this.southBtn = southBtn;
	}

	public Button getEastBtn() {
		return eastBtn;
	}

	public void setEastBtn(Button eastBtn) {
		this.eastBtn = eastBtn;
	}

	public Button getWestBtn() {
		return westBtn;
	}

	public void setWestBtn(Button westBtn) {
		this.westBtn = westBtn;
	}
}

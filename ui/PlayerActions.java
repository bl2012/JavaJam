package ui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PlayerActions extends GridPane{

	private Button climbUp = new Button("Climb Up");
	private Button climbDown = new Button("Climb Down");
	private Button look = new Button("Look");
	private Button attackBtn = new Button("Attack");
	private Button fleeBtn = new Button("FLEE!");
	
	public PlayerActions()
	{
		
		add(climbUp, 0, 0);
		add(climbDown, 0, 1);
		add(look, 0, 2);
		add(attackBtn, 0, 3);
		add(fleeBtn, 0, 4);
		
		setConstraints(climbUp, 0, 0);
		setConstraints(climbDown, 0, 1);
		setConstraints(look, 0, 2);
	}
	
	public Button getClimbUp() {
		return climbUp;
	}

	public void setClimbUp(Button climbUp) {
		this.climbUp = climbUp;
	}

	public Button getClimbDown() {
		return climbDown;
	}

	public void setClimbDown(Button climbDown) {
		this.climbDown = climbDown;
	}

	public Button getLook() {
		return look;
	}

	public void setLook(Button look) {
		this.look = look;
	}

	public Button getAttackBtn() {
		return attackBtn;
	}

	public void setAttackBtn(Button attackBtn) {
		this.attackBtn = attackBtn;
	}

	public Button getFleeBtn() {
		return fleeBtn;
	}

	public void setFleeBtn(Button fleeBtn) {
		this.fleeBtn = fleeBtn;
	}
	
}

package source;

import java.util.LinkedList;

public class Player {

	private Coordinates currentCoord;
	private Floor currentFloor;
	private LinkedList<Floor> floors;
	private int numFloors;
	private int maxHP;
	private int currentHP;
	
	// when player is created, they are put on a new floor
	public Player()
	{
		maxHP = 100;
		currentHP = maxHP;
		numFloors = 0;
		floors = new LinkedList<Floor>();
		currentFloor = new Floor(numFloors);
		floors.addFirst(currentFloor);
		currentCoord = currentFloor.getCurrentRoom().coord;
	}
	
	// This series of methods control directional movement
	
	public void GoDir(String dir)
	{		
		// reset the action-specific text
		currentFloor.getCurrentRoom().setOtherDescription("");
		
		if(currentFloor.getCurrentRoom().getType() == "enemy" && !currentFloor.getCurrentRoom().getEnemy().isDead())
		{
			ButtonsLocked("dir");
			return;
		}
		
		switch(dir)
		{
		case "north":
			GoNorth();
			break;
		case "south":
			GoSouth();
			break;
		case "east":
			GoEast();
			break;
		case "west":
			GoWest();
			break;
		default:
			break;
		}
		
		EnviroUpdate();
	}
	
	public void GoNorth()
	{
		if(currentFloor.getCurrentRoom().north == null)
			currentFloor.CreateRandomRoom("north");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().north);
		}
		
		System.out.println("Move North	");
	}
	
	public void GoSouth()
	{
		if(currentFloor.getCurrentRoom().south == null)
			currentFloor.CreateRandomRoom("south");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().south);
		}
		
		System.out.println("Move South	");
	}
	
	public void GoEast()
	{
		if(currentFloor.getCurrentRoom().east == null)
			currentFloor.CreateRandomRoom("east");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().east);
		}
		
		System.out.println("Move East	");
	}
	
	public void GoWest()
	{
		if(currentFloor.getCurrentRoom().west == null)
			currentFloor.CreateRandomRoom("west");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().west);
		}
		
		System.out.println("Move West	");
	}
	
	// these methods move the player between floors
	public void Climb(String dir)
	{		
		// reset the action-specific text
		currentFloor.getCurrentRoom().setOtherDescription("");
		
		if(currentFloor.getCurrentRoom().getType() != "ladder")
		{
			ButtonsLocked("climb");
			return;
		}
		
		switch(dir)
		{
		case "up":
			ClimbUp();
			break;
		case "down":
			ClimbDown();
			break;
		default:
			break;
		}	
		
		EnviroUpdate();
	}
	
	public void ClimbUp() // a new floor is created
	{
		
		if(!currentFloor.getCurrentRoom().getLadderGoesUp())
		{
			return;
		}
		
		if(currentFloor.getFloorAbove() == null)
		{
			Floor newFloor = new Floor(++numFloors);
			
			currentFloor.setFloorAbove(newFloor);
			newFloor.setFloorBelow(currentFloor);
			
			currentFloor = newFloor;
			floors.addFirst(newFloor);
			System.out.print("New ");
		}
		else
		{
			currentFloor = currentFloor.getFloorAbove();
		}

	}
	
	public void ClimbDown() // the player moves down to the previous floor
	{
		if(!currentFloor.getCurrentRoom().getLadderGoesDown())
		{
			return;
		}
		
		if(currentFloor.getFloorBelow() == null)
			System.out.println("You can't climb down. That floor doesn't exist");
		else
			currentFloor = currentFloor.getFloorBelow();
		
	}
	
	// these methods control actions in combat
	
	public void Combat(String action)
	{		
		// reset the action-specific text
		currentFloor.getCurrentRoom().setOtherDescription("");
		String message = "";
		
		if(currentFloor.getCurrentRoom().getType() != "enemy")
		{
			ButtonsLocked("combat");
			return;
		}
		
		switch(action)
		{
		case "attack":
			if(currentFloor.getCurrentRoom().getEnemy().isDead())
			{
				message = new String("You attack the enemy's lifeless body. "
						+ "There is a sickening squish as your sword makes contact with so much dead tissue. ");
			}
			else{
				Attack(100);
				String tempStr = (currentFloor.getCurrentRoom().getEnemy().isDead()) ? "It slumps to the ground, lifeless. " : "It recovers"
						+ "and prepares to attack. ";
				message = new String("You attack the enemy. " + tempStr);
				
			}
			
			currentFloor.getCurrentRoom().setOverrideDisplayed(false);
			
			break;
		case "flee":
			if(currentFloor.getCurrentRoom().getEnemy().isDead())
			{
				message = new String("The enemy is dead. There is no need to run away.");
				currentFloor.getCurrentRoom().setOverrideDisplayed(false);

			}
			else{
				Flee();
				message = new String("You run away. ");
				EnviroUpdate();
			}
			break;
		default:
			break;
		}	
		
		currentFloor.getCurrentRoom().setOtherDescription(message);
	}
	
	public void Attack(int dmg)
	{
		Enemy enemy = null;
				
		if(currentFloor.getCurrentRoom().getType() == "enemy")
		{
			enemy = currentFloor.getCurrentRoom().getEnemy();
		}
		else return;
		
		if(enemy.isDead()) return;

		
		enemy.setHp(enemy.getHp() - dmg);
		if(enemy.getHp() <= 0) enemy.setDead(true);
		
	}
	
	public void Flee() {
		currentFloor.setCurrentRoom(currentFloor.getRooms().get(1));		
	}
	
	// these methods are called within a room
	
	public void Look()
	{
		
	}
	
	// this method is called after any room change
	
	public void EnviroUpdate()
	{			
		currentFloor.getRooms().remove(currentFloor.getCurrentRoom());
		currentFloor.getRooms().addFirst(currentFloor.getCurrentRoom());
		currentCoord = currentFloor.getCurrentRoom().coord;

		
		System.out.print("Floor: " + currentFloor.getFloorNum() + "	");
		System.out.println("Room : " + currentFloor.getCurrentRoom().getCoord().getX() + ", " + currentFloor.getCurrentRoom().getCoord().getY());
	}
	
	public void ButtonsLocked(String buttonType)
	{		
		String message = "";
		
		switch(buttonType)
		{
		case "dir":
			currentFloor.getCurrentRoom().getEnemy().Attack(this);
			message = new String("The enemy attacks. You have " + getHp() + " health remaining. ");
			break;
		case "climb":
			message = new String("There isn't a ladder in this room. ");
			break;
		case "combat":
			message = new String("There is no danger here. ");
		default:
			break;
		}
		
		currentFloor.getCurrentRoom().setOtherDescription(message);
	}
	
	// getters and setters
	
	public int getHp() {
		return currentHP;
	}

	public void setHp(int hp) {
		this.currentHP = hp;
	}
	
	public void Heal()
	{
		currentHP = maxHP;
	}
	
	public Coordinates getCurrentCoord() {
		return currentCoord;
	}

	public Floor getCurrentFloor() {
		return currentFloor;
	}

	public LinkedList<Floor> getFloors() {
		return floors;
	}
}

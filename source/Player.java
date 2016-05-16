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
		numFloors = 0;
		floors = new LinkedList<Floor>();
		currentFloor = new Floor(numFloors);
		floors.addFirst(currentFloor);
		currentCoord = currentFloor.getCurrentRoom().coord;
	}
	
	// This series of methods control directional movement
	
	public void GoNorth()
	{
		if(currentFloor.getCurrentRoom().north == null)
			currentFloor.CreateRandomRoom("north");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().north);
		}
		
		System.out.print("Move North	");
		EnviroUpdate();
	}
	
	public void GoSouth()
	{
		if(currentFloor.getCurrentRoom().south == null)
			currentFloor.CreateRandomRoom("south");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().south);
		}
		
		System.out.print("Move South	");
		EnviroUpdate();
	}
	
	public void GoEast()
	{
		if(currentFloor.getCurrentRoom().east == null)
			currentFloor.CreateRandomRoom("east");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().east);
		}
		
		System.out.print("Move East	");
		EnviroUpdate();
	}
	
	public void GoWest()
	{
		if(currentFloor.getCurrentRoom().west == null)
			currentFloor.CreateRandomRoom("west");
		else
		{
			currentFloor.setCurrentRoom(currentFloor.getCurrentRoom().west);
		}
		
		System.out.print("Move West	");
		EnviroUpdate();
	}
	
	// these methods move the player between floors
	
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

		EnviroUpdate();
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
		
		EnviroUpdate();
	}
	
	// these methods control actions within a room
	
	public void Attack(int dmg)
	{
		Enemy enemy = null;
				
		if(currentFloor.getCurrentRoom().getType() == "enemy")
		{
			enemy = currentFloor.getCurrentRoom().getEnemy();
		}
		else return;
		
		if(enemy.dead) return;

		enemy.setHp(enemy.getHp() - dmg);
		if(enemy.getHp() <= 0) enemy.dead = true;
		
	}
	
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

	public void Flee() {
		// TODO Auto-generated method stub
		
	}
}

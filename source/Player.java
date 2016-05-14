package source;

import java.util.LinkedList;

public class Player {

	private Coordinates currentCoord;
	private Floor currentFloor;
	private LinkedList<Floor> floors;
	private int numFloors;
	
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
		currentFloor.CreateRandomRoom("north");
		currentCoord = currentFloor.getCurrentRoom().coord;
		System.out.println("Move North");
	}
	
	public void GoSouth()
	{
		currentFloor.CreateRandomRoom("south");
		currentCoord = currentFloor.getCurrentRoom().coord;
		System.out.println("Move South");

	}
	
	public void GoEast()
	{
		currentFloor.CreateRandomRoom("east");
		currentCoord = currentFloor.getCurrentRoom().coord;
		System.out.println("Move East");

	}
	
	public void GoWest()
	{
		currentFloor.CreateRandomRoom("west");
		currentCoord = currentFloor.getCurrentRoom().coord;
		System.out.println("Move West");

	}
	
	// these methods move the player between floors
	
	public void ClimbUp() // a new floor is created
	{
		Floor newFloor = new Floor(++numFloors);
		currentFloor = newFloor;
		floors.addFirst(newFloor);
	}
	
	public void ClimbDown() // the player moves down to the previous floor
	{
		Floor newFloor = floors.get(1);
		floors.remove(newFloor);
		floors.addFirst(newFloor); // move this floor to the front of the linked list
		currentFloor = newFloor;
	}
	
	// these methods control actions within a room
	
	public void Attack()
	{
		
		
	}
	
	public void Look()
	{
		
	}
	
	// this method is called after any room change
	
	public void EnviroUpdate()
	{
		System.out.println("Floor: " + currentFloor.getFloorNum());
		System.out.println("Room : " + currentFloor.getCurrentRoom().getCoord().getX() + ", " + currentFloor.getCurrentRoom().getCoord().getY());
	}
	
	// getters 
	
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

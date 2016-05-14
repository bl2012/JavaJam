package source;

import java.util.LinkedList;

public class Player {

	private Coordinates currentCoord;
	private Floor currentFloor;
	private LinkedList<Floor> floors;
	
	public Player()
	{
		floors = new LinkedList<Floor>();
		currentFloor = new Floor();
		floors.addFirst(currentFloor);
		currentCoord = currentFloor.currentRoom.coord;
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

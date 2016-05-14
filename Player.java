package source;

public class Player {

	public Coordinates currentCoord;
	public Floor currentFloor;
	
	public Player()
	{
		currentFloor = new Floor();
		currentCoord = currentFloor.currentRoom.coord;
	}
}

package source;

public class EmptyRoom extends Room{

	public EmptyRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);
		
		newRoomDescription = "This room is eerily empty";
		visitedDescription = "The eerie emptiness of this room is awfully familiar";
	}

}

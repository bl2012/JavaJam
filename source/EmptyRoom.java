package source;

public class EmptyRoom extends Room{

	public EmptyRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);
		
		newRoomDescription = "This room is eerily empty. ";
		visitedDescription = "The eerie emptiness of this room is awfully familiar. ";
		type = "empty";
	}

	// getters and setters
	
	public String getDescription() {
		if(bIsVisited) return (new String(otherDescription + visitedDescription));
		else 		   return (new String(newRoomDescription + otherDescription));
	}
	
	public void setOtherDescription(String str)
	{
		this.otherDescription = str;
	}
	
}

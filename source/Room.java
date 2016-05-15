package source;

public abstract class Room{
	
	// pointers to the next room
	Room north;
	Room south;
	Room east;
	Room west;
	
	// booleans for checking various states
	boolean bIsVisited;
	boolean bHasUpLadder;
	boolean bHasDownLadder;
	
	// the coordinates for each room
	Coordinates coord;
	
	// description for the room
	String type;
	String newRoomDescription;
	String visitedDescription;
	
	// constructor
	public Room(int prevX, int prevY, String dir, Floor floor)
	{	
		// set the new coordinates
		coord = (setCoordinates(prevX, prevY, dir));
		
		// visited will be set to true when the player leaves the room.
		// this allows us to check if the room has previously been visited
		// thus eliminating duplication of rooms at the same coordinates
		bIsVisited = false;
		
		// each room should have a description, describing it to the player
		newRoomDescription = "This room is dank, yo.";
		visitedDescription = "This room is familiar.";
		
		// set the type to default. This is be overwritten in subconstructors
		type = "default";
		
		if(floor.GetPreviousRoom() != null)
		{
			//System.out.println("Room " + floor.GetPreviousRoom().getCoord().getX() + ", " + floor.GetPreviousRoom().getCoord().getY() + " has been visited");
			floor.GetPreviousRoom().bIsVisited = true;
		}
		
		// new rooms start with null pointers...
		north = null;
		south = null;
		east = null;
		west = null;
				
		// except in the direction the player has come from
		if(dir == null) return;
		
		switch(dir)
		{
		case "north":
			south = floor.GetPreviousRoom();
			break;
		case "south":
			north = floor.GetPreviousRoom();
			break;
		case "east":
			west = floor.GetPreviousRoom();
			break;
		case "west":
			east = floor.GetPreviousRoom();
			break;
		}

	}
	
	Coordinates setCoordinates(int prevX, int prevY, String dir)
	{
		if(dir == null) {
			return new Coordinates(0, 0);
		}
		
		switch(dir)
		{
		case "north":
			return new Coordinates(prevX, prevY - 1);
		case "south":
			return new Coordinates(prevX, prevY + 1);
		case "east":
			return new Coordinates(prevX + 1, prevY);
		case "west":
			return new Coordinates(prevX - 1, prevY);
		default:
			return null;
		}
	}
	
	boolean isEqual(Room otherRoom)
	{
		if(otherRoom.getCoord().getX() == this.coord.getX() && otherRoom.getCoord().getY() == this.coord.getY())
		{
			System.out.print("Room " + otherRoom.getCoord().getX() + ", " + otherRoom.getCoord().getY() + " = ");
			System.out.println("Room " + this.coord.getX() + ", " + this.coord.getY());

			return true;
		}
		else 
		{
			return false;
		}
	}
		
	Coordinates getCoord()
	{
		return coord;
	}
	
	boolean isVisited()
	{
		return bIsVisited;
	}
	
	boolean hasUpLadder()
	{
		return bHasUpLadder;
	}
	
	boolean hasDownLadder()
	{
		return bHasDownLadder;
	}

	public String getDescription() {
		if(bIsVisited) return visitedDescription;
		else 		   return newRoomDescription;
	}
	
	public String getType() {
		return type;
	}
}



package source;

import java.util.LinkedList;

public class Floor {
	
	public Room currentRoom;
	private LinkedList<Room> rooms = new LinkedList<Room>();
	private String[] roomTypes = new String[] {"LadderRoom", "EnemyRoom", "EmptyRoom"};
	
	public Floor()
	{
		currentRoom = CreateRoom("EmptyRoom", null);
	}
	
	public Room CreateRandomRoom(String dir)
	{
		int randRoom = 0;
		
		return CreateRoom(roomTypes[randRoom], dir);
	}
	
	public Room CreateRoom(String roomType, String dir)
	{
		Room ret = null;
		Room oldRoom = FindRoom(dir);	// check to see if a room already exists at these coordinates
		
		if(oldRoom != null)
		{
			rooms.remove(oldRoom);
			rooms.addFirst(oldRoom); 	// move the room to the front of the LinkedList
			return oldRoom;
		}
		
		switch(roomType)
		{
		case "LadderRoom":
			ret = new LadderRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		case "EnemyRoom":
			ret = new EnemyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		case "EmptyRoom":
			ret = new EmptyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		}
		
		rooms.addFirst(ret);
		return ret;
	}
	
	public Room GetPreviousRoom()
	{
		return rooms.get(1);
	}
	
	public Room FindRoom(String dir)
	{
		EmptyRoom newRoom = new EmptyRoom(0, 0, null, null);
		newRoom.setCoordinates(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir);
		
		for(int i = 0; i < rooms.size(); i++)
		{
			if(currentRoom.isEqual(newRoom))
			{
				return rooms.get(i);
			}
		}
		
		return null;
	}
	
	public Room FindRoom(Room otherRoom)
	{
		// pretty inefficient, there's probably a better way to do this. 
		// Probably using the current room...
		for(int i = 0; i < rooms.size(); i++)
		{
			if(currentRoom.isEqual(otherRoom))
			{
				return rooms.get(i);
			}
		}
		
		return null;
	}
	
	
}

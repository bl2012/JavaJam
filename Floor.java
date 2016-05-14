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
		
		switch(roomType)
		{
		case "LadderRoom":
			ret = new LadderRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		case "EnemyRoom":
			ret = new EnemyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		case "EmptyRoom":
			ret = new EnemyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		}
		
		rooms.add(ret);
		return ret;
	}
	
	public Room FindRoom(int roomX, int roomY)
	{
		// pretty inefficient, there's probably a better way to do this. 
		// Probably using the current room...
		for(int i = 0; i < rooms.size(); i++)
		{
			if(rooms.get(i).getCoord().getX() == roomX && rooms.get(i).getCoord().getY() == roomY)
			{
				return rooms.get(i);
			}
		}
		
		return null;
	}
	
	
}

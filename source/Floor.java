package source;

import java.util.LinkedList;

public class Floor {
	
	private Room currentRoom;
	private int floorNum;
	private LinkedList<Room> rooms = new LinkedList<Room>();
	private String[] roomTypes = new String[] {"LadderRoom", "EnemyRoom", "EmptyRoom"};
	
	public Floor(int num)
	{
		if(num == 0) // if this is the first floor created, the initial room will be empty
		{
			currentRoom = CreateFirstRoom("EmptyRoom", null);
		}
		else // otherwise there will be a down ladder in the room
			currentRoom = CreateRoom("LadderRoom", null);
		floorNum = num;
	}
	
	// a room of a random type is created
	public Room CreateRandomRoom(String dir)
	{
		int randRoom = 0;
		
		return CreateRoom(roomTypes[randRoom], dir);
	}
	
	public Room CreateFirstRoom(String roomType, String dir) {
		Room ret = null;
		
		switch(roomType) // Factory Design-esque instantiation
		{
		case "LadderRoom":
			ret = new LadderRoom(0, 0, dir, this);
		case "EnemyRoom":
			ret = new EnemyRoom(0, 0, dir, this);
		case "EmptyRoom":
			ret = new EmptyRoom(0, 0, dir, this);
		}
		
		rooms.addFirst(ret);
		currentRoom = ret;
		return ret;
	}
	// if a room does not exist in the desired direction, a new one is created
	public Room CreateRoom(String roomType, String dir)
	{
		Room ret = null;
		Room oldRoom = FindRoom(dir);	// check to see if a room already exists at these coordinates
		
		if(oldRoom != null)
		{
			rooms.remove(oldRoom);
			rooms.addFirst(oldRoom); 	// move the room to the front of the LinkedList
			currentRoom = oldRoom;
			return oldRoom;
		}
		
		switch(roomType) // Factory Design-esque instantiation
		{
		case "LadderRoom":
			ret = new LadderRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		case "EnemyRoom":
			ret = new EnemyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		case "EmptyRoom":
			ret = new EmptyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
		}
		
		rooms.addFirst(ret);
		currentRoom = ret;
		return ret;
	}
	
	// search for a room that exists in the rooms linked list
	
	public Room FindRoom(String dir) // using a direction
	{
		EmptyRoom newRoom = new EmptyRoom(0, 0, dir, this);
		
		try {
		newRoom.setCoordinates(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir);
		}
		catch (NullPointerException itsNUllForSomeReason){
			System.out.println("it's null, yo");
		}
		for(int i = 0; i < rooms.size(); i++)
		{
			if(currentRoom.isEqual(newRoom))
			{
				return rooms.get(i);
			}
		}
		
		return null;
	}
	
	public Room FindRoom(Room otherRoom) // using a defined room
	{
		for(int i = 0; i < rooms.size(); i++)
		{
			if(currentRoom.isEqual(otherRoom))
			{
				return rooms.get(i);
			}
		}
		
		return null;
	}
	
	// getters
	
	public Room GetPreviousRoom()
	{
		if(rooms.size() <= 1) return null;
		
		return rooms.get(1);
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}

	public LinkedList<Room> getRooms() {
		return rooms;
	}

	public String[] getRoomTypes() {
		return roomTypes;
	}
	
	public int getFloorNum() {
		return floorNum;
	}
	
}

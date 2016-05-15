package source;

import java.util.LinkedList;
import java.util.Random;

public class Floor {
	
	private Room currentRoom;

	private Floor floorAbove;
	private Floor floorBelow;
	
	private int floorNum;
	private String[] roomTypes = new String[] {"LadderRoom", "EnemyRoom", "EmptyRoom"};

	private LinkedList<Room> rooms = new LinkedList<Room>();
	
	private boolean bHasLadderUp;
	
	Random rand = new Random();
	
	
	public Floor(int num)
	{
		if(num == 0) // if this is the first floor created, the initial room will be empty
		{
			currentRoom = CreateFirstRoom("EmptyRoom");
		}
		else // otherwise there will be a down ladder in the room
		{
			currentRoom = CreateFirstRoom("LadderRoom");
			currentRoom.setLadderUp(false);
		}
		
		bHasLadderUp = false;
		floorNum = num;
		floorAbove = null;
		floorBelow = null;
	}
	
	// a room of a random type is created
	public Room CreateRandomRoom(String dir)
	{
		int randRoom = getRandNum();
		
		while(bHasLadderUp && (roomTypes[randRoom] == "LadderRoom"))
		{
			randRoom = getRandNum();
		}
		
		return CreateRoom(roomTypes[randRoom], dir);
	}
	
	private int getRandNum()
	{
		switch(rand.nextInt(10))
		{
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 1;
		case 3:
			return 1;
		case 4:
			return 2;
		case 5:
			return 2;
		case 6:
			return 2;
		case 7:
			return 2;
		case 8:
			return 2;
		case 9:
			return 2;
		default:
			return 2;
		}
		
	}
	
	public Room CreateFirstRoom(String roomType) {
		Room ret = null;
		String dir = null;
		
		switch(roomType) // Factory Design-esque instantiation
		{
		case "LadderRoom":
			ret = new LadderRoom(0, 0, dir, this);
			break;
		case "EnemyRoom":
			ret = new EnemyRoom(0, 0, dir, this);
			break;
		case "EmptyRoom":
			ret = new EmptyRoom(0, 0, dir, this);
			break;
		}
		
		rooms.addFirst(ret);
		currentRoom = ret;
		return ret;
	}
	// if a room does not exist in the desired direction, a new one is created
	public Room CreateRoom(String roomType, String dir)
	{
		Room ret = null;
		
		switch(roomType) // Factory Design-esque instantiation
		{
		case "LadderRoom":
			ret = new LadderRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
			ret.setLadderUp(true);
			bHasLadderUp = true;
			break;
		case "EnemyRoom":
			ret = new EnemyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
			break;
		case "EmptyRoom":
			ret = new EmptyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);
			break;
		}
		
		rooms.addFirst(ret);
		currentRoom = ret;
		return ret;
	}
	
	// search for a room that exists in the rooms linked list
	
	public Room FindRoom(String dir) // using a direction
	{
		EmptyRoom newRoom = new EmptyRoom(currentRoom.getCoord().getX(), currentRoom.getCoord().getY(), dir, this);

		for(int i = 0; i < rooms.size(); i++)
		{
			if(rooms.get(i).isEqual(newRoom))
			{
				return rooms.get(i);
			}
		}
		
		return null;
	}
	
	public Room FindRoom(int x, int y) // using coordinates
	{
		for(int i = 0; i < rooms.size(); i++)
		{
			if(rooms.get(i).getCoord().getX() == x && rooms.get(i).getCoord().getY() == y)
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
			if(rooms.get(i).isEqual(otherRoom))
			{
				return rooms.get(i);
			}
		}
		
		return null;
	}
	
	// getters and setters
	
	public Floor getFloorAbove() {
		return floorAbove;
	}

	public void setFloorAbove(Floor floorAbove) {
		this.floorAbove = floorAbove;
	}

	public Floor getFloorBelow() {
		return floorBelow;
	}

	public void setFloorBelow(Floor floorBelow) {
		this.floorBelow = floorBelow;
	}
	
	public Room GetPreviousRoom() // called before the new room is added to the linked list
	{
		if(rooms.size() < 1)
		{
			System.out.println("	rooms.size() = " + rooms.size());
			return null;
		}
		
		return rooms.get(0);
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
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

package source;

public class EnemyRoom extends Room{

	public EnemyRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);

		newRoomDescription = "There's an enemy in this room";
		visitedDescription = "A dead enemy lay as flat and cold as the ground";
		type = "enemy";
	}

}

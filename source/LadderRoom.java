package source;

public class LadderRoom extends Room{

	public LadderRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);
		
		bHasUpLadder = (bHasDownLadder) ? false : true;
		newRoomDescription = "There's a ladder in this room";
		visitedDescription = "You've seen that ladder before";
		type = "ladder";
	}

}

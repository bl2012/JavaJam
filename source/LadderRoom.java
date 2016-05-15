package source;

public class LadderRoom extends Room{

	public LadderRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);
		
		bHasUpLadder = (bHasDownLadder) ? false : true;
		description = "There's a ladder in this room";
	}

}

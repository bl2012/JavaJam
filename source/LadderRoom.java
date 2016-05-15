package source;

public class LadderRoom extends Room{

	private boolean ladderUp;
	
	public LadderRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);
		
		newRoomDescription = "There's a ladder in this room";
		visitedDescription = "You've seen that ladder before";
		type = "ladder";
	}

	public boolean getLadderGoesUp() {
		if(ladderUp) return true;
		else return false;
	}
	
	public boolean getLadderGoesDown()
	{
		if(ladderUp) return false;
		else return true;
	}

	public void setLadderUp(boolean ladderGoesUp) {
		this.ladderUp = ladderGoesUp;
	}
}

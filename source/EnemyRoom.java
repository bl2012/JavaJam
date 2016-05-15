package source;

public class EnemyRoom extends Room{

	public EnemyRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);
		// TODO Auto-generated constructor stub
		description = "There's an enemy in this room";
	}

}

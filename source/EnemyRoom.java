package source;

import java.util.Random;

public class EnemyRoom extends Room{

	Enemy enemy;

	private Random rand = new Random();
	private String[] enemyTypes = new String[]{"bigEnemy", "smallEnemy"};
	
	public EnemyRoom(int prevX, int prevY, String dir, Floor floor) {
		super(prevX, prevY, dir, floor);

		enemy = RandEnemy();
		type = "enemy";

		
		newRoomDescription = "There's an enemy in this room. ";
		visitedDescription = "A dead enemy lay as flat and cold as the ground. ";
	}
	
	public String getDescription()
	{
		String typeText;
		String HPtext;
				
		typeText = enemy.getDescription();
		HPtext = " It has " + enemy.getHp() + " health. ";
		
		if(!bIsVisited) return new String(newRoomDescription + typeText + HPtext);
		else return new String(visitedDescription);
	}
	
	private Enemy RandEnemy()
	{
		float randNum = rand.nextFloat() * 100;
		String enemyType;
		
		if(randNum < 30)	// big enemy
		{
			enemyType = enemyTypes[0];
		}
		else 				// small enemy
		{
			enemyType = enemyTypes[1];
		}
		
		switch(enemyType) // Factory Design-esque instantiation
		{
		case "smallEnemy":
			return new SmallEnemy();
		case "bigEnemy":
			return new BigEnemy();
		default:
			return null;
		}
		
	}
	
	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
			

}

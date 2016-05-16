package source;

public class SmallEnemy extends Enemy{
	public SmallEnemy()
	{
		super();
		type = "small";
		hp = 25;
		dmg = 10;
		description = "A small warrior runs at you with a sword.";
	}
	
	public String getDescription()
	{
		return description;
	}
}

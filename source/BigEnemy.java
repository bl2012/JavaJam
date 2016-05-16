package source;

public class BigEnemy extends Enemy {
	public BigEnemy()
	{
		super();
		type = "big";
		hp = 75;
		dmg = 50;
		description = "A large warrior runs at you with a sword.";
	}
	
	public String getDescription()
	{
		return description;
	}
}

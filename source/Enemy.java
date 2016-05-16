package source;

public abstract class Enemy {

	int hp;
	String description;
	String type;
	boolean dead;
	
	public Enemy()
	{
		hp = 50;
		dead = false;
		description =  "Oh noes it's a bad guy!";
		type = "default";
	}
	
	void Attack(Player player, int dmg)
	{
		player.setHp(player.getHp() - dmg);
	}
	
	String getDescription() {
		return description;
	}
	
	int getHp() {
		return hp;
	}

	void setHp(int hp) {
		this.hp = hp;
	}
}

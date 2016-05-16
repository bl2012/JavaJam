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
	
	// getters and setters
	
	int getHp() {
		return hp;
	}

	void setHp(int hp) {
		this.hp = hp;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}


public class Character {
	// Attributes
    private int hitPoints;
    private int maxHitPoints;
    private int attackPoints;
    private int defense;
    private int speed;
    private boolean isAlive;
    
    // Constructor
    public Character (int maxHitPoints, int attackPoints, 
            int defense, int speed) {
    	this.maxHitPoints = maxHitPoints;
    	this.hitPoints = maxHitPoints;
    	this.attackPoints = attackPoints;
    	this.defense = defense;
    	this.speed = speed;
    	this.isAlive = true;

    }
    
    // Getters and setters
    public int getHitPoints() {
		return hitPoints;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}


	public int getDefense() {
		return defense;
	}


	public int getSpeed() {
		return speed;
	}


	public boolean isAlive() {
		return isAlive;
	}
	
	// Methods to change statistics
	public void takeDamage() {};

}
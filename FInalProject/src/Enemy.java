//import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
	// Attributes
    private String name = "";
    private int hitPoints;
    private int maxHitPoints;
    private int attackPoints;
    private int defense;
    private int speed;
    private int level;
    private int experienceReward; // XP given to Player when Enemy is defeated
    private boolean isAlive;
    private boolean isMagical;
    private int mana;
    private int maxMana;
    private int magicPower;
    private int magicResistance;
    private List<Spell> knownSpells;
    private Random random;
    
    /**
     * Note: In the future, use a switch that randomly assigns the type of enemy at creation.
     * For example, use a random number generator to generate a number between 1 and 3.
     * 
     * 1 = Vampire - is the only magical character
     * 2 = Zombie
     * 3 = Werewolf
     * 
     * To simplify the game I may remove all but one enemy character
     * 
     * These classes will inherit from Enemy.
     * Once I learn more about inheritance in Java I will create a base character class
     * for Enemy and Player.
     */

    // Constructor
    public Enemy(String name, int maxHitPoints, int attackPoints, 
            int defense, int speed, int experienceReward, boolean isMagical) {
    	this.name = name;
    	this.maxHitPoints = maxHitPoints;
    	this.hitPoints = maxHitPoints;
    	this.attackPoints = attackPoints;
    	this.defense = defense;
    	this.speed = speed;
    	this.experienceReward = experienceReward;
    	this.isMagical = isMagical;
    	this.maxMana = isMagical ? 50 : 0;
    	this.mana = this.maxMana;
    	this.magicPower = isMagical ? 10 : 0;
    	this.magicResistance = 5;
   
    	initializeSpells();
    }
    
    // getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperienceReward() {
		return experienceReward;
	}

	public void setExperienceReward(int experienceReward) {
		this.experienceReward = experienceReward;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isMagical() {
		return isMagical;
	}

	public void setMagical(boolean isMagical) {
		this.isMagical = isMagical;
	}
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getMagicPower() {
		return magicPower;
	}

	public void setMagicPower(int magicPower) {
		this.magicPower = magicPower;
	}

	public int getMagicResistance() {
		return magicResistance;
	}

	public void setMagicResistance(int magicResistance) {
		this.magicResistance = magicResistance;
	}

	public List<Spell> getKnownSpells() {
		return knownSpells;
	}

	public void setKnownSpells(List<Spell> knownSpells) {
		this.knownSpells = knownSpells;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	};
	
	// Methods to change statistics
	public void increaseHitPoints() {};
	public void increaseMaxHitPoints() {};
	public void increaseAttackPoints() {};
	public void increaseDefense() {};
	public void increaseSpeed() {};
	public void increaseLevel() {};
	public void increaseExperienceReward() {};
	public void takeDamage() {};
	public void heal() {};
	public void levelUp() {};
	public void initializeSpells() {};

}

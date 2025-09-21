import java.util.List;
import java.util.Random;

public class Player {
	// Attributes
	private String name = "";
    private int hitPoints;
    private int maxHitPoints;
    private int attackPoints;
    private int defense;
    private int speed;
    private int level;
    private boolean isAlive;
    private boolean isMagical;
    private int mana;
    private int maxMana;
    private int magicPower;
    private int magicResistance;
    private List<Spell> knownSpells;
    private int experience; 
    private Random random;
    private Inventory inventory;
    
    // Constructor
    public Player(String name, int maxHitPoints, int attackPoints, 
            int defense, int speed, boolean isMagical) {
    	this.name = name;
    	this.maxHitPoints = maxHitPoints;
    	this.hitPoints = maxHitPoints;
    	this.attackPoints = attackPoints;
    	this.defense = defense;
    	this.speed = speed;
    	this.isMagical = isMagical;
    	this.maxMana = isMagical ? 50 : 0;
    	this.mana = this.maxMana;
    	this.magicPower = isMagical ? 10 : 0;
    	this.magicResistance = 5;
   
    	initializeSpells();
    }
    
    // Getters and setters
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

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
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
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	// Methods to change statistics
	public void increaseHitPoints() {};
	public void increaseMaxHitPoints() {};
	public void increaseAttackPoints() {};
	public void increaseDefense() {};
	public void increaseSpeed() {};
	public void increaseLevel() {};
	public void increaseExperience() {};
	public void takeDamage() {};
	public void heal() {};
	public void levelUp() {};
	public void initializeSpells() {};

}
